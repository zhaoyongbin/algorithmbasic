package com.example.java.jxl.test.bean;

import java.util.List;

/**
 * ����������װÿһҳ������
 * 
 * @author foxlee1024
 */
public class Page {

	/**
	 * ҳ����Ϣ
	 */
	private String sheetName; // ÿ��sheet����
	private String currentPage; // ��ǰҳ
	private String tolalPage; // ��ҳ

	/**
	 * ҳ����������� List �ķ����������ã�����������ݶ�����ͬһ�����д�Ǹ��࣬ 
	 * ����ͬһ�����м̳о�д�̳���ķ��ͣ�û�о�д�ʺš�
	 */
	private List<?> data;
	
	/**
	 * һҳֻ����һ���˵���Ϣ
	 */
	private Object onlyOne;

	
	public Page(String sheetName, String currentPage, String tolalPage, List<?> data) {
		super();
		this.sheetName = sheetName;
		this.currentPage = currentPage;
		this.tolalPage = tolalPage;
		this.data = data;
	}
	
	public Page() {
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getTolalPage() {
		return tolalPage;
	}

	public void setTolalPage(String tolalPage) {
		this.tolalPage = tolalPage;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Object getOnlyOne() {
		return onlyOne;
	}

	public void setOnlyOne(Object onlyOne) {
		this.onlyOne = onlyOne;
	}
}
