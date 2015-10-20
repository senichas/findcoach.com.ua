package ua.com.findcoach.conf;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by senich on 10/1/2015.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/findcoach/*");

        insertSpringSecurityFilterChain(servletContext);
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("ua.com.findcoach.conf");
        return context;
    }

    private void insertSpringSecurityFilterChain(ServletContext servletContext) {
        String filterName = "springSecurityFilterChain";
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy(filterName);

        registerFilter(servletContext, true, filterName, springSecurityFilterChain);
    }

    private final void registerFilter(ServletContext servletContext, boolean insertBeforeOtherFilters, String filterName, Filter filter) {
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if (registration == null) {
            throw new IllegalStateException("Duplicate Filter registration for \'" + filterName + "\'. Check to ensure the Filter is only configured once.");
        } else {
            registration.setAsyncSupported(true);
            EnumSet dispatcherTypes = this.getSecurityDispatcherTypes();
            registration.addMappingForUrlPatterns(dispatcherTypes, !insertBeforeOtherFilters, new String[]{"/*"});
        }
    }

    protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
    }
}
