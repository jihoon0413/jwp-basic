package next.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import core.mvc.Controller;
import next.model.User;

public class LoginController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = DataBase.findUserById(id);

        if(user == null) {
            req.setAttribute("loginFailed", true);
            return "/user/login.jsp";
        }

        if(!user.matchPassword(password)) {
            req.setAttribute("loginFailed", true);
            return "/user/login.jsp";
        }

        HttpSession session = req.getSession();
        session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
        return "redirect:/";
    }
}
