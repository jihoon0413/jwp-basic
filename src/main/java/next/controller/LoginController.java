package next.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String password = req.getParameter("password");
        UserDao userDao = new UserDao();

        User user = null;
        user = userDao.findByUserId(id);

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
