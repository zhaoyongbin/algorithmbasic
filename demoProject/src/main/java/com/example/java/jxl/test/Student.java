package com.example.java.jxl.test;

public class Student {

    private Integer age;

    private String haircolor;

    private Integer no;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Student(Integer age, String haircolor, Integer no) {
        this.age = age;
        this.haircolor = haircolor;
        this.no = no;
    }

}
