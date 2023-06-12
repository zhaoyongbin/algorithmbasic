package com.example.java.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Component
public class PoiExcelUtil {
    private static final Logger logger =  LoggerFactory.getLogger(PoiExcelUtil.class);
    //默认日期格式
    private static final String DEFAULT_DATE_FORMA = "yyyy-MM-dd";
    //是否为数字正则表达式
    public static final String REGEX_NUMBER = "^\\d+(\\.\\d+)?$";
    //创建excel元素样式处理类
    private ExcelStyleCreate excelStyleCreate;

    //构造方法
    public PoiExcelUtil() {
        this.excelStyleCreate = getExcelStyleCreate();
    }

    /**
     * 获取生成 excel元素样式的处理类
     *
     * @return
     */
    public ExcelStyleCreate getExcelStyleCreate() {
        if (this.excelStyleCreate == null) {
            return new DefaultExcelStyleCreate();
        }
        return excelStyleCreate;
    }

    /**
     * 获取excel 表头信息 以集合形式返回
     *
     * @param in         excel 文件流输入
     * @param SheetAtNo  excel sheet 的序号，默认从0开始
     * @param headerLine 表头行的序号 eg:若首行为标题行，则表头行 序号为1 ；若首行就是表头 则表头序号为0
     * @return
     */
    public List<String> getHeader(InputStream in, int SheetAtNo, int headerLine) {
        List<String> headers = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(in);
            int sheetNumber = workbook.getNumberOfSheets();
            if (SheetAtNo >= sheetNumber) {
                logger.warn("指定的sheetNumber不存在");
                throw new IllegalArgumentException("指定的sheetNumber不存在");
            }
            Sheet sheet = workbook.getSheetAt(SheetAtNo);
            Row row = sheet.getRow(headerLine);
            if (null != row) {
                int cells = row.getPhysicalNumberOfCells();
                for (int rowIndex = 0; rowIndex < cells; rowIndex++) {
                    Cell cell = row.getCell(rowIndex);
                    if (null != cell && !StringUtils.isEmpty(cell.getStringCellValue())) {
                        headers.add(cell.getStringCellValue());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(workbook);
        }
        return headers;
    }


    /**
     * 取excel 指定行【startLine endLine】 之间的数据，以字符串集合的方式返回
     *
     * @param in              excel 文件输入流
     * @param SheetAtNo       excel sheet 的序号 ，默认从0开始
     * @param startLineNumber 起始行
     * @param endLineNumber   结束行
     * @param igonreEmptCell  是否忽略空的单元格
     * @return
     */
    public List<List<String>> getInfo(InputStream in, int SheetAtNo, int startLineNumber, int endLineNumber, boolean igonreEmptCell) {
        List<List<String>> rowInfoList = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(in);
            int sheeNumber = workbook.getNumberOfSheets();
            if (SheetAtNo <= sheeNumber) {
                throw new IllegalArgumentException("指定的sheetNumber不存在");
            }
            Sheet sheet = workbook.getSheetAt(SheetAtNo);
            for (int rowIndex = startLineNumber; rowIndex <= endLineNumber; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (null != row) {
                    List<String> content = new ArrayList<>();
                    int cells = row.getPhysicalNumberOfCells();
                    for (int cellIndex = 0; cellIndex < cells; cellIndex++) {
                        Cell cell = row.getCell(cellIndex);
                        if (null != cell && (!StringUtils.isEmpty(cell.getStringCellValue()) || !igonreEmptCell)) {
                            content.add(cell.getStringCellValue());
                        }

                    }
                    if (!CollectionUtils.isEmpty(content)) {
                        rowInfoList.add(content);
                    }

                }
            }

        } catch (Exception e) {
            logger.warn("获取excel表头信息失败");

            e.printStackTrace();
            //exceptionUtil()
        }
        return rowInfoList;
    }

    /**
     * EXCEL 数据解析
     *
     * @param is            excel 问价输入流
     * @param SheetAtNo     指定读取第几张sheet 默认下标从0开始
     * @param clz           指定的javaBean
     * @param impFields     导入的属性名 String[]
     * @param firstDataLine 这是数据的其实行号 eg:若首行为标题行 则数据其实行号为2 若无标题行 则数据的其实行号为1
     * @param <T>
     * @return list<object>
     * </>
     */
    public <T> List<T> parse(InputStream is, int SheetAtNo, Class<T> clz, String[] impFields, int firstDataLine) {
        return parse(is, SheetAtNo, clz, impFields, firstDataLine, DEFAULT_DATE_FORMA);
    }

    /**
     * excel 数据解析
     * @param is excel 文件输入流
     * @param SheetAtNo 指定读第几张sheet 默认下从0开始
     * @param clz 指定的JavaBean
     * @param impFields 导入的属性名 String[]
     * @param firstDataLine 真是
     * @param dateFormat
     * @param <T>
     * @return
     */
    public <T> List<T> parse(InputStream is, int SheetAtNo, Class<T> clz, String[] impFields, int firstDataLine, String dateFormat) {
        List<T> dist = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            int sheetNumber = workbook.getNumberOfSheets();
            if (SheetAtNo >= sheetNumber) {
                logger.warn("指定的sheetNumber不存在");
                throw new IllegalArgumentException("指定的sheetNumber不存在");
            }
            Sheet sheet = workbook.getSheetAt(SheetAtNo);
            int physicalNumberOfRows = sheet.getLastRowNum();
            int cellCount = impFields.length;
            for (int i = firstDataLine; i <= physicalNumberOfRows; i++) {
                Row row = sheet.getRow(i);
                if (null != row) {
                    T bean = ReflectUtils.getNewInstance(clz);
                    Object[] objValues = new Object[impFields.length];
                    for (int m = 0; m < cellCount; m++) {
                        Cell cell = row.getCell(m);
                        if (null != cell) {
                            Field field = ReflectUtils.getDeclaredField(bean, impFields[m]);
                            ReflectUtils.matchType(field, match(cell, dateFormat));

                        }

                    }

                    if (isEmptyRow(objValues, i)) {
                        continue;
                    }
                    ReflectUtils.invokeSetterMethod(bean, impFields, objValues);
                    dist.add(bean);
                }
            }


        } catch (Exception e) {
            logger.warn("excel文件导入出错", e);
            //exceptionUtils.runtimeException
        }finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(workbook);
        }

