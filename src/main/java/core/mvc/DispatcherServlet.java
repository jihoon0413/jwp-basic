package core.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    private RequestMapping rm;

    @Override
    public void init() throws ServletException {
        rm = new RequestMapping();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String url = req.getRequestURI();
        Controller controller = rm.getController(url);

        try {
            String viewName = controller.execute(req, res);
            if(viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
                res.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
                return;
            }
            RequestDispatcher rd = req.getRequestDispatcher(viewName);
            rd.forward(req, res);
        } catch (Throwable e) {
            logger.error("Exception : %s", e);
            throw new ServletException(e.getMessage());
        }
    }
}
