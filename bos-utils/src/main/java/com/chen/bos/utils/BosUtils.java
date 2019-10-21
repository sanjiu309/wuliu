package com.chen.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.chen.bos.entity.User;

public class BosUtils {

	//获取Session
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	//获取Session中登陆的User
	public static User getLoginUser(){
		return (User)getSession().getAttribute("user");
	}
}
