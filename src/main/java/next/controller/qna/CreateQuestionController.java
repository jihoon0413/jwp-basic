package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController extends AbstractController {
    private final QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        if(!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }   // 3. 로그인 사용자 확인

        User user = UserSessionUtils.getUserFromSession(req.getSession());
        // 4. 세션에서 작성자 정보 가져오기
        if(user == null) {
            throw new IllegalArgumentException();
        }
        Question question = new Question(user.getUserId(), req.getParameter("title"), req.getParameter("contents"));

        questionDao.insert(question);


        return jspView("redirect:/home");
        // 2. 성공 후 "/"으로 redirect
    }
}
