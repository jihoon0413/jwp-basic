package next.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

public class HomeController extends AbstractController {
    private static final long serialVersionUID = 1L;
    private final QuestionDao questionDao = QuestionDao.getInstance();


    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return jspView("index.jsp").addObject("questions", questionDao.findAll());
    }
}
