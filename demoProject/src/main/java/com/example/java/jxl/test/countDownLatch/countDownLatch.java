package com.example.java.jxl.test.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class countDownLatch {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "出去了");
                downLatch.countDown();
            },
                   String.valueOf(i)).start();
            System.out.println("--------------------------");
        }
        //downLatch.await();
        System.out.println("主线程关门！");
    }



}
