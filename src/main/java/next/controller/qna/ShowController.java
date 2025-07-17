package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowController extends AbstractController {

    private final QuestionDao questionDao = QuestionDao.getInstance();
    private final AnswerDao answerDao = AnswerDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long questionId = Long.valueOf(req.getParameter("questionId"));
        req.setAttribute("question", questionDao.findById(questionId));
        req.setAttribute("answers", answerDao.findAllByQuestionId(questionId));
        return jspView("/qna/show.jsp");


//        ModelAndView mav = jspView("/qna/show.jsp");
//        mav.addObject("question", questionDao.findById(questionId));
//        mav.addObject("answers", answerDao.findAllByQuestionId(questionId));
//        return mav;


    }
}
