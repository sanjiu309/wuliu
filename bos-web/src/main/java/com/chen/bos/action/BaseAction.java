package com.chen.bos.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.chen.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 表现层通用实现
 * @author 陈
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	//模型对象
	protected T model;
	
	//分页参数
	protected PageBean pageBean=new PageBean();	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}
	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	protected DetachedCriteria dc=null;
	
	//在构造方法中动态获取实体类型，通过反射创建model对象
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		Class<T> clazz=(Class<T>) types[0];
		//分页离线对象
		dc=DetachedCriteria.forClass(clazz);
		pageBean.setDc(dc);
		try {
			 model=clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 将指定java对象除去哪些属性转为Json,并输出响应到客户端
	 * @param o
	 * @param strings
	 * @throws IOException
	 */
	public void codeToJson(Object o,String[] strings) {
		//设置转换时的参数
		JsonConfig config=new JsonConfig();
		config.setExcludes(strings);
		//将对象转为json
		String json = JSONObject.fromObject(o,config).toString();
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将指定集合除去哪些属性转为Json,并输出响应到客户端
	 * @param list
	 * @param strings
	 */
	public void codeToJson(List list,String[] strings) {
		//设置转换时的参数
		JsonConfig config=new JsonConfig();
		config.setExcludes(strings);
		//将对象转为json
		String json = JSONArray.fromObject(list,config).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public T getModel() {
		return model;
	}
	
}
