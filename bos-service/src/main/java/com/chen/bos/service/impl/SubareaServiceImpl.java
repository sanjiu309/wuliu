package com.chen.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.SubareaDao;
import com.chen.bos.entity.Subarea;
import com.chen.bos.service.SubareaService;
import com.chen.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService{

	@Autowired
	private SubareaDao dao;
	
	public void add(Subarea model) {
		dao.save(model);
	}

	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

	public List<Subarea> findAll() {
		List<Subarea> list = dao.findAll();
		return list;
	}

	public List<Subarea> findNotGive() {
		DetachedCriteria dc=DetachedCriteria.forClass(Subarea.class);
		//添加过滤条件，分区对象中decidedzone对象为null
		dc.add(Restrictions.isNull("decidedzone"));
		List<Subarea> list = dao.findByCriteria(dc);
		return list;
	}

	public List<Subarea> relationDecide(String decidedzoneId) {
		DetachedCriteria dc=DetachedCriteria.forClass(Subarea.class);
		//上一次使用别名进行查询是因为在分区表中没有区域表的省市区字段，才需要使用别名
		//本次查询，分区表中有定区表的id外键，所以不需要别名
		dc.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		List<Subarea> list = dao.findByCriteria(dc);
		return list;
	}

	public List<Object> findSubareasByPro() {
		return dao.findSubareasByPro();
	}
}
