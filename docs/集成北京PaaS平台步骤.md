
### 1. 工程中引入Dockerfile文件
复制[spring-boot-webapp-demo][1]工程根路径下的Dockerfile文件到自己工程根路径下面。

### 2. 工程中引入Jenkin.sh文件
复制[spring-boot-webapp-demo][1]工程根路径下的Jenkin.sh文件到自己工程根路径下面。并修改jenkin.sh文件中IMAGE=自己应用的名称（管理端以-mgr结尾，api以-api结尾）。

### 3. 引入配置中心jar包。

    
    <dependency>
			<groupId>cn.dceast.platform</groupId>
			<artifactId>platform-config-adapter</artifactId>
			<version>1.0.3</version>
		</dependency>
	
#### 修改 com.digitalchina.config.security 包中的 CasProperties.java,SecureProperties.java 文件。去掉@ConfigurationProperties注解中，location属性。

## 以上规则只适用spring-boot框架。

### 4. 普通Spring工程，需要如下方式配置bean
    
    <bean id="propertyPlaceholderConfigAdapter" class="cn.dceast.platform.config.adapter.SpringPropertyPlaceholderConfigAdapter">
    	<property name="loadProperties">
    		<list>
    			<value>application.properties</value>
    			<value>cas.properties</value>
    		</list>
    	</property>
    </bean>
    
#### 注意，此bean需要在其他bean之前加载。
    
### 5. 普通java web工程
  使用 CommonConfigAdapter类。此类型readPropertiesByEnvironment()方法可以根据环境变量决定是读取本地properties文件还是读取配置中心。此方法默认读取application.properties。可以自定义设置此类的loadProperties属性。
  
### 6. 对于静态资源中，需要替换属性变量
   在web.xml中如下配置
   
   
    <context-param>
    <param-name>replaceFiles</param-name>
    <param-value>
    	1.js
    	2.js
    	3.css
    </param-value>
  </context-param>
  <context-param>
    <param-name>replaceFileStrs</param-name>
    <param-value>
    	co.test.helper
    </param-value>
  </context-param>
  <context-param>
    <param-name>replaceProperties</param-name>
    <param-value>
    	application.properties
    	cas.properties
    </param-value>
  </context-param>
  
    <listener>
      <listener-class>cn.dceast.platform.config.adapter.listener.ReplaceStaticResourceListener</listener-class>
    </listener>
    
 replaceFiles：需要替换的静态文件清单。
 replaceFileStrs：需要替换的属性。
 replaceProperties：默认替换的属性值的来源。

### 7. 修改好后，进行paas平台部署。详细部署流程请联系paas平台负责人。

  


  [1]: https://gitlab.dctech.club/labs/spring-boot-webapp-demo/blob/master/Dockerfile