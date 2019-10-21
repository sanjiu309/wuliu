package com.chen.bos.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.Workordermanage;
import com.chen.bos.service.WorkordermanageService;
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage>{

	@Autowired
	private WorkordermanageService service;
	
	public String add() throws Exception {
		String f="1";
		try {
			service.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			f="0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(f);
		return NONE;
	}
}
