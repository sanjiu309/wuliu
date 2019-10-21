package com.chen.bos.dao;

import com.chen.bos.entity.User;

public interface UserDao extends IBaseDao<User>{

	public User findByUsername(String username);

}
