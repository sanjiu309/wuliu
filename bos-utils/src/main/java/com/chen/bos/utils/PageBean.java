package com.chen.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 分页工具类
 * @author 陈
 *
 */
public class PageBean {

	private int currentPage;//当前页码
	private int pageSize;//每页显示量
	private int total;//总记录数
	private DetachedCriteria dc;//离线查询
	private List rows;//数据
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public DetachedCriteria getDc() {
		return dc;
	}
	public void setDc(DetachedCriteria dc) {
		this.dc = dc;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
