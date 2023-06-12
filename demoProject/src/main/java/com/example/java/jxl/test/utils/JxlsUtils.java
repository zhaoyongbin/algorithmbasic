package com.example.java.jxl.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;

/**
 * 
 * @author klguang
 *
 */
public class JxlsUtils{
	
	public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException{
        Context context = PoiTransformer.createInitialContext();
        if (model != null) {
            for (String key : model.keySet()) {
                context.putVar(key, model.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer  = jxlsHelper.  createTransformer(is, os);
        //�������
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
        //���þ�Ĭģʽ����������
//        evaluator.getJexlEngine().setSilent(true);
        //����ǿ�ƣ��Զ��幦��
        Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("utils", new JxlsUtils());    //����Զ��幦��
//        evaluator.getJexlEngine().setFunctions(funcs);
        //����Ҫ��������߱����ͳ�ƻ����
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
        //��ûʲô����
        //jxlsHelper.setDeleteTemplateSheet(true);
	}

    public static void exportExcel(File xls, File out, Map<String, Object> model) throws FileNotFoundException, IOException {
            exportExcel(new FileInputStream(xls), new FileOutputStream(out), model);
    }
    
    public static void exportExcel(String templatePath, OutputStream os, Map<String, Object> model) throws Exception {
    	File template = getTemplate(templatePath);
    	if(template != null){
        	exportExcel(new FileInputStream(template), os, model);	
    	} else {
    		throw new Exception("Excel ģ��δ�ҵ���");
    	}
    }
    
    //��ȡjxlsģ���ļ�
    public static File getTemplate(String path){
        File template = new File(path);
        if(template.exists()){
            return template;
        }
        return null;
    }	
	
    // ���ڸ�ʽ��
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
            return dateFmt.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    // if�ж�
    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }
    
}