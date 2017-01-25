## MSCX后端upms集成

### 一. maven jar包引入

> 首先引入mscx-security的jar包，引入方法如下：
TODO 改变gropid 为platform
```
	<!-- for @ConfigurationProperties  @add by 胡本强 for upms-->
	<dependency>
	    <groupId>com.digitalchina.platform.boot</groupId>
        <artifactId>platform-security</artifactId>
	    <version>1.1</version>
	</dependency>
```


> mscx-security依赖的jar如下

```
	<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <version>1.4.2.RELEASE</version>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>19.0</version>
    </dependency>

    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpmime</artifactId>
        <version>4.3.5</version>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.6</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-cas</artifactId>
        <version>4.1.3.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-config</artifactId>
        <version>4.1.3.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-core</artifactId>
        <version>4.1.3.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-web</artifactId>
        <version>4.1.3.RELEASE</version>
    </dependency>
```
><font color="red">如果项目中已经包含上述的jar ，请在引入mscx-security的过程中滤除依赖的jar以保证项目内jar包的版本不冲突</font>，过滤jar的方法如下：

```
	<dependency>
	    <groupId>com.digitalchina.platform.boot</groupId>
        <artifactId>platform-security</artifactId>
	    <version>1.1</version>
		<exclusions>
            <exclusion>
                <artifactId>fastjson</artifactId>
                <groupId>com.alibaba</groupId>
            </exclusion>
        </exclusions>
	</dependency>
```

### 二. 配置security并添加secure.propertis

> - 引入 secure.properties，放在和application.yml同一目录下（可以调整，建议保持一致）

	```
	###config cas properties
	#登录成功回调地址
	cas.loginSuccessBackUrl=http://localhost:8080/login/cas
	#登录地址
	cas.loginUrl=http://mscxsso.eastdc.cn:82
	#认证key
	cas.providerKey=key4CasAuthenticationProvider
	#无需健全url
	cas.ignoreAuthUrls=/css/**,/js/**,/layer/**,/img/**,/,/favicon.ico,/images/**
	#默认开启过滤
	cas.rejectPublicInvocations=true
	#开启此配置，未登录访问鉴权资源默认不跳转到登录页而直接返回500800错误
	cas.authPomptType=true
	#权限过滤器默认st校验入口
	cas.filterProcessesUrl=/login/cas
	#登录后是否访问默认页
	cas.alwaysTarget=true
	#默认页地址
	cas.targetUrl=http://localhost:8080/
	#是否访问上次一request请求
	cas.redirectCurrentUrl=false
	
	
	### Configure upms  param
	#项目编号
	sc.pCode=demo
	#远程调用upms的apiKey
	sc.apiKey=543df6e9aed348dc85e05593b1c9c24d
	#所有需权限访问的url
	sc.allApplicationUrl=http://mscxupms.eastdc.cn:82/api/allApplication.json
	#用户信息
	sc.userInfoUrl=http://mscxupms.eastdc.cn:82/api/userInfo.json
	#当前用户所有可访问的url
	sc.authApplicationUrl=http://mscxupms.eastdc.cn:82/api/authApplication.json
	#httpclient连接超时时间
	sc.httpConnectionTimeout=60
	```
> - 配置资源加载,配置目录下加入以下两个类（读取配置文件内容并将文件映射成Bean）

```
@ConfigurationProperties(prefix = "cas", locations = "classpath:secure.properties")
public class CasProperties extends com.digitalchina.platform.security.properties.CasProperties {

}
```

和

```
@ConfigurationProperties(prefix = "sc", locations = "classpath:secure.properties")
public class SecureProperties extends com.digitalchina.platform.security.properties.SecureProperties {
}

```
	
### 三. 通过springboot ，配置security认证和授权

> - 在项目的config目录中，添加

```
@Configuration
@AutoConfigureBefore(SecurityConfigurer.class)
@EnableConfigurationProperties({
        SecureProperties.class, CasProperties.class
})
public class SecurityBeanConfigurer extends com.digitalchina.platform.security.config.SecurityBeanConfigurer {

}
```
和

```
@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends com.digitalchina.platform.security.config.SecurityConfigurer {

}
```

其中，SecurityBeanConfigurer用于显示配置SecurityConfigurer所需要的bean，SecurityConfigurer用于配置认证和授权规则


### 四. 项目内必须包含的获取用户的方法，对应的url必须为：/user/currentUserInfo.json

```
@RequestMapping(value = "/user/currentUserInfo.json")
public RtnData userInfo(HttpServletRequest request) {

    SecurityContext context = null;
    HttpSession httpSession = request.getSession(false);
    try {
        if (httpSession != null) {
            Object contextFromSession = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
            if (contextFromSession == null) {
                return null;
            } else if (!(contextFromSession instanceof SecurityContext)) {
                return null;
            } else {
                context = (SecurityContext) contextFromSession;
            }
        }

        if (context == null) {
            return RtnData.fail(null, "unLogin Error");
        }

        CustomUser userDetails = ((CustomUser) context.getAuthentication().getPrincipal());
        return RtnData.ok(userDetails.getExtendInfo());
    } catch (Exception e) {
        return RtnData.fail(null, "unLogin Error");
    }
}
```


如果其他地方需要获取用户信息，可以参考此方法内部获取用户信息的逻辑。<br/>
前端获取用户信息必须使用此链接且不能更改，因为前端框架获取用户信息使用了此链接，所以所有的整合upms的后端管理项目必须保证有此方法且url唯一。



### 四.通过UserProxy可以获取各项用户信息，比如 UserProxy.getUserId()，具体的说明如下：

| 功能        | 调用说明         | 
| ------------- |:-------------:| 
| UserProxy.getUserId()| 获取用户ID | 
| UserProxy.getAccount()| 获取用户账号|
| UserProxy.getName() | 获取用户名称| 
| UserProxy.getDesc() | 获取描述| 
| UserProxy.getExt1() | 获取扩展字段1| 
| UserProxy.getExt2() | 获取扩展字段2| 
| UserProxy.getExt3() | 获取扩展字段3| 
| UserProxy.getExt4() | 获取扩展字段4| 
| UserProxy.getExt5() | 获取扩展字段5| 



### 五. 取消集成
> - 如果希望取消注入，则注释掉config目录下的SecurityConfigurer，SecurityBeanConfigurer，CasProperties，SecureProperties 4个类，
> - 注释掉UserController类
> - 注释掉pom.xml的下面的这个包的引用

```
	<!-- for @ConfigurationProperties  @add by 胡本强 for upms-->
	<dependency>
	    <groupId>com.digitalchina.platform.boot</groupId>
        <artifactId>platform-security</artifactId>
	    <version>1.1</version>
	</dependency>
```
> ps.  取消upms-security集成后，访问index.html会报错，因为index页面本身集成了upms，所以不能访问index页面。

---

---

胡本强
2016-12-03