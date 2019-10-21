package com.chen.bos.action;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.Region;
import com.chen.bos.entity.Subarea;
import com.chen.bos.service.SubareaService;
import com.chen.bos.utils.FileUtils;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{

	@Autowired
	private SubareaService service;
	
	/**
	 * 添加分区
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("add-subarea")
	public String add() throws Exception {
		service.add(model);
		return "toList";
	}
	
	/**
	 * 分页查询
	 */
	public String pageQuery() throws Exception {
		//动态添加过滤条件查询数据
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)){
			dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		Region region = model.getRegion();
		if(region!=null){
			//因为region属于region表，所有需要使用多表查询,添加一个表的别名
			//参数一是Subarea中Region属性的属性名，参数二是别名的值
			dc.createAlias("region", "r");
			String province = region.getProvince();
			if(StringUtils.isNotBlank(province)){
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			String city = region.getCity();
			if(StringUtils.isNotBlank(city)){
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			String district = region.getDistrict();
			if(StringUtils.isNotBlank(district)){
				dc.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		service.pageQuery(pageBean);
		codeToJson(pageBean, new String[]{"currentPage","dc","pageSize","decidedzone","subareas"});
		return NONE;
	}
	
	/**
	 * 分区数据导出功能
	 */
	public String exportXls() throws Exception {
		//查出所有数据
		List<Subarea> list=service.findAll();
		//使用poi将数据写入一个excel文件
		//在内存中创建一个excel文件
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建标题行
		HSSFRow titleRow = sheet.createRow(0);
		titleRow.createCell(0).setCellValue("分区编号");
		titleRow.createCell(1).setCellValue("开始编号");
		titleRow.createCell(2).setCellValue("结束编号");
		titleRow.createCell(3).setCellValue("位置信息");
		titleRow.createCell(4).setCellValue("省市区");
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		//第三步，使用输出流进行文件下载(一个流 两个头)
		//使用servletContext获取以文件后缀的contentType
		String filename="分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		//获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		//使用工具类编码
		filename=FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		workbook.write(outputStream);
		return NONE;
	}
	
	/**
	 * 查询所有未分配的分区
	 */
	public String selectNotGive() throws Exception {
		List<Subarea> list=service.findNotGive();
		codeToJson(list, new String[]{"region","decidedzone"});
		return NONE;
	}
	
	private String decidedzoneId;
	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}
	/**
	 * 查询定区关联的分区
	 * @param subareaid
	 */
	public String relationDecide() throws Exception {
		List<Subarea> list=service.relationDecide(decidedzoneId);
		codeToJson(list, new String[]{"decidedzone","subareas"});
		return NONE;
	}
	
	/**
	 * 查询每个省份下的分区
	 */
	public String findSubareasByPro() throws Exception {
		List<Object> list=service.findSubareasByPro();
		codeToJson(list, new String[]{});
		return NONE;
	}
}
