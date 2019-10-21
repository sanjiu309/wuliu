package com.chen.bos.action;

import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.Staff;
import com.chen.bos.service.StaffService;
import com.chen.bos.utils.PageBean;
import com.mysql.fabric.xmlrpc.base.Array;
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{

	@Autowired
	private StaffService service;
	
	/**
	 * 添加取派员
	 */
	public String add() throws Exception {
		service.save(model);
		return "toList";
	}
	
	
	/**
	 * 取派员列表
	 * @return
	 * @throws Exception
	 */
	public String pageQuery() throws Exception {
		service.pageQuery(pageBean);
		this.codeToJson(pageBean, new String[]{"currentPage","pageSize","dc","decidedzones"});
		return NONE;
	}
	
	//批量删除的id
	private String ids;
	/**
	 * 批量删除
	 * @return
	 */
	@RequiresPermissions("staff-delete")//执行这个方法需要当前用户具有该权限
	public String delete() throws Exception {
		service.deleteBetch(ids);
		return "toList";
	}
	
	/**
	 * 修改取派员
	 * @return
	 */
	public String edit() throws Exception {
		/*
		 * 不可以直接update参数封装的对象，这样参数没有封装的属性都是null，都被修改了
		 * 这里我使用executeUpdate方法，在orm元数据中书写update语句需要更新的字段是
		 * 参数对象中封装的属性，在service中按照顺序传参
		 * 也可以先根据参数封装的对象的id查询数据库获得要修改的对象，再利用参数对象覆盖
		 * 查询出来的对象的属性值，在执行普通update方法
		 */
		service.updateStaff(model);
		return "toList";
	}

	/**
	 * 查询所有未离职的取派员
	 */
	public String select() throws Exception {
		List<Staff> list=service.select();
		codeToJson(list, new String[]{"decidedzones"});
		return NONE;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
}
