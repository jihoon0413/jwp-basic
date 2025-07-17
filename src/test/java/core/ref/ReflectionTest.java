package core.ref;


import next.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        // 1. Junit3Test : 특정 문자로 시작하는 메소드 실행하기
        Class<Junit3Test> junit3TestClass = Junit3Test.class;

        for (Method method : junit3TestClass.getMethods()) {
            if(method.getName().startsWith("test")) {
                method.invoke(junit3TestClass.getConstructor().newInstance());
            }
        }
        // 2. Junit4Test : 특정 어노테이션이 있는 메소드 실행하기

        Class<Junit4Test> junit4TestClass = Junit4Test.class;

        for (Method method :junit4TestClass.getMethods()) {
            if(method.isAnnotationPresent(MyTest.class)) {
                method.invoke(junit4TestClass.getConstructor().newInstance());
            }
        }

        // 3. User의 생성자 가져오기
        Class<User> userClass = User.class;

//        Constructor<User> constructor = userClass.getDeclaredConstructor(String.class, String.class, String.class, String.class);
        Constructor<User> constructor = userClass.getConstructor(String.class, String.class, String.class, String.class);
        User user = constructor.newInstance("userId", "password", "wlgns", "email");
        logger.info(user.getName());

        // 4. private 한 필드 값 설정하기
        Student student = new Student();

        //private 한 필드에 접근하기 위해 getDeclaredField 사용
//        Field field = student.getClass().getDeclaredField("name");
        Field[] fields = student.getClass().getDeclaredFields();

        fields[0].setAccessible(true);
        fields[1].setAccessible(true);

//        field.setAccessible(true);

//        field.set(student, "wlgns");
//        field.set(student, 25);

        fields[0].set(student, "wlgns");
        fields[1].set(student, 25);

        logger.info(student.getName());
        logger.info(String.valueOf(student.getAge()));

    }
}
