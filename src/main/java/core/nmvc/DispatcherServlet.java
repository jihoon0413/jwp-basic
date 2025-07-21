package core.nmvc;

import core.mvc.Controller;
import core.mvc.LegacyHandlerMapping;
import core.mvc.ModelAndView;
import core.mvc.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> mappings = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        log.info("DispatcherServlet init called");

        LegacyHandlerMapping lhm = new LegacyHandlerMapping();
        AnnotationHandlerMapping ahm = new AnnotationHandlerMapping("next.controller");
        ahm.initialize();

        mappings.add(lhm);
        mappings.add(ahm);
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException {

        String url = req.getRequestURI();

        log.debug("Method : {}, Request URI : {}", req.getMethod(), url);

        Object controller = getHandler(req);
        if(controller == null) {
            throw new IllegalArgumentException("존재하지 않는 url 입니다.");
        }

        if(controller instanceof Controller) {
            try {
                ModelAndView mav = ((Controller) controller).execute(req,res);
                View view = mav.getView();
                view.render(mav.getModel(),req, res);

            } catch (Exception e) {
                log.error("Exception : %s", e);
                throw new ServletException(e.getMessage());
            }
        } else if(controller instanceof HandlerExecution) {
            ((HandlerExecution) controller).handle(req, res);
        } else {
            throw new IllegalArgumentException();
        }

    }

    private Object getHandler(HttpServletRequest req) {
        for (HandlerMapping mapping : mappings) {
            Object controller = mapping.getHandler(req);
            if(controller != null) {
                return controller;
            }
        }
        return null;
    }


}
