package com.chen.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.Decidedzone;
import com.chen.bos.service.DecidedzoneService;
import com.chen.crm.Customer;
import com.chen.crm.CustomerService;

/**
 * 定区管理
 * @author 陈
 *
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

	@Autowired
	private DecidedzoneService service;
	@Autowired
	private CustomerService proxy;
	
	//接收多个Subareaid
	private String[] subareaid;

	/**
	 * 添加定区
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		service.save(model,subareaid);
		return "list";
	}

	/**
	 * 分页查询
	 */
	public String pageQuery() throws Exception {
		service.pageQuery(pageBean);
		codeToJson(pageBean, new String[]{"currentPage","pageSize","dc","subareas","decidedzones"});
		return NONE;
	}
	
	/**
	 * 查询未关联客户
	 * @param subareaid
	 */
	public String findNotAssociation() throws Exception {
		List<Customer> list = proxy.findNotAssociation();
		codeToJson(list, new String[]{});
		return NONE;
	}
	
	/**
	 * 查询已关联客户
	 * @param subareaid
	 */
	public String findHasAssociation() throws Exception {
		List<Customer> list = proxy.findHasAssociation(model.getId());
		codeToJson(list, new String[]{});
		return NONE;
	}
	
	private List<Integer> customerIds;
	/**
	 * 客户关联定区
	 * @param subareaid
	 */
	public String assigncustomer() throws Exception {
		proxy.customerAssignDecide(model.getId(), customerIds);
		return "list";
	}
	
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
}
