package core.mvc;

import next.controller.*;
import next.controller.qna.*;
import next.controller.user.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/home", new HomeController());
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/qna/form", new ForwardController("/qna/form.jsp"));
        controllers.put("/users/login", new LoginController());
        controllers.put("/users/create", new CreateUserController());
        controllers.put("/users/logout", new LogoutController());
        controllers.put("/users", new ListUserController());
        controllers.put("/users/updateForm", new UpdateUserFormController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/profile", new ProfileController());
        controllers.put("/qna/show", new ShowController());
        controllers.put("/qna/create", new CreateQuestionController());
        controllers.put("/qna/delete", new DeleteQuestionController());
        controllers.put("/qna/updateForm", new UpdateFormQuestionController());
        controllers.put("/qna/update", new UpdateQuestionController());
        controllers.put("/api/qna/addAnswer", new AddAnswerController());
        controllers.put("/api/qna/deleteAnswer", new DeleteAnswerController());
//        controllers.put("/api/qna/updateAnswer", new DeleteAnswerController());
        controllers.put("/api/qna/list", new ApiListQuestionController());
    }

    public Controller getController(String key) {
        return controllers.get(key);
    }
}
