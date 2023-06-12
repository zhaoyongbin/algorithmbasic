package com.example.java;

public class User1 {

    public  String name;
    public  int age;
    public  String stockCode;
    public  String stockName;
    public boolean curr;

    public User1(String name, Integer age, String stockCode, String stockName) {
        this.name = name;
        this.age = age;
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

    public User1() {
    }

    public boolean isCurr() {
        return curr;
    }

    public void setCurr(boolean curr) {
        this.curr = curr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                '}';
    }
}
