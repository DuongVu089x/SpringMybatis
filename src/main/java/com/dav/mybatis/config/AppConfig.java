package com.dav.mybatis.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class AppConfig extends WebMvcConfigurerAdapter{
	
	  @Bean
	  public MessageSource messageSource() {
	      ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
	      bean.setBasename("classpath:messages.properties");
	      bean.setDefaultEncoding("UTF-8");
	      return bean;
	  }

	  @Bean
	  public LocalValidatorFactoryBean validator() {
	      LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	      bean.setValidationMessageSource(messageSource());
	      return bean;
	  }

	  @Override
	  public Validator getValidator() {
	      return validator();
	  }
	}