package next.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController extends AbstractController {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String password = req.getParameter("password");
        UserDao userDao = UserDao.getInstance();

        User user = null;
        user = userDao.findByUserId(id);

        if(user == null) {
            req.setAttribute("loginFailed", true);
            return jspView("/user/login.jsp");
        }

        if(!user.matchPassword(password)) {
            req.setAttribute("loginFailed", true);
            return jspView("/user/login.jsp");
        }

        HttpSession session = req.getSession();
        session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
        return jspView("redirect:/");
    }
}
