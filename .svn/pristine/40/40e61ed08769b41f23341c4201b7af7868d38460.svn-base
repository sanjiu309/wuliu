package com.chen.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.StaffDao;
import com.chen.bos.entity.Staff;
import com.chen.bos.service.StaffService;
import com.chen.bos.utils.PageBean;
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao dao;
	
	public void save(Staff model) {
		dao.save(model);
	}

	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

	/**
	 * 取派员批量删除
	 */
	public void deleteBetch(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] split = ids.split(",");
			for(String s : split){
				dao.executeUpdate("staff.delete", s);
			}
		}
	}

	public void updateStaff(Staff model) {
		dao.executeUpdate("staff.update",model.getName(),model.getTelephone(),model.getHaspda()
				,model.getStation(),model.getStandard(),model.getId());
	}

	public List<Staff> select() {
		DetachedCriteria dc=DetachedCriteria.forClass(Staff.class);
		dc.add(Restrictions.eq("deltag", "0"));
		List<Staff> list=dao.findByCriteria(dc);
		return list;
	}

}
