package com.atb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.atb")
@Import({JmsConfiguration.class,JbdcConfig.class})
public class AppConfig {
	
}
