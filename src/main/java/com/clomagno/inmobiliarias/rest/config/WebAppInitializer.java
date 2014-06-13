package com.clomagno.inmobiliarias.rest.config;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

public class WebAppInitializer implements WebApplicationInitializer {
	private WebApplicationContext createRootContext(
			ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext=null;
		rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(
				MVCConfiguration.class, 
				SecurityConfiguration.class,
				JPAConfiguration.class,
				ReSTConfiguration.class);

		servletContext.addListener(new ContextLoaderListener(rootContext));
		servletContext.setInitParameter("defaultHtmlEscape", "true");

		return rootContext;
	}
	
	private void configureCORSFilter(ServletContext servletContext,
			WebApplicationContext rootContext) {
		FilterRegistration.Dynamic corsFilter = servletContext.addFilter(
				"corsFilterChain", SimpleCORSFilter.class);
		corsFilter.addMappingForUrlPatterns(null, false, "/*");
	}

	private void configureSpringSecurity(ServletContext servletContext,
			WebApplicationContext rootContext) {
		FilterRegistration.Dynamic springSecurity = servletContext.addFilter(
				"springSecurityFilterChain", new DelegatingFilterProxy(
						"springSecurityFilterChain", rootContext));
		springSecurity.addMappingForUrlPatterns(null, true, "/*");
	}

	private void configureSpringMvc(ServletContext servletContext,
			WebApplicationContext rootContext) {
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		
		mvcContext.setParent(rootContext);
		ServletRegistration.Dynamic appServlet = servletContext.addServlet(
				"webservice", new DispatcherServlet(mvcContext));
		appServlet.setLoadOnStartup(1);
		Set<String> mappingConflicts = appServlet.addMapping("/*");

		if (!mappingConflicts.isEmpty()) {
			System.out.println("Mapping error!!!");
			throw new IllegalStateException(
					"'webservice' cannot be mapped to '/'");
		}
	}

	@Override
	public void onStartup(ServletContext servletContext) {
		WebApplicationContext rootContext = createRootContext(servletContext);
		
		configureSpringMvc(servletContext, rootContext);

		configureSpringSecurity(servletContext, rootContext);
		
		configureCORSFilter(servletContext, rootContext);
	}
}
