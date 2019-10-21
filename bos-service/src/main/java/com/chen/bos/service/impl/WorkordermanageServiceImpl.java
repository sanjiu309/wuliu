package com.chen.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.WorkordermanageDao;
import com.chen.bos.entity.Workordermanage;
import com.chen.bos.service.WorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements WorkordermanageService{

	@Autowired
	private WorkordermanageDao dao;
	
	public void save(Workordermanage model) {
		dao.save(model);
	}

}
