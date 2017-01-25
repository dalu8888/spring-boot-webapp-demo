###### 名词解释：
1. Eureka Server：服务注册中心
1. Eureka Service: 服务/接口提供方
1. Eureka Client：服务调用方
1. Ribbon：提供客户端路由的组件


###### 调用过程
1.  Eureka Server启动
1.  Eureka Service启动时，会将自身的ip及port注册到server上
1.  Eureka Client启动时，会将Eureka Server上的注册信息拉到本地，并缓存到Ribbon中


###### 请注意
一旦你的API注册到Eureka上，并且开放给别人用了，就不要将本地的系统注册到Eureka上，因为别人调不到你本地的服务。
在本地测试时，必须换一个名字（spring.application.name）。


###### 如何配置Eureka Service

1. 在pom.xml中引入以下依赖

```
<dependencies>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-eureka</artifactId>
	</dependency>
</dependencies>

<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>Brixton.SR7</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

2. 在application.yaml或PAASEnvironmentPostProcessor.java中加入以下代码，请注意spring.application.name需要改成你项目的名字，例如mscx-dict-api

```
--- # webserver
server:
  port: 8080  #请一定放8080
  context-path: /

--- # eureka
spring:
  application:
    name: spring-boot-demo                                     #当前模块名
	groupName: mscx                                            #当前项目名

eureka:
  client:
    registerWithEureka: true                                   #改为false时，服务将不会注册到Eureka
    fetchRegistry: true                                        #改为false时，将不会从Server上拉取注册信息
    registry-fetch-interval-seconds: 10                        #生产环境上，这个值得设为30
    initial-instance-info-replication-interval-seconds: 5      #生产环境上，这个值得设为10
    serviceUrl:
      defaultZone: http://mscx-eureka-server-mgr.eastdc.cn:82/eureka/,http://mscx-eureka-server-mgr-2.eastdc.cn:82/eureka/
    healthcheck:
      enabled: true
  instance:
    prefer-ip-address: true
    lease-renewal-interval-in-seconds: 10                       #生产环境上，这个值得设为30
    lease-expiration-duration-in-seconds: 10                    #生产环境上，这个值得设为30
  server:
    waitTimeInMsWhenSyncEmpty: 0
    registrySyncRetries: 0
```

3. 在Spring Boot的主类中加入@EnableDiscoveryClient

4. 启动系统后，在http://mscx-eureka-server-mgr.eastdc.cn:82/中即可看到当前系统的注册信息


###### 如何配置Eureka Client
1. 在pom.xml中引入以下依赖

```
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>1.4.2.RELEASE</version>
	<relativePath />
</parent>
	
<dependencies>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-eureka</artifactId>
	</dependency>

	<dependency>
		<groupId>com.digitalchina.invoketrace</groupId>
		<artifactId>platform-invoke-trace-client</artifactId>
		<version>1.0.0</version>
	</dependency>

	<dependency>
		<groupId>com.digitalchina.resttemplate</groupId>
		<artifactId>platform-retryable-resttemplate</artifactId>
		<version>1.0.1</version>
	</dependency>

	<dependency>
		<groupId>org.springframework.retry</groupId>
		<artifactId>spring-retry</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>

	<dependency>
		<groupId>org.apache.httpcomponents</groupId>
		<artifactId>httpclient</artifactId>
		<version>4.5.2</version>
	</dependency>

	<dependency>
		<groupId>com.alibaba</groupId>
		<artifactId>fastjson</artifactId>
		<version>1.2.6</version>
	</dependency>
</dependencies>

<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>Brixton.SR7</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

2. 在Spring Boot的主类中加入@EnableDiscoveryClient

3. 在application.yaml或PAASEnvironmentPostProcessor.java中加入以下配置
```
--- # webserver
server:
  port: 8080  #请一定放8080
  context-path: /

--- # eureka
spring:
  application:
    name: spring-boot-demo                                     #当前模块名
	groupName: mscx                                            #当前项目名

eureka:
  client:
    registerWithEureka: true                                   #改为false时，服务将不会注册到Eureka
    fetchRegistry: true                                        #改为false时，将不会从Server上拉取注册信息
    registry-fetch-interval-seconds: 10                        #生产环境上，这个值得设为30
    initial-instance-info-replication-interval-seconds: 5      #生产环境上，这个值得设为10
    serviceUrl:
      defaultZone: http://mscx-eureka-server-mgr.eastdc.cn:82/eureka/,http://mscx-eureka-server-mgr-2.eastdc.cn:82/eureka/
    healthcheck:
      enabled: true
  instance:
    prefer-ip-address: true
    lease-renewal-interval-in-seconds: 10                       #生产环境上，这个值得设为30
    lease-expiration-duration-in-seconds: 10                    #生产环境上，这个值得设为30
  server:
    waitTimeInMsWhenSyncEmpty: 0
    registrySyncRetries: 0

--- # platform
platform:
  invokeTrace:
    enabled: true
    url: http://invoke-trace.eastdc.cn:82/invoke.do
  ribbon:
    httpClient:
      timeToLiveInSeconds: 30                                  #长连接保持时间，默认30秒
      maxConnectionsTotal: 100                                 #总连接数，默认100
      maxConcurrentPerRoute: 30                                #同路由的并发数，默认100
      maxConnectionTimeoutInSeconds: 1                         #连接超时时间，默认3秒
      maxReadTimeoutInSeconds: 5                               #数据读取超时时间，默认10秒
      maxRetryAttempts: 3                                      #API访问失败时的重试次数，默认3次
```

4. 在BootApplication.java中加入以下代码

````
	@Bean
	@LoadBalanced
	RestTemplate retryableLoadbalancedRestTemplate() {
		return new RestTemplate();
	}
```

5. 使用以下代码调用接口
```
@RestController
public class ApiConsumerController {
	@Autowired
	RetryableLoadbalancedRestTemplateUtil restTemplate;

	@RequestMapping(value = "/consume", method = RequestMethod.GET, produces = "application/json")
	public String consume(HttpServletRequest req) {
		String result=restTemplate.get("spring-boot-demo: /query.do?id=36", null);
		return result;
	}
}
```

6. 查看微服务接的调用情况
http://invoke-trace.eastdc.cn:82/findAllInvoke.do?project=mscx&invokeDate=2016-12-18