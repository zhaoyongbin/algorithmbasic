package com.example.java.math.class03;

import java.lang.reflect.Array;
import java.util.Stack;

public class RingArray {

    //    一、栈 队列的实际实现
//
    //2.数组实现 队列
    public static class MyQueue {
        private int[] arr;
        private int pushi;//end 下标 增加 +1
        private int polli;//begin 下标 减少 -1
        private int size;//添加数据的数据量
        private int limit;// 队列的大小

        //        初始化队列
        public MyQueue(int limit) {
            arr = new int[limit];
            this.pushi = 0;
            this.polli = 0;
            this.size = 0;
            this.limit = limit;
        }

        public void push(int val) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能存数据了");
            }
            size++;
            arr[pushi] = val;
            pushi = nextIndex(pushi);
        }

        public int pop(){
        if(size==0){
            throw  new RuntimeException("队列空了不能拿数据了");
        }
        size--;
        int ans=arr[polli];
        polli=nextIndex(polli);
        return ans;
        }

        //环形数组的关键
        public int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

        public static void main(String[] args) {
            MyQueue queue=new MyQueue(2);
            queue.push(1);
            queue.push(2);
            int a = queue.pop();
            int aa = queue.pop();
            System.out.println(queue.arr.toString());


        }




    }


    //数组实现 栈
    public static class MyStack {
      private  int arr[];
      private  int index;
      private  int size;//当前 栈空间大小
      private  int limit; //栈初始空间大小
      public MyStack(int limit){
          this.arr=new int[limit];
          this.index=0;
          this.size=0;
          this.limit=limit;
      }

      public void push(int value){
          if (limit==size){
              throw new RuntimeException("栈空间以满");
          }
          arr[index]=value;
          index++;
          size++;

      }
        public int pop(){
          if (size==0){
              throw new RuntimeException("当前栈空间是空的");
          }
          index--;
            size--;
          return arr[index];
        }

        public  static void main(String[] args) {
            MyStack stack=new MyStack(2);
            stack.push(2);
            stack.push(1);
           int a= stack.pop();
           int aa= stack.pop();
            System.out.println(stack.arr.toString());


        }

    }
}
