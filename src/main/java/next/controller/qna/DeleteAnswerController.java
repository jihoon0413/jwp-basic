package next.controller.qna;

import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.dao.AnswerDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController implements Controller {


    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Long answerId = Long.valueOf(req.getParameter("answerId"));
        AnswerDao answerDao = new AnswerDao();

        answerDao.delete(answerId);

        View view = new JsonView();

        return new ModelAndView(view);


    }
}
