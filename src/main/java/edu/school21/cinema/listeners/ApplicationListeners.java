package edu.school21.cinema.listeners;

import edu.school21.cinema.config.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListeners implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ApplicationContext springContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        context.setAttribute("springContext", springContext);
    }
}
