package com.chen.bos.interceptor;

import org.apache.struts2.ServletActionContext;

import com.chen.bos.entity.User;
import com.chen.bos.utils.BosUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 登陆拦截器
 * @author 陈
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor{

	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = BosUtils.getLoginUser();
		if(user==null){
			return "login";
		}
		return invocation.invoke();
	}
	
}
