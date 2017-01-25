##主要目的是为了界面（html）中能够动态的获取upms访问地址。以此达到动态引入upms中公共的css、js等文件。

#### 1. 复制公共的java文件
  复制
     
     com.digitalchina.common.config.WebConfig
     com.digitalchina.common.interceptor.UPMSInterceptor
     
  两个文件到自己工程对应的包中。(如果包不存在，创建响应的包)
  
#### 2. 配置文件中加入upms地址
 在本工程的application.yaml文件中，加入如下配置
 
    upms:
      url: http://mscxupms.eastdc.cn:82
      interceptor.patterns: /**/*.do,/
      
upms.url 是upms系统的域名。
upms.interceptor.patterns 是拦截器过来地址。一般情况下，使用上面配置即可。多个配置使用逗号隔开。


#### 3. 页面中应用upms地址

页面中，可以使用如下方式引用upms地址

   <script th:src="${upms.url}+'/lib/js/jquery.validate.js'"></script>
 
其中${upms.url}代表upms访问地址。

# 4. 注意系统中，原来使用maven profile 替换方式的，一定要改过来。


  