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
    }

    @Test
    public void testRegisterUser() {
    }

    @Test
    public void testLoginUser() {
    }
}
