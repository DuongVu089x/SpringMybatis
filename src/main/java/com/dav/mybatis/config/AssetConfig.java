package com.dav.mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.dav.mybatis.common.logger.MyLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class AssetConfig.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.dav.mybatis.common.logger" })
public class AssetConfig {

	/**
	 * My logger.
	 *
	 * @return the my logger
	 */
	@Bean
	public MyLogger myLogger() {
		return new MyLogger();
	}
}
