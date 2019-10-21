package com.chen.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chen.bos.dao.RegionDao;
import com.chen.bos.entity.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao{

	public List<Region> findByQ(String q) {
		String hql="from Region r where r.shortcode like ? or r.citycode like ? or r.province like ? or r.city like ? or r.district like ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}

}
