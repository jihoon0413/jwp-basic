package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class UpdateFormQuestionController extends AbstractController {
    QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

        long questionId = Long.parseLong(req.getParameter("questionId"));

        Question question = questionDao.findById(questionId);
        if(!question.isSameUser(Objects.requireNonNull(UserSessionUtils.getUserFromSession(req.getSession())))) {
            throw new IllegalArgumentException("다른 사용자의 질문은 삭제할 수 없습니다.");
        }

        return jspView("/qna/update.jsp").addObject("question", question);
    }
}
