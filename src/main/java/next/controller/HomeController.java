package next.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;

public class HomeController implements Controller {
    private static final long serialVersionUID = 1L;


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "/index.jsp";
    }
}
