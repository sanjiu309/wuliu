package com.chen.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chen.bos.dao.UserDao;
import com.chen.bos.entity.User;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User findByUsername(String username) {
		String hql="from User where username=?";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, username);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
