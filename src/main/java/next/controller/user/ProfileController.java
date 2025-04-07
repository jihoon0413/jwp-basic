package next.controller.user;

import core.db.DataBase;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileController extends AbstractController {
    private static final long serialVersionUID = 1L;

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        req.setAttribute("user", user);
        return jspView("/user/profile.jsp");
    }
}
