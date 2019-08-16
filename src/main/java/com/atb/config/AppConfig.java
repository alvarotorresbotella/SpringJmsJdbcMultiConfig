package com.atb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.atb")
@Import({JmsConfig.class,JbdcConfig.class,RestConfig.class})
public class AppConfig {
	
}