        return null;
    }

    /**
     * 判断某一行是否都时null 值 【excel 文件本身传null值 或者都是类型转换异常导致出现null 值】
     * @param objValues
     * @param rowIndex
     * @return
     */
    private boolean isEmptyRow(Object[] objValues, int rowIndex) {
        if(objValues==null){
            return  true;
        }
        for(int i=0;i<objValues.length;){
            if(objValues[i]!=null&&!"".equals(objValues[i].toString())){
                return false;
            }
        }
        logger.warn("excel导入，第{}行单元格都是null或则和都是空字符串，可能是因为当前行是最后一行的空行，或者因为每个单元格的类型都不匹配导致的，排除这一行数据",rowIndex);
        return true;
    }

    private static String match(Cell cell, String dateFormat) {
        String value = "";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        if (cell != null) {
            switch (cell.getCellType()) {
                case BLANK:
                    value = "";
                    break;
                case _NONE:
                    value = "";
                    break;
                case STRING:
                    value = cell.getRichStringCellValue().toString();
                    break;
                case FORMULA:
                    value = cell.getCellFormula().toString();
                    break;
                case BOOLEAN:
                    value = cell.getCellFormula();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        value = sdf.format(cell.getDateCellValue());
                    } else {
                        double d = cell.getNumericCellValue();
                        DecimalFormat df = new DecimalFormat("#.####################");
                        value = df.format(d);
                    }
                    break;

            }

        }
        return value;

    }


}
