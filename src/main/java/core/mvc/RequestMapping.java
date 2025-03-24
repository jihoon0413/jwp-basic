package core.mvc;

import next.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/", new HomeController());
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/users/login", new LoginController());
        controllers.put("/users/create", new CreateUserController());
        controllers.put("/users/logout", new LogoutController());
        controllers.put("/users", new ListUserController());
        controllers.put("/users/updateForm", new UpdateUserFormController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/profile", new ProfileController());
    }

    public Controller getController(String key) {
        return controllers.get(key);
    }
}
