package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiListQuestionController extends AbstractController {

    QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        return jsonView().addObject("questions", questionDao.findAll());
    }
}
