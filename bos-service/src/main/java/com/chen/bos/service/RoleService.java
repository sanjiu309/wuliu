package com.chen.bos.service;

import java.util.List;

import com.chen.bos.entity.Role;
import com.chen.bos.utils.PageBean;

public interface RoleService {

	void save(Role model, String functionIds);

	void pageQuery(PageBean pageBean);

	List<Role> findAll();

}
