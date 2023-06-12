package com.example.java.asyncAspect.other;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class MethodparameterUtil {
    private static Logger LOG = LoggerFactory.getLogger(MethodparameterUtil.class);

     //async_execute_result_ticket
    public static String resolveParameterRequst(String targetParamName) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration parameterNames = request.getParameterNames();
        String pName;
        do{
            if(!parameterNames.hasMoreElements()){
                return request.getHeader(targetParamName);
            }
            pName=(String) parameterNames.nextElement();
        } while(!targetParamName.equals(pName));

        String ticket = request.getParameter(pName);
        return ticket;
    }

    public static List<MultipartFile> getMultiPartFile(Method method, ProceedingJoinPoint joinPoint) {
        List<MultipartFile> multipartFileList = new ArrayList<>();
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = ms.getParameterNames();
        Object[] paratemerValus = joinPoint.getArgs();
        Type[] paratemerTypes = method.getGenericParameterTypes();
        Class<?>[] parameterClasses = method.getParameterTypes();

        for (int i = 0; i < parameterNames.length; i++) {
            Object targetValue = paratemerValus[i];
            String parameterName = parameterNames[i];
            if (targetValue instanceof MultipartFile) {
                LOG.info("日志切面，推断出入参数{}对应的类型是MultipartFile", parameterName);
                MultipartFile file = (MultipartFile) targetValue;
                multipartFileList.add(file);
            }else  if (targetValue instanceof  MultipartFile[]){
                LOG.info("日志切面，推断出入参数{}对应的类型是MultipartFile", parameterName);
                MultipartFile[] files = (MultipartFile[]) ((MultipartFile[]) targetValue);
                multipartFileList.addAll(Arrays.asList(files));
            }else {
                Class<?> parameterClz = parameterClasses[i];
                if(Collection.class.isAssignableFrom(parameterClz)){
                    Type parameterType = paratemerTypes[i];
                    if(parameterType instanceof ParameterizedType){
                        Type[] actualTypes = ((ParameterizedType) parameterType).getActualTypeArguments();
                        Type actualType = actualTypes[0];
                        if(actualType.getTypeName().equals("org.springframework.web.multipart.MultipartFile")){
                            LOG.info("日志切面，推断出入参数{}对应的类型是Collection<MultipartFile>", parameterName);
                            Collection<MultipartFile> multipartFiles=(Collection) targetValue;
                            multipartFiles.addAll(multipartFiles);
                        }
                    }

                }
            }
        }


        return multipartFileList;
    }
}
