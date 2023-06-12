package com.example.java;

import org.junit.Test;
import org.springframework.stereotype.Service;

@Service("1233")
public class User {
    public  User user;

    private Integer userId;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*public User(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }*/

    public User() {
    }

    public User(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    public  User getUser(){
        if(user==null) {
            System.out.println("sdfsdfsdfsdf");
            user=new User();
        }
        return  user;
    }


    @Test
    public void test(){
//        getUser();
        System.out.println(user);
    }

}


