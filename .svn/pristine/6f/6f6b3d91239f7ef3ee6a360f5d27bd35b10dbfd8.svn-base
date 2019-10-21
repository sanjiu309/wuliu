package com.chen.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.chen.bos.utils.PageBean;

/**
 * 持久层通用接口
 * @author 陈
 *
 * @param <T>
 */
public interface IBaseDao<T> {

	public void save(T t);
	public void update(T t);
	public void delete(T t);
	public T findById(Serializable serializable);
	public List<T> findAll();
	public void executeUpdate(String queryName,Object... objects);
	public void pageQuery(PageBean pageBean);
	public void saveOrUpdate(T t);
	public List<T> findByCriteria(DetachedCriteria dc);
}
