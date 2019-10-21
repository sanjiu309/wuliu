package com.chen.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.RegionDao;
import com.chen.bos.entity.Region;
import com.chen.bos.service.RegionService;
import com.chen.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements RegionService{

	@Autowired
	private RegionDao dao;

	/**
	 * 区域数据批量保存
	 */
	public void saveRegion(List<Region> list) {
		for (Region region : list) {
			dao.saveOrUpdate(region);
		}
	}

	/**
	 * 区域分页查询
	 */
	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

	/**
	 * 查询所有区域
	 */
	public List<Region> findAll() {
		return dao.findAll();
	}

	/**
	 * 根据参数模糊查询
	 */
	public List<Region> findByQ(String q) {
		List<Region> list = dao.findByQ(q);
		return list;
	}
	
	
}
