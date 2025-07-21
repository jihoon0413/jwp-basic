package core.nmvc;

import core.annotation.Controller;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerScanner {

    private static final Logger log = LoggerFactory.getLogger(ControllerScanner.class);

    private Reflections reflections;

    public ControllerScanner(Object... basePackage) {
        reflections = new Reflections(basePackage);
    }

    public Map<Class<?>, Object> getControllers() {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
        return instantiateControllers(annotated);
    }

    public Map<Class<?>, Object> instantiateControllers(Set<Class<?>> preInitiatedControllers) {

        Map<Class<?>, Object> controllers = new HashMap<>();

        try {
            for (Class<?> clazz : preInitiatedControllers) {
                controllers.put(clazz, clazz.getConstructor().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException |InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage());
        }

        return controllers;
    }




}
