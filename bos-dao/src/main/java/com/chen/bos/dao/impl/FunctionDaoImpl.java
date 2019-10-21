package com.chen.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chen.bos.dao.FunctionDao;
import com.chen.bos.entity.Function;
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao{

	public List<Function> findAll() {
		String hql="from Function where parentFunction is null";
		List<Function> list = (List<Function>) getHibernateTemplate().find(hql);
		return list;
	}

	//根据用户id查询对应的权限
	public List<Function> findFunctionByUser(String userId) {
		String hql="select distinct f from Function f left outer join f.roles r left outer join r.users u where u.id=?";
		List<Function> list = (List<Function>) getHibernateTemplate().find(hql, userId);
		return list;
	}

	//查询所有要生成菜单的权限
	public List<Function> findAllMenu() {
		String hql="from Function where generatemenu='1' order by zindex desc";
		List<Function> list = (List<Function>) getHibernateTemplate().find(hql);
		return list;
	}

	//根据用户id查询具有的生成菜单的权限
	public List<Function> findMenuByUser(String id) {
		String hql="select distinct f from Function f left outer join f.roles r left outer join r.users u where u.id=? and f.generatemenu='1' order by f.zindex";
		List<Function> list = (List<Function>) getHibernateTemplate().find(hql, id);
		return list;
	}
}
