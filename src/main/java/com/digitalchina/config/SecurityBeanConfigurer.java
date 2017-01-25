package com.digitalchina.config;

import com.digitalchina.config.security.CasProperties;
import com.digitalchina.config.security.SecureProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 胡本强
 * Date: 2016-11-29:15:37
 */
@Configuration
@AutoConfigureBefore(SecurityConfigurer.class)
@EnableConfigurationProperties({
        SecureProperties.class, CasProperties.class
})
public class SecurityBeanConfigurer extends com.digitalchina.platform.security.config.SecurityBeanConfigurer {

}
