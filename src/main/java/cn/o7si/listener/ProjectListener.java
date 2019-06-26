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

@Component
@WebListener
public class ProjectListener implements ApplicationListener, ServletContextListener {

    private MarketManager marketManager;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (marketManager == null) {
            marketManager = new MarketManager(5 * 1000);
//            marketManager.start();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        marketManager.setRunState(false);
    }
}
