package com.example.java.math.class03;

import java.util.Stack;

//栈实现队列结构
public class TwoStackImplQueue {
    public static class MyStack1 {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public MyStack1() {
            this.popStack = new Stack<>();
            this.pushStack = new Stack<>();
        }

        //push栈向pop 栈导入数据   一次全部导入  保证pop 栈是空栈
        public void pushToPop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        //从pop 栈中取出数据
        public int popDate() {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                throw new RuntimeException("当前队列是空的");
            }
            pushToPop();
            return popStack.pop();
        }

        // 往push 栈中压入数据
        public void pushDate(int val) {
            pushStack.push(val);
            pushToPop();
        }


        //peek 数据
        public int peek(){
            if (popStack.empty() && pushStack.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return popStack.peek();
        }



    }
}
