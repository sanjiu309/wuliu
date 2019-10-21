package com.chen.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.DecidedzoneDao;
import com.chen.bos.dao.SubareaDao;
import com.chen.bos.entity.Decidedzone;
import com.chen.bos.entity.Subarea;
import com.chen.bos.service.DecidedzoneService;
import com.chen.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService{

	@Autowired
	private DecidedzoneDao dao;
	@Autowired
	private SubareaDao dao2;
	
	/**
	 * 添加定区，同时关联分区
	 */
	public void save(Decidedzone model, String[] subareaid) {
		//为定区表添加数据
		dao.save(model);
		//定区关联分区
		for (String id : subareaid) {
			//一的一方放弃了维护外键的权利，只能由多方维护
			Subarea subarea = dao2.findById(id);
			//分区管理定区
			subarea.setDecidedzone(model);
		}
	}

	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

}
