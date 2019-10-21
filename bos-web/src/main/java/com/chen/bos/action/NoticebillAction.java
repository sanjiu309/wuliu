package com.chen.bos.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.Noticebill;
import com.chen.bos.service.NoticebillService;
import com.chen.crm.Customer;
import com.chen.crm.CustomerService;
/**
 * 业务通知单Action
 * @author 陈
 *
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{

	@Autowired
	private NoticebillService service;
	
	/**
	 * 添加新的业务通知单,并自动分单
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		service.save(model);
		return "noticebill_add";
	}
	
	@Autowired
	private CustomerService proxy;
	//根据手机号调用proxy
	public String findCustByPhone() throws Exception {
		Customer customer = proxy.getCustByPhone(model.getTelephone());
		codeToJson(customer, new String[]{});
		return NONE;
	}
}
