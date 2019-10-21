package com.chen.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.FunctionDao;
import com.chen.bos.entity.Function;
import com.chen.bos.entity.User;
import com.chen.bos.service.FunctionService;
import com.chen.bos.utils.BosUtils;
import com.chen.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService{

	@Autowired
	private FunctionDao dao;

	public List<Function> findAll() {
		List<Function> list = dao.findAll();
		return list;
	}

	public void add(Function model) {
		//因为当前权限可以不选择父权限，但是表单提交上来的父权限id是空字符串，会报错
		if(model.getParentFunction()!=null && "".equals(model.getParentFunction().getId())){
			model.setParentFunction(null);
		}
		dao.save(model);
	}

	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

	public List<Function> findMenu() {
		User user = BosUtils.getLoginUser();
		List<Function> list=null;
		if("admin".equals(user.getUsername())){
			list=dao.findAllMenu();
		}else{
			list=dao.findMenuByUser(user.getId());
		}
		return list;
	}
	
}
