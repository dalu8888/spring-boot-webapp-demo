package com.digitalchina.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.digitalchina.common.data.ServiceRuntimeException;
import com.digitalchina.common.data.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuyd on 2016/12/3.
 * 获取 门户router请求头中的信息
 */
public class HttpHeaderInfoUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpHeaderInfoUtil.class);

    static public UserData getUserData(HttpServletRequest request){
        String data = request.getHeader("mscx-user-data");
        if(StringUtils.isEmpty(data)){
            logger.error("有一条缺少标准头信息的请求,RequestHeader有，" +
                            "Host:%s  Origin:%s  User-Agent:%s",
                    request.getHeader("Host"),
                    request.getHeader("Origin"),
                    request.getHeader("User-Agent"));
            throw new ServiceRuntimeException("路由缺少头信息，请补全");
        }
        return JSONObject.parseObject(data,UserData.class);
    }

    static public String getNameOrAccount(UserData userData){
        return StringUtils.isEmpty(userData.getName())?userData.getAccount():userData.getName();
    }


    static public String getAreaCode(HttpServletRequest request){
        String area = request.getHeader("mscx-area-code");
        if(StringUtils.isEmpty(area)){
            logger.error("有一条缺少标准头信息的请求,RequestHeader有，" +
                            "Host:%s  Origin:%s  User-Agent:%s",
                    request.getHeader("Host"),
                    request.getHeader("Origin"),
                    request.getHeader("User-Agent"));
            throw new ServiceRuntimeException("路由缺少头信息，请补全");
        }
        return area;
    }
}
