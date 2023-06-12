package com.example.java.math;

/**
 * 双向链表-节点类
 */
public class DoubleNode {
    int value;
    String name;
    DoubleNode next;
    DoubleNode pre;

    public DoubleNode(int value ) {
        this.value = value;
    }

    public DoubleNode(int value ,String name) {
        this.value = value;
        this.name= name;
    }

    public DoubleNode(DoubleNode next, DoubleNode last) {
        this.next = next;
        this.pre = last;
    }

    public DoubleNode(int value, DoubleNode next, DoubleNode pre) {
        this.value = value;
        this.next = next;
        this.pre = pre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public DoubleNode getPre() {
        return pre;
    }

    public void setPre(DoubleNode pre) {
        this.pre = pre;
    }
}
