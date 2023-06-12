package com.example.java.jxl.test.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.java.jxl.test.bean.Page;

/**
 * �������ڷ�ҳ�����ǰѴ����ݿ��ѯ������һ��������List������һ��һ�������ݡ�
 * @author foxlee1024
 */
public class DataByPage {

	static int pagesize = 3; // ÿҳ��¼��
	
	/**
	 * ����ÿҳ��ʾ���������ݼ�����ҳ��
	 * @param dataList ���ݿ��ѯ������
	 */
	public static int countPages(List<?> dataList) {
		int recordcount = dataList.size(); // �ܼ�¼��
		return (recordcount + pagesize - 1) / pagesize;
	}
	
	public static List<Page> byPage(List<?> dataList) {
		int pagecount; // ��ҳ��
		int nowDataListPoint = 0; // ��ȡ�����յ���һ������

		pagecount = countPages(dataList); // ����ҳ��
		List<Page> pageList = new ArrayList<Page>(); // ҳ���ҳ
		for (int i = 0; i < pagecount; i++) {

			List<Object> pagedata = new ArrayList<Object>();
			// �Ѵ���������ȡ��
			while (nowDataListPoint < dataList.size()) {
				pagedata.add(dataList.get(nowDataListPoint));
				nowDataListPoint += 1;
				if (nowDataListPoint != 0 && nowDataListPoint % pagesize == 0) {
					break;
				}
			}
			Page page = new Page("page_" + (i + 1), (i + 1) + "", pagecount + "", pagedata);
			pageList.add(page);
		}
		return pageList;
	}
}
