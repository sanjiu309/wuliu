package com.chen.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chen.bos.entity.Region;
import com.chen.bos.service.RegionService;
import com.chen.bos.utils.PageBean;
import com.chen.bos.utils.PinYin4jUtils;
/**
 * 区域管理
 * @author 陈
 *
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{

	@Autowired
	private RegionService service;
	
	//通过属性驱动接收上传文件
	private File regionFile;
	/**
	 * 区域文件导入.xls
	 */
	public String importFile() throws Exception {
		List<Region> list=new ArrayList<Region>();
		//使用POI解析Excel
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet hssfSheet = workbook.getSheetAt(0);
		for (Row row : hssfSheet) {
			if(row.getRowNum()==0){
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city=row.getCell(2).getStringCellValue();
			String district=row.getCell(3).getStringCellValue();
			String postcode=row.getCell(4).getStringCellValue();
			
			Region region=new Region(id, province, city, district, postcode,null,null,null);
			//需要在构造方法后使用pinyin4j获取简码和编码
			province=province.substring(0, province.length()-1);
			city=city.substring(0, city.length()-1);
			district=district.substring(0, district.length()-1);
			String info=province+city+district;
			String[] strings = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(strings);
			//生成城市编码
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			list.add(region);
		}
		service.saveRegion(list);
		return null;
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String pageQuery() throws Exception {
		service.pageQuery(pageBean);
		this.codeToJson(pageBean, new String[]{"currentPage","pageSize","dc","subareas"});
		return NONE;
	}
	
	//下拉选中mode:'remote'每次输入的值
	private String q;
	/**
	 * 查询所有区域
	 * @param regionFile
	 */
	public String list() throws Exception {
		List<Region> list=null;
		if(StringUtils.isNotBlank(q)){
			list=service.findByQ(q);
		}else{
			list=service.findAll();
		}
		this.codeToJson(list, new String[]{"postcode","shortcode","citycode","subareas"});
		return NONE;
	}
	
	
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	public void setQ(String q) {
		this.q = q;
	}
}
