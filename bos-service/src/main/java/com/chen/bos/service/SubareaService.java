package com.chen.bos.service;

import java.util.List;

import com.chen.bos.entity.Subarea;
import com.chen.bos.utils.PageBean;

public interface SubareaService {

	void add(Subarea model);

	void pageQuery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> findNotGive();

	List<Subarea> relationDecide(String decidedzoneId);

	List<Object> findSubareasByPro();

}
