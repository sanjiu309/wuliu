package com.chen.bos.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.chen.bos.dao.IBaseDao;
import com.chen.bos.utils.PageBean;
/**
 * 持久层通用实现
 * @author 陈
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	//运行时类型实现了泛型的类对象
	private Class<T> clazz;
	//等待子类调用构造方法
	public BaseDaoImpl() {
		//this代表子类，获取它的类对象，再获取父类type,强转为ParameterizedType代表父类的泛型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类对象声明的泛型数组
		Type[] types = type.getActualTypeArguments();
		//获得泛型
		clazz=(Class<T>) types[0];
	}
	
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	public T findById(Serializable serializable) {
		return getHibernateTemplate().get(clazz,serializable);
	}

	public List<T> findAll() {
		String hql="from "+clazz.getSimpleName();
		return (List<T>) getHibernateTemplate().find(hql);
	}

	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		//为HQL语句赋值
		int i=0;
		for(Object object:objects){
			query.setParameter(i++, object);
		}
		query.executeUpdate();
	}

	//分页查询
	public void pageQuery(PageBean pageBean) {
		//页面和Action已封装
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria dc = pageBean.getDc();
		//查询total
		dc.setProjection(Projections.rowCount());
		List<Long> couList = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		if(couList!=null&&couList.size()>0){
			pageBean.setTotal(couList.get(0).intValue());
		}
		dc.setProjection(null);
		//指定hibernate封装对象的方式
		dc.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		//查询分页中数据rows
		List rows = getHibernateTemplate().findByCriteria(dc, (currentPage-1)*pageSize, pageSize);
		pageBean.setRows(rows);
	}

	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	public List<T> findByCriteria(DetachedCriteria dc) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}

}
