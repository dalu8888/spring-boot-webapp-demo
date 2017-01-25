Invoke trace模块用来追踪微服务之间的相互调用。例如，订单模块调了用户模块的/xxx.do接口100次，其中有10次是失败的。

如果在Eureka环境中使用InvokeTrace，则已由com.digitalchina.resttemplate:platform-retryable-resttemplate-1.0.4.jar自动集成了。


以下是集成步骤。

###### 在pom.xml中加上以下依赖

```
<dependency>
  <groupId>com.digitalchina.resttemplate</groupId>
  <artifactId>platform-retryable-resttemplate</artifactId>
  <version>1.0.5</version>
</dependency>

<dependency>
  <groupId>com.digitalchina.invoketrace</groupId>
  <artifactId>platform-invoke-trace-client</artifactId>
  <version>1.0.2</version>
</dependency>
```

###### 在application.yaml中加入以下配置项

```
spring:
  application:
    name: spring-boot-demo    #调用服务名称
    groupName: mscx    #项目分组

--- # platform
platform:
  invokeTrace:
    fileEnabled: false  #是否保存文件内容(application/multipart-data),默认值false
    enabled: true       #是否启用invokeTrace,默认值false
    detailEnabled: true   #是否记录详情(headers,parameters),默认值false
    encoding: UTF-8         #参数编码方式(仅用于params),默认值UTF-8
    url: http://invoke-trace2.eastdc.cn:82/invoke.do  #invokeTrace 服务接口地址
    parameterIgnore: password;pwd;mobile;  #请求敏感参数过滤,不区分大小写. 用分号(';')隔开
    cron: 0/15 * * * * ?    #将调用信息发送invokeTrace server时间间隔，默认15秒
    header:        #请求headers中需要过滤掉的header（仅限于请求头）
      excluded: Accept;Accept-Charset;Accept-Encoding;Accept-Language;Accept-Datetime;Cache-Control;Connection;Content-MD5;Content-Length;Date;Expect;Forwarded;From;Host;If-Match;If-Modified-Since;If-None-Match;If-Range;If-Unmodified-Since;Max-Forwards;Origin;Pragma;Proxy-Authenticate;Proxy-Authorization;Range;Referer;TE;User-Agent;Upgrade;Via;Warning;DNT;X-Forwarded-For;X-Forward-Host;X-Forward-Proto;Front-End-Https;X-HttpMethod-Override;X-ATT-DeviceId;X-Wap-Profile;Proxy-Connection;X-UIDH;X-Request-ID;
    
```
###### portal-backend 集成

由于portal-backend 使用restTemplateUtil 中的execute方法，RequestCallback需要实现InvokeTrace接口,接口代码如下如下:

```
public interface InvokeTrace {
	/**
	 * 获取请求body
	 * @return
	 */
	public byte[] getBytes();
	
	/**
	 * 获取请求头
	 * @return
	 */
	public HttpHeaders getHeaders();
}
```

###### 测试

查看微服务接的调用情况(根据对应的项目名称,如点击mscx查看mscx下所有服务调用情况)
http://invoke-trace-mgr.eastdc.cn:82/


