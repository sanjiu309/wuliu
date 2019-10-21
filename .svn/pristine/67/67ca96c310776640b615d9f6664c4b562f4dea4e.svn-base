package chen.bos.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;
/**
 * 使用POI解析Excel
 * @author 陈
 *
 */
public class POITest {

	@Test
	public void Test1() throws Exception{
		String path="G:\\视频\\黑马JAVA\\10_物流BOS项目\\BOS-day05\\BOS-day05\\BOS-day05\\资料\\区域导入测试数据.xls";
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(path)));
		//获得第一个Sheet页的内容
		HSSFSheet hssfSheet = workbook.getSheetAt(0);
		//获取每一行
		for (Row row : hssfSheet) {
			//如果是第一行结束本次循环
			if(row.getRowNum()==0){
				continue;
			}
			//获得每一个单元格的值
			for (Cell cell : row) {
				String value = cell.getStringCellValue();
				System.out.print(value +"  ");
			}
			System.out.println();
		}
	}
}
