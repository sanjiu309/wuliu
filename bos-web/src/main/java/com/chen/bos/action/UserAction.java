package com.chen.bos.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.User;
import com.chen.bos.service.UserService;
import com.chen.bos.utils.BosUtils;
import com.chen.bos.utils.MD5Utils;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private String checkcode;
	
	@Autowired
	private UserService service;
	/**
	 * 用户登陆
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		//校验验证码是否正确
		String string = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.toLowerCase().equals(string.toLowerCase())){
			//使用shiro框架进行认证
			Subject subject=SecurityUtils.getSubject();//获得当前用户对象,状态为未认证
			AuthenticationToken token=new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
			try{
				subject.login(token);
			}catch(Exception e){
				e.printStackTrace();
				return "login";
			}
			//认证通过，会把对象放入Principal
			User user = (User) subject.getPrincipal();
			ActionContext.getContext().getSession().put("user", user);
			return "toHome";
		}else {
			//输入错误,输出错误信息，跳转到登陆页面
			this.addActionError("验证码错误!");
			return "login";
		}
	}
	
	/**
	 * 退出登陆
	 */
	public String logout() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String modifyPwd() throws Exception {
		String data="1";
		//获得当前登陆用户
		User user = BosUtils.getLoginUser();
		//根据当前用户的id修改密码，新密码为参数中的密码
		try {
			service.modifyPwd(user.getId(),model.getPassword());
		} catch (Exception e) {
			data="0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(data);
		return null;
	}
	
	private String[] roleIds;
	
	/**
	 * 添加用户
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		service.save(model,roleIds);
		return "list";
	}
	
	/**
	 * 用户分页查询
	 * @return
	 */
	public String pageQuery() throws Exception {
		service.pageQuery(pageBean);
		codeToJson(pageBean, new String[]{"noticebills","roles"});
		return NONE;
	}
	
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
}
