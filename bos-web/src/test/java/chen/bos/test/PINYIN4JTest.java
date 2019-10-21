package chen.bos.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.chen.bos.utils.PinYin4jUtils;

public class PINYIN4JTest {

	@Test
	public void test1(){
		String a="河北省";
		String b="石家庄市";
		String c="桥西区";
		//生成简码
		a=a.substring(0, a.length()-1);
		b=b.substring(0, b.length()-1);
		c=c.substring(0, c.length()-1);
		String info=a+b+c;
		System.out.println(info);
		String[] strings = PinYin4jUtils.getHeadByString(info);
		String join = StringUtils.join(strings);
		System.out.println(join);
		//生成城市编码
		String string = PinYin4jUtils.hanziToPinyin(b, "");
		System.out.println(string);
	}
}
