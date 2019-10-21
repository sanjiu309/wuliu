package com.chen.bos.service;

import java.util.List;

import com.chen.bos.entity.Function;
import com.chen.bos.utils.PageBean;

public interface FunctionService {

	List<Function> findAll();

	void add(Function model);

	void pageQuery(PageBean pageBean);

	List<Function> findMenu();

}
