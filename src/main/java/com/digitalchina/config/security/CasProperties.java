package com.digitalchina.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 * User: 胡本强
 * Date: 2016-11-28:14:36
 */
@ConfigurationProperties(prefix = "cas")
public class CasProperties extends com.digitalchina.platform.security.properties.CasProperties {

}
