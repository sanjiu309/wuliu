package com.chen.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.Role;
import com.chen.bos.service.RoleService;
/**
 * 角色关联
 * @author 陈
 *
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	private String functionIds;
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	@Autowired
	private RoleService service;
	
	public String add() throws Exception {
		service.save(model,functionIds);
		return "list";
	}
	
	/**
	 * 分页查询
	 */
	public String pageQuery() throws Exception {
		service.pageQuery(pageBean);
		codeToJson(pageBean, new String[]{"functions","users"});
		return NONE;
	}
	
	public String listAjax() throws Exception {
		List<Role> list=service.findAll();
		codeToJson(list, new String[]{"functions","users"});
		return NONE;
	}
}
