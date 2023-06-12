package com.example.java.asyncAspectImpl;

import com.example.java.asyncAspect.AsyncExecuteAspect;
import com.example.java.asyncAspect.process.StakeResultProcessor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 、 异步执行任务时 预先返回结果的处理器
 * 定义了预先返回结果 returnMsg
 */
public class BipStakeResultProcessor implements StakeResultProcessor<ReturnMsg> {
    @Override
    public boolean isFinishedResult(ReturnMsg result) {
        Object details = result.getData();
        if(details==null){
            return true;
        }
        if(details instanceof  Map){
            Map<String, Object> detailsMap = (Map<String, Object>) details;
            return !detailsMap.containsKey(AsyncExecuteAspect.ASYNC_EXECUTE_RESULT_TICKET);
        }else {
            return false;
        }

    }

    @Override
    public ReturnMsg createStakeResultWithDetails(Map<String, Object> details) {
        ReturnMsg retmg = ReturnMsg.returnSuccess("");
        return retmg;
    }

    @Override
    public ReturnMsg createStakeResultWithDetail(String impl, Object detail) {
        Map<String, Object> details = new HashMap<>();
        details.put(impl, detail);
        return createStakeResultWithDetails(details);


    }

    @Override
    public ReturnMsg createFallbackResult(Exception e) {
        String msg = e.getMessage();
        if(e instanceof InvocationTargetException){
            msg=((InvocationTargetException)e).getTargetException().getMessage();
        }
        return ReturnMsg.returnSuccess(msg);
    }

    @Override
    public Class<?> getSupportClazz() {
        return ReturnMsg.class;
    }
}
