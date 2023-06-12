package com.example.java.math.class03;

import java.util.Stack;

public class getMinValStack {
     public  static class MyStack{
         private Stack<Integer> stackData;
         private Stack<Integer> stackMin;

         public MyStack( ) {
             this.stackData = new Stack<>();
             this.stackMin = new Stack<>();
         }

         public  void  push(int integer){
             if (stackMin.empty()) {
                 stackMin.push(integer);
             }else if (getMin()>=integer){
                 stackMin.push(integer);
             }else if (getMin()<integer){
                 stackMin.push(getMin());
             }
             stackData.peek();
         }

         public int pop(){
             if(stackData.empty()){
                 throw new RuntimeException("your stack is enpty");
             }
             stackMin.pop();
             return stackData.pop();
         }

        public int getMin(){
             if(stackMin.empty()){
                 throw new RuntimeException("your stack is enpty");
             }
             return  stackMin.peek();
        }

     }

}
