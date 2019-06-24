package cn.o7si.listener;


import cn.o7si.sim.MarketManager;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 暂时不能使用的一个类
 * 状况频出
 */
@Component
@WebListener
public class ProjectListener implements ApplicationListener, ServletContextListener {

    private MarketManager marketManager;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        marketManager = new MarketManager(5 * 1000);
        marketManager.start();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        marketManager.setRunState(false);
    }
}
