package next.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.User;

public class CreateUserController extends AbstractController {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));

        log.debug("User : {}", user);

        UserDao userDao = UserDao.getInstance();
        userDao.insert(user);
        return jspView("redirect:/");
    }
}
