package com.digitalchina.appname.api.controller;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.platform.security.auth.CustomUser;
import com.digitalchina.platform.security.context.UserProxy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * 5 * Date: 2016-11-30:17:03
 */
@RestController
public class UserController {

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
}
