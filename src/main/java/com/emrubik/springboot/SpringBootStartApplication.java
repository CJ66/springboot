package com.emrubik.springboot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource(locations = {"classpath*:spring/sub/mongo_framework_config.xml" })
public class SpringBootStartApplication{
    
    
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringBootStartApplication.class).web(true).run(args);
	}
}
