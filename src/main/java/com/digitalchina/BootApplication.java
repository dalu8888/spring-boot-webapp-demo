package com.digitalchina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableDiscoveryClient
@EnableRetry
public class BootApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(BootApplication.class);
	
    /* 这个方法在打包在war包运行时是必需的 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootApplication.class);
    }
    
	@Bean
	@LoadBalanced
	RestTemplate retryableLoadbalancedRestTemplate() {
		return new RestTemplate();
	}
	
    public static void main(String[] args) {
    	log.debug("============ Application Starting ===========");
        SpringApplication.run(BootApplication.class, args);
    }
}
