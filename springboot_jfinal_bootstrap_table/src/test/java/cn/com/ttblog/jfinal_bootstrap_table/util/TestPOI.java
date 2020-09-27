package cn.com.ttblog.jfinal_bootstrap_table.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestPOI {

	@Test
	public void tt() throws IOException {
//		File directory = new File("");// 参数为空
//		String projectpath=directory.getAbsolutePath();
//		POIExcelUtil.exec(projectpath+File.separator+"aa.xls");
//		List<String> titles=new ArrayList<String>(){};
//		titles.add("昵称");
//		titles.add("姓名");
//		titles.add("性别");
//		List<Map<String, Object>> datas=new ArrayList<Map<String, Object>>();
//		for(int i=0;i<4;i++){
//			//保障顺序
//			Map<String, Object> mp=new LinkedHashMap<String, Object>();
//			mp.put("n","tt"+i);
//			mp.put("x","行ing"+i);
//			mp.put("b","男"+i);
//			datas.add(mp);
//		}
//		POIExcelUtil.export(titles, datas, projectpath+File.separator+"test.xls");
	}

	public void sysoutpath() throws IOException {
		// 第一种：获取类加载的根路径
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println(f);
		// 获取当前类的所在工程路径; 如果不加“/” 获取当前类的加载目录
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);

		// 第二种获取项目路径
		File directory = new File("");// 参数为空
		System.out.println(directory.getAbsolutePath());
		String courseFile = directory.getCanonicalPath();
		System.out.println(courseFile);

		// 第三种
		URL xmlpath = this.getClass().getClassLoader().getResource("");
		System.out.println(xmlpath);

		// 第四种
		System.out.println(System.getProperty("user.dir"));
		/*
		 * 获取当前工程路径
		 */
		// 第五种： 获取所有的类路径 包括jar包的路径
		System.out.println(System.getProperty("java.class.path"));
	}
}
