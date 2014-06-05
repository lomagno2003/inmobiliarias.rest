package com.clomagno.inmobiliarias.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class ReSTConfiguration 
	extends RepositoryRestMvcConfiguration 
	{
}
