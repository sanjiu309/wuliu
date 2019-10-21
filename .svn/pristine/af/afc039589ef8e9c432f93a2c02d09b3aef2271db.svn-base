package com.chen.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bos.dao.DecidedzoneDao;
import com.chen.bos.dao.NoticebillDao;
import com.chen.bos.dao.WorkbillDao;
import com.chen.bos.entity.Decidedzone;
import com.chen.bos.entity.Noticebill;
import com.chen.bos.entity.Staff;
import com.chen.bos.entity.User;
import com.chen.bos.entity.Workbill;
import com.chen.bos.service.NoticebillService;
import com.chen.bos.utils.BosUtils;
import com.chen.crm.CustomerService;
@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService{

	@Autowired
	private NoticebillDao dao;
	@Autowired
	private CustomerService proxy;
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private WorkbillDao workbillDao;
	
	/**
	 * 添加新的业务通知单,并尝试自动分单
	 */
	public void save(Noticebill model) {
		User user = BosUtils.getLoginUser();
		model.setUser(user);//设置关联的用户
		dao.save(model);
		
		//获取客户的地址
		String address = model.getPickaddress();
		//远程调用crm查询定区id
		String decidedzoneId = proxy.getDecidedzoneIdByAddress(address);
		if(decidedzoneId!=null){
			//查询到了定区id，可以自动分单
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			Staff staff = decidedzone.getStaff();
			//让业务通知单关联取派员
			model.setStaff(staff);
			//设置分单类型为自动
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			
			//为取派员分配一个工单
			Workbill workbill=new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//创建时间
			workbill.setNoticebill(model);//工单区关联业务通知单
			workbill.setPickstate(workbill.PICKSTATE_NO);//取件状态
			workbill.setRemark(model.getRemark());//备注
			workbill.setStaff(staff);//关联取派员
			workbill.setType(workbill.TYPE_1);//工单类型
			//保存工单
			workbillDao.save(workbill);
			//调用短信平台，发送短信
		}else{
			//不能自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}
}
