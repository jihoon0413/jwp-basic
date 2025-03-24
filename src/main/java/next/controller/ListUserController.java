package next.controller;

import core.db.DataBase;
import core.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListUserController implements Controller {
    private static final long serialVersionUID = 1L;


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }

        req.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
}
