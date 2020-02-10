package com.practice.contact.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.practice.contact.dao.ContactDAO;
import com.practice.contact.dao.ContactDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.practice.contact")
public class SpringMvcConfig implements WebMvcConfigurer{
	@Bean
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb?verifyServerCertificate=false&useSSL=true");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		return dataSource;
		
	}
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver =new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	
	@Bean
	public ContactDAO getContactDAO() {
		ContactDAO dao=new ContactDAOImpl(getDataSource());
		return dao;
		
	}
	
	
}
