package config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WeatherAppContext implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationConfig.class, JpaConfig.class, FlyWayConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));

        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
        final ServletRegistration.Dynamic registrationDispatcher = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        registrationDispatcher.setLoadOnStartup(1);
        registrationDispatcher.addMapping("/");
    }
}
