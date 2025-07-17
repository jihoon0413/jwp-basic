package next.controller.qna;

import core.mvc.*;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController extends AbstractController {

    AnswerDao answerDao = AnswerDao.getInstance();
    QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Long answerId = Long.valueOf(req.getParameter("answerId"));

        Answer answer = answerDao.findById(answerId);

        long questionId = answer.getQuestionId();

        answerDao.delete(answerId);
        questionDao.updateCountOfAnswer(questionId, -1);

        int countOfAnswer = questionDao.findById(questionId).getCountOfAnswer();

        return jsonView()
                .addObject("countOfAnswer", countOfAnswer)
                .addObject("result", Result.ok());


    }
}
