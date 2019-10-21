package com.chen.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.RoleDao;
import com.chen.bos.entity.Function;
import com.chen.bos.entity.Role;
import com.chen.bos.service.RoleService;
import com.chen.bos.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao dao;
	
	public void save(Role model, String functionIds) {
		dao.save(model);
		//角色关联权限
		if(StringUtils.isNotBlank(functionIds)){
			String[] fIds = functionIds.split(",");
			for (String fid : fIds) {
				//创建托管对象
				Function function=new Function();
				function.setId(fid);
				//让持久化对象去关联托管对象
				model.getFunctions().add(function);
			}
		}
	}

	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

	public List<Role> findAll() {
		return dao.findAll();
	}

	
}
