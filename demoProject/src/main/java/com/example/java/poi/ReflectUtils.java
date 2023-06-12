package com.example.java.poi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReflectUtils {
    private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);


    public static final <T> T getNewInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 循环向上转型 获取对象的 DeclaredField
     * 如无法向上转型到Object 仍无法找到返回 null
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Field getDeclaredField(final Object object, final String fieldName) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * 根据Field 和传入值 进行属性匹配传值
     *
     * @param type 属性类型
     * @param s    excel 单元格值
     * @return
     */
    public static Object matchType(Field type, String s) {
        Object obj = null;
        if (type.getGenericType() instanceof Class<?>) {
            Class cls = (Class<?>) type.getGenericType();
            try {
                if (String.class.isAssignableFrom(cls)) {
                    obj = String.valueOf(s).trim();
                } else if (Byte.class.isAssignableFrom(cls)) {
                    obj = Byte.valueOf(s);
                } else if (Short.class.isAssignableFrom(cls)) {
                    obj = Short.valueOf(s);
                } else if (Integer.class.isAssignableFrom(cls)) {
                    obj = Integer.valueOf(s);
                }
                ///
                else if (Integer.class.isAssignableFrom(cls)) {
                    obj = Integer.valueOf(s);
                } else if (Long.class.isAssignableFrom(cls)) {
                    obj = Long.valueOf(s);
                } else if (Float.class.isAssignableFrom(cls)) {
                    obj = Float.valueOf(s);
                } else if (Double.class.isAssignableFrom(cls)) {
                    obj = Double.valueOf(s);
                } else if (Boolean.class.isAssignableFrom(cls)) {
                    obj = Boolean.valueOf(s);
                } else if (Date.class.isAssignableFrom(cls)) {
                    obj = string2Date(s);
                } else if (BigDecimal.class.isAssignableFrom(cls)) {
                    obj = new BigDecimal(s);
                }

            } catch (Exception e) {
                logger.warn("excel导入出现单元格和属性不服符合的情形，属性名称：{}，类型为{}，值为{}，所以属性赋值为null");
                logger.warn(type.getName());//,cls ,s
                logger.warn(cls.toString());//,cls ,s
                logger.warn(s);//,cls ,s
            }
        }
        return obj;

    }

    private static Date string2Date(String dateString) {
        if (dateString == null || dateString.trim().length() == 0) {
            return null;
        }

        try {
            String strFormat = "";
            switch (dateString.length()) {
                case 6:
                    strFormat = "yyMMdd";
                    break;
                case 8:
                    strFormat = "yyyyMMdd";
                    break;
                case 10:
                    strFormat = "yyyy-MM-dd";
                    break;
                case 14:
                    strFormat = "yyyyMMddHHmmss";
                    break;
                default:
                    strFormat = "yyyy-mm-dd HH:mm:ss";

            }
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat(strFormat);
            Date timeDate = SimpleDateFormat.parse(dateString);
            return timeDate;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 用Setter 方法 使用value的Class 来查找Setter方法
     *
     * @param target
     * @param PropertyNames
     * @param values
     * @param <T>
     */


    public static <T> void invokeSetterMethod(Object target, String[] PropertyNames, Object[] values) {
        if (null == PropertyNames || null == values) {
            throw new IllegalArgumentException("the array is not empty");
        }
        if (PropertyNames.length != values.length) {
            throw new IllegalArgumentException("propertyNames.length!=values.length");

        }
        for (int i = 0; i < PropertyNames.length; i++) {
            invokeSetterMethod(target, PropertyNames[i], values[i], null);
        }
    }

    /**
     * 用于查找Setter 方法 ，为空时使用value的Class替代
     * 调用Setter 方法
     *
     * @param target
     * @param PropertyNames
     * @param values
     * @param <T>
     */
    public static <T> void invokeSetterMethod(Object target, String PropertyNames, Object values, Class propertyType) {
        if (values == null) {
            return;
        }
        Class<?> type = propertyType != null ? propertyType : values.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(PropertyNames);
        invokeMethod(target, setterMethodName, new Class[]{type}, new Object[]{values});
    }


    /**
     * 直接调用对象的方法 无视private/protected修饰符
     *
     * @param object
     * @param methodName
     * @param parameterTypes
     * @param parameters
     */
    private static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes, final Object[] parameters) {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if(method==null){
            throw new IllegalArgumentException("could not find metnod ["+method+"] parameterType"+parameterTypes+"on" +
                    "target["+object+"]");
        }
        method.setAccessible(true);
        try {
            return  method.invoke(object,parameters);
        }catch (Exception e){
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 将反射时的 checked exception 转换为 unchecked exception
     * @param e
     * @return
     */
    private static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        return convertReflectionExceptionToUnchecked(null,e);
    }


    private static RuntimeException convertReflectionExceptionToUnchecked(String desc,Exception e){
        desc=(desc==null)? "Unexpected Checked Exception.":desc;
        if(e instanceof IllegalAccessException || e instanceof IllegalAccessException
        || e instanceof  NoSuchMethodException){
            return  new IllegalArgumentException(desc,e);
        }
        else if(e instanceof InvocationTargetException){
            return  new RuntimeException(desc,((InvocationTargetException)e).getTargetException());
        }else if (e instanceof  RuntimeException){
            return  (RuntimeException)e;

        }
        return new RuntimeException(desc,e);
    }

    /**
     * 循环向上转型 获取对象的DeclareMethod
     * 如向上转型到Object 仍无法找到 返回null
     *
     * @param object
     * @param methodName
     * @param parameterTypes
     * @return
     */
    private static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {

            }
        }
        return null;
    }

}
