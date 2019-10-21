package com.chen.bos.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.chen.bos.dao.FunctionDao;
import com.chen.bos.dao.UserDao;
import com.chen.bos.entity.Function;
import com.chen.bos.entity.User;

public class BosRealm extends AuthorizingRealm {

	@Autowired
	private UserDao dao;
	@Autowired
	private FunctionDao functionDao;
	
	// 认证
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		//框架定义必须先根据用户名查询数据库中的密码，再比对用户输入的密码
		UsernamePasswordToken token =(UsernamePasswordToken) arg0;
		String username = token.getUsername();
		User user = dao.findByUsername(username);
		if(user==null){
			//用户输入的用户名不存在，return null抛出用户名不存在异常
			return null;
		}
		//框架负责比对数据库中的密码和用户输入的密码是否一致,创建认证对象,比对失败抛出密码不存在异常
		AuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}

	// 授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		//根据当前登陆用户查询数据，查询用户的权限
		User user = (User) arg0.getPrimaryPrincipal();
		List<Function> list=null;
		if("admin".equals(user.getUsername())){
			//管理员用户，直接查询所有权限
			DetachedCriteria dc=DetachedCriteria.forClass(Function.class);
			list = functionDao.findByCriteria(dc);
		}else{
			list=functionDao.findFunctionByUser(user.getId());
		}
		
		//为用户授权
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}

}
