package com.example.java.jxl.test.Main;

import com.example.java.jxl.test.bean.Page;
import com.example.java.jxl.test.bean.Student;
import com.example.java.jxl.test.utils.DataByPage;
import com.example.java.jxl.test.utils.DelSheet;
import com.example.java.jxl.test.utils.JxlsUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TestMain {

	public static void main(String[] args) throws Exception {
		DataByPage as=new DataByPage();
		// ģ��λ�ã������
//		String templatePath =ClassLoader.getSystemClassLoader().getResource("templates/template5.xls").getPath() ;
		String templatePath ="e:/template5.xls";
		OutputStream os = new FileOutputStream("E:/out5zhaoyongbin.xls");

		List<Student> list = generateData(); //	ģ�����ݿ��ȡ����
		List<Page> page = DataByPage.byPage(list); // �ѻ�ȡ�����ݽ��з�ҳת��
//		List<Page> page = individual(list); // һҳһ����
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pages", page);
		model.put("sheetNames", getSheetName(page));
		model.put("className", "六年三班");
		model.put("teacherComment", "已核实");
		model.put("directorComment", "已核实");
		
		JxlsUtils.exportExcel(templatePath, os, model);
		os.close();
		// ɾ���������sheet
//		DelSheet.deleteSheet("E:/out5_2.xls", "template");
		System.out.println("���");
	}
	
	/**
	 * Excel �ķ�ҳ����ҳ�룩�ķ�װ
	 * �˷���������ȡ�ֺ�ҳ��ҳ����Ϣ������Ϣ����һ�������з���
	 */
	public static ArrayList<String> getSheetName(List<Page> page) {
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < page.size(); i++) {
			al.add(page.get(i).getSheetName());
		}
		return al;
	}
	
	/**
	 * ģ����������
	 */
	public static List<Student> generateData(){
		
		List<Student> list = new ArrayList<Student>();
		Student stu1 = new Student("001", "AAA", 10, 20, 30, 40, 50, 60);
		Student stu2 = new Student("002", "BBB", 20, 30, 40, 50, 60, 70);
		Student stu3 = new Student("003", "CCC", 30, 40, 50, 60, 70, 80);
		Student stu4 = new Student("004", "DDD", 40, 50, 60, 70, 80, 90);
		Student stu5 = new Student("005", "EEE", 50, 60, 70, 80, 90, 100);
		list.add(stu1);
		list.add(stu2);
		list.add(stu3);
		list.add(stu4);
		list.add(stu5);
		
		return list;
	}
	
	/**
	 * �����ݻ�ȡ�����ݷ�װ��һҳһ���˵�List
	 */
	public static List<Page> individual(List<Student> list){
		
		List<Page> pages = new ArrayList<Page>();
		for(int i = 0; i < list.size(); i++){
			Page p = new Page();
			p.setOnlyOne(list.get(i));
			p.setSheetName(list.get(i).getName());
			pages.add(p);
		}
		
		return pages;
	}

}
