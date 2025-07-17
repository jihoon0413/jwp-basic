package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class DeleteQuestionController extends AbstractController {

    QuestionDao questionDao = QuestionDao.getInstance();
    AnswerDao answerDao = AnswerDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        if(!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

        long questionId = Long.parseLong(req.getParameter("questionId"));

        String questionWriter = questionDao.findById(questionId).getWriter();
        List<Answer> answerList = answerDao.findAllByQuestionId(questionId);

        for(Answer answer : answerList) {
            if(!Objects.equals(answer.getWriter(), questionWriter)) {
                throw new Exception("다른 사용자의 답글이 달려 삭제할 수 없습니다.");
            }
        }

        questionDao.delete(questionId);

        return jspView("redirect:/home");

    }
}
