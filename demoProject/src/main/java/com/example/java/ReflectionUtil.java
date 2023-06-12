package com.example.java;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ReflectionUtil {
    /**
     * 根据属性名查找对应的属性，子类没有的属性会到父类找
     *
     * @param o
     * @param fieldName
     * @return
     */
    public static Field getDeclareField(Object o, String fieldName) {
        Field field = null;
        Class<?> clazz = o.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
//                log.warn("bean没有该属性，bean = " + clazz.getName() + "，属性 = " + fieldName);
            }
        }
        return field;
    }

    /**
     * 属性设值
     *
     * @param o         子类对象
     * @param fieldName 属性名
     * @param value     需要设置的属性值
     */
    public static void setFieldValue(Object o, String fieldName, Object value) {
        Field field = getDeclareField(o, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(o, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
//            log.error("对象没有该属性，对象 = " + o.getClass() + " 属性 = " + fieldName);
        }
    }

    public static Object getFieldName(Object o, String fieldName) {
        Field field = getDeclareField(o, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                return field.get(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取对象所有的属性，包括父类的属性
     *
     * @param o
     * @return
     */
    public static Field[] getAllProperties(Object o ,String filedName) {
        Class clazz = o.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        boolean istrue = fieldList.contains(filedName);
        if(fieldList.contains(filedName)){

        }
        return fields;
    }

   /* public static Field getAllProperties(Object o ,String filedName) {
        Class clazz = o.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        boolean istrue = fieldList.contains(filedName);
        if(fieldList.contains(filedName)){
            Field field = new Field();
        }
        return fields;
    }*/
}

