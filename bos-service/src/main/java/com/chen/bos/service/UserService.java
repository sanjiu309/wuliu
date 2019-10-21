package com.chen.bos.service;

import com.chen.bos.entity.User;
import com.chen.bos.utils.PageBean;

public interface UserService {

	User login(User model);

	void modifyPwd(String id, String password);

	void save(User model, String[] roleIds);

	void pageQuery(PageBean pageBean);

}
