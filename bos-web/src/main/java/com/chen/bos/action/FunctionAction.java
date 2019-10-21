package com.chen.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.Function;
import com.chen.bos.service.FunctionService;
/**
 * 权限Action
 * @author 陈
 *
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{

	@Autowired
	private FunctionService service;
	
	/**
	 * 根据权限级别查询出树形结构的Json
	 */
	public String treeList() throws Exception {
		List<Function> list=service.findAll();
		codeToJson(list, new String[]{"parentFunction","roles",""});
		return NONE;
	}
	
	/**
	 * 添加权限
	 */
	public String add() throws Exception {
		service.add(model);
		return "list";
	}
	
	/**
	 * 分页查询
	 */
	public String pageQuery() throws Exception {
		//Function对象的page属性和分页的page属性名字相同
		pageBean.setCurrentPage(Integer.parseInt(model.getPage()));
		service.pageQuery(pageBean);
		codeToJson(pageBean, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	
	/**
	 * 根据当前登陆用户查询对应权限的菜单
	 */
	public String findMenu() throws Exception {
		List<Function> list=service.findMenu();
		codeToJson(list, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
}
