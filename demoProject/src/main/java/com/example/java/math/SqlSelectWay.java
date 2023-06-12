package com.example.java.math;

public enum SqlSelectWay {
    LABLE("X","1"),
    LABL3("8","1"),
    SELECT("2","5");
    private  String code;
    private  String value;


    SqlSelectWay(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
