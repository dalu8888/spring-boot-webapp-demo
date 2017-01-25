package com.digitalchina.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * UPMS拦截器
 * 目的：在spring controller方法执行完后渲染视图之前，向Model中注入UMPS对象。<br/>
 *     使用thymeleaf模板渲染html页面中，可以使用${upms.url}获取upms系统的访问地址。<br/>
 *     这样，其他系统可以引用upms系统中公共的js，css文件。
 * @author zhang
 *
 */
public class UPMSInterceptor implements HandlerInterceptor {
	
	private static final String upms_obj_name="upms";
	
	private String upmsUrl;
	private UPMS upms=new UPMS();
	
	public UPMSInterceptor(){
		
	}
	
	public UPMSInterceptor(String upmsUrl){
		this.upmsUrl=upmsUrl;
	}
	/**
	 * 渲染视图后
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
    
	/**
	 * 渲染视图前
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView mv) throws Exception {
		
		if(mv!=null){
			upms.setUrl(upmsUrl==null?"":upmsUrl);
			mv.addObject(upms_obj_name, upms);
		}
		
	}

	/**
	 * 处理方法前
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		return true;
	}
	
	class UPMS {
		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
	}

}
