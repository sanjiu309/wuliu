package com.chen.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chen.bos.dao.SubareaDao;
import com.chen.bos.entity.Subarea;
@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements SubareaDao{

	public List<Object> findSubareasByPro() {
		String sql="select r.province,count(*) from Subarea s left outer join s.region r group by r.province";
		return (List<Object>) getHibernateTemplate().find(sql);
	}

}
