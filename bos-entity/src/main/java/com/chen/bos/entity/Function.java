package com.chen.bos.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * 权限对象
 */

public class Function implements java.io.Serializable {

	// Fields
	private String id;
	private Function parentFunction;//父权限
	private String name;
	private String code;
	private String description;
	private String page;
	private String generatemenu;//是否管理菜单 1：是    0：否
	private Integer zindex;
	private Set roles = new HashSet(0);//哪些角色有该权限
	private Set children = new HashSet(0);//该权限的子权限

	//当属性第二个字母大写时，它对应的getset方法的首字母就不会大写了
	public String getpId(){
		if(parentFunction==null){
			return "0";
		}else {
			return parentFunction.getId();
		}
	}
	
	// Constructors
	
	public String getText(){
		return name;
	}
	
	/** default constructor */
	public Function() {
	}

	/** minimal constructor */
	public Function(String id) {
		this.id = id;
	}

	/** full constructor */
	public Function(String id, Function function, String name, String code,
			String description, String page, String generatemenu,
			Integer zindex, Set roles, Set functions) {
		this.id = id;
		this.parentFunction = function;
		this.name = name;
		this.code = code;
		this.description = description;
		this.page = page;
		this.generatemenu = generatemenu;
		this.zindex = zindex;
		this.roles = roles;
		this.children = functions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Function getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getGeneratemenu() {
		return generatemenu;
	}

	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}

	public Integer getZindex() {
		return zindex;
	}

	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public Set getChildren() {
		return children;
	}

	public void setChildren(Set children) {
		this.children = children;
	}
}