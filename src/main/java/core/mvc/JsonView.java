package core.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class JsonView implements View{

    @Override
    public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse res) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        res.setContentType("application/json:charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.print(mapper.writeValueAsString(model));
    }


}
