package com.example.java.jxl.test.countDownLatch;

import org.junit.Assert;

import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);
        String a="sdf";
        Assert.assertNotNull(a,"method must not be null");

        for(int i=0; i<2; i++){
            Thread thread = new Thread(new Player(begin,end));
            Thread thread1 = new Thread(new Player(begin,end),String.valueOf(i));
            thread.start();
            Thread.sleep(3000);
        }
//        Thread thread1 = new Thread(new Player(begin,end));
//        thread1.start();
//        System.out.println(Thread.currentThread().getName() + " arrived !");

        try{
            System.out.println("the race begin");
            begin.countDown();

//            end.await();
//            end.countDown();
            System.out.println("the race end");
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("每个不曾起舞的日子，都是对生命的辜负");

    }






}
