package org;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

public final class WebConfigTest {

    public static ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    public static HandlerExceptionResolver exceptionResolver(){
        SimpleMappingExceptionResolver mappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties exceptionMapping = new Properties();
        exceptionMapping.put("NoValidLoginException", "error");

        Properties statusCodeMapping = new Properties();
        statusCodeMapping.put("error", "404");

        mappingExceptionResolver.setExceptionMappings(exceptionMapping);
        mappingExceptionResolver.setStatusCodes(statusCodeMapping);
        return mappingExceptionResolver;


    }



}
