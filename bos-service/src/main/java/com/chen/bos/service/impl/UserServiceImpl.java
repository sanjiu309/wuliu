package com.chen.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.UserDao;
import com.chen.bos.entity.Role;
import com.chen.bos.entity.User;
import com.chen.bos.service.UserService;
import com.chen.bos.utils.MD5Utils;
import com.chen.bos.utils.PageBean;
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	public User login(User model) {
		User user = dao.findByUsername(model.getUsername());
		if(user==null){
			return null;
		}
		if(!MD5Utils.md5(model.getPassword()).equals(user.getPassword())){
			return null;
		}
		return user;
	}

	public void modifyPwd(String id, String password) {
		dao.executeUpdate("user.modifyPwd", MD5Utils.md5(password),id);
	}

	/**
	 * 添加一个用户，关联角色
	 */
	public void save(User model, String[] roleIds) {
		String md5 = MD5Utils.md5(model.getPassword());
		model.setPassword(md5);
		dao.save(model);
		if(roleIds!=null && roleIds.length>0){
			for (String rid : roleIds) {
				Role role=new Role(rid);
				model.getRoles().add(role);
			}
		}
	}

	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

}
