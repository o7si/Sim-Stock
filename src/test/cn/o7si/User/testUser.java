package cn.o7si.User;

import cn.o7si.controller.UserController;
import cn.o7si.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testUser {

    private ApplicationContext ac;
    private UserController userController;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("spring.xml");
        userController = ac.getBean("userController", UserController.class);
    }

    @Test
    public void testUserIsExist() {
        userController.existUser("test");
    }

    @Test
    public void testRegisterUser() {
        userController.registerUser("test", "123456");
    }

    @Test
    public void testLoginUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        userController.loginUser(user);
    }
}
