package com.digitalchina.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 胡本强 on 2016/8/21.
 */
@ConfigurationProperties(prefix = "sc")
public class SecureProperties extends com.digitalchina.platform.security.properties.SecureProperties {
}
