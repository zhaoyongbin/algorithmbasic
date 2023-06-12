package com.example.java.asyncAspectImpl;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public class ReturnMsg<T> implements Serializable {
   private  static final long seriaVersionUID=-1233456468756456l;
    public static final int SUCCESS_CODE=200;
    public static final int ERROR_CODE=500;
    private  int code=SUCCESS_CODE;
    private String msg="";
    private  T data;

    public ReturnMsg() {
    }

    public ReturnMsg(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ReturnMsg(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ReturnMsg returnSuccess( ) {
        return new ReturnMsg(ReturnMsg.SUCCESS_CODE,"成功");
    }

    public static ReturnMsg returnSuccess(String s) {
        if(StringUtils.isEmpty(s)){
            return  returnSuccess();
        }
        return new ReturnMsg(ReturnMsg.SUCCESS_CODE,s);
    }
    public static ReturnMsg dataSuccess(Object data){
        return new ReturnMsg(ReturnMsg.SUCCESS_CODE,"查询成功");
    }

    public static ReturnMsg dataSuccess(Object data,String message){
        return new ReturnMsg(ReturnMsg.SUCCESS_CODE,message,data);
    }

    public static ReturnMsg returnError(){
        return new ReturnMsg(ReturnMsg.ERROR_CODE,"失败");
    }

    public static ReturnMsg returnError( String message){
        return new ReturnMsg(ReturnMsg.ERROR_CODE,message);
    }


    public static ReturnMsg returnError( String message,Object data){
        return new ReturnMsg(ReturnMsg.ERROR_CODE,message,data);
    }

    public static ReturnMsg returnError(  Object data){
        return new ReturnMsg(ReturnMsg.ERROR_CODE,"失败",data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getData() {
        return null;
    }


    @Override
    public String toString() {
        return "ReturnMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
