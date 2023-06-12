package com.example.java.math;


import org.junit.Test;

import java.util.Stack;

public class test11 {
    public static void main(String[] args) {
        //回文数
        //准备数据
        Node headNode = prepData();
        //获取中结点
        Node middleNode = getMiddleNode(headNode);
        //将后半段逆序
        Node reverseNode = reverseNode(middleNode);
        //判断是否为回文
        boolean palindrome = isPalindrome(headNode, reverseNode);
        //逆序复原
        middleNode = reverseNode(reverseNode);
        System.out.println(palindrome);

    }

    //    @Test
    public void aaaaadd() {
        Node headNode = new Node(0);
        System.out.println(headNode);
        Node current = headNode;
        System.out.println(current);
    }


    //在测试类中，准备好链表数据：
    public static Node prepData() {
        //拼接链表：
        Node headNode = new Node(0);
        Node current = headNode;
        int[] a = {1, 2, 3, 4, 5, 5, 4, 3, 2, 1, 0};
        for (int i = 0; i < 11; i++) {
            Node temp = new Node(a[i]);
            current.setNext(temp);
            current = temp;
        }
        return headNode;
    }

    //通过快慢指针获取该链表的中间节点
    public static Node getMiddleNode(Node headNode) {
        Node lowlyNode = headNode.getNext();
        Node fastNode = null;
        if (lowlyNode != null) {
            fastNode = lowlyNode.getNext();
        }
        while (fastNode != null) {
            if (fastNode.getNext() == null) {
                //说明链表长度为奇数
                //说明此时lowlyNode为中间结点
                return lowlyNode;
            }

            if (fastNode.getNext().getNext() == null) {
                //说明链表长度为偶数
                //此时应该根据规定（看你的需求）来返回上中结点还是下中结点
                //假设链表长度为4（0，1，2，3）
                //上中结点就是1，下中结点就是2
                //此处我返回下中结点
                return lowlyNode.getNext();
            }
            //每次循环，快指针前进两步
            fastNode = fastNode.getNext();
            fastNode = fastNode.getNext();
            //每次循环，慢指针前进一步
            lowlyNode = lowlyNode.getNext();
        }

        return lowlyNode;
    }

    //    二、从中间节点对后半部分逆序
//对单链表逆序的代码如下
    public static Node reverseNode(Node head) {
        Node temp = head.getNext();
        Node flag = temp.getNext();

        head.setNext(null); //head 这个对象的地址 headNode是原始链表对象的5的地址  所以修改这里就是修改的headNode的对象

        while (flag != null) {
            temp.setNext(head);

            //temp 变成了头
            head = temp;
            //flag 变成了 temp
            temp = flag;

            flag = flag.getNext();
        }

        temp.setNext(head);
        return temp;
    }

    //    若链表长度为奇数，则在中间结点的 next 结点开始逆序
    //    若链表长度为偶数，则在下中结点开始逆序
    //三、前后半数据比较，判断是否回文

    /**
     * @param head       头结点
     * @param middleHead <p>若链表长度为奇数，则在中间结点的 next 结点开始逆序，
     *                   若链表长度为偶数，则在下中结点开始逆序<p/>
     * @return
     */
    public static boolean isPalindrome(Node head, Node middleHead) {
        /**
         * middleHead肯定是在head后面的
         * 也就是 middleHead 会先走到结尾，所以此处用 middleHead 来判断
         * 可能不是 要我挣得，明年在挣吧
         */
        while (middleHead != null) {
            if (head.getValue() != middleHead.getValue()) {
                return false;
            }

            head = head.getNext();
            middleHead = middleHead.getNext();
        }

        return true;
    }


    //单链表去除某一个值
    public Node removeNode(Node head, int num) {

        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    @Test
    public void testNode() {
        //拼接链表：
        Node headNode = new Node(0);
        Node current = headNode;
        int[] a = {3,3,1, 2, 3, 4,3, 5};
        for (int i = 0; i < 5; i++) {
            Node temp = new Node(a[i]);
            current.setNext(temp);//当前节点是指向2 添加新的接点(next)3
            current = temp;       //把这个节点地址从指向2 变成指向3
        }

        Node node1 = removeNode(headNode, 3);
        System.out.println("sdfsbkfdsjdgb");
    }

//    一、栈 队列的实际实现
//    1.双向链表实现
public void statckTest(){
    Stack aa=new Stack();
//    Code03_DoubleEndsQueueToStackAndQueue
}
//2.数组实现
public void arrayTack(){

}


    //2.栈和队列
    //。栈 ：数据先进后出  犹如弹夹     双向链表实现 栈
    //队列 ：数据先进先出  好似排队     双向链表实现 队列
//    1：24 数组实现栈  队列
    //栈和堆常见面试提
//    实现栈的基本功能基础上 在实现返回栈中最小元素功能
//    1.pop push getMin 操作时间复杂度0（1）2.设计栈类型可以 使用现成的栈结构

    //1.用栈结构实现队列结构
    // 2.用队列结构实现栈结构


    //2.递归      从思想上理解递归    从实际实现的角度出发理解递归
      //递归函数在系统上 怎么实现的？ 递归实际利用的是系统栈  递归函数的 参数 变量 放到系统栈里

    //递归 求arr中的最值
    @Test
    public void getMax( ){
//        int[] arr=new  int[4];
        int[] arr={1,2,3};
        System.out.println(  process(arr,0,arr.length-1));
        System.out.println(0+(8>>1));
    }

    public int process(int []arr,int L,int R){
        if(L==R){
            return  arr[L];
        }
//        for(int i=L..){
//          System.;
//        }
        int mid=L+((R-L)>>1);//中点
        int leftMax=process(arr,L,mid);
        int rightMax=process(arr,mid+1,R);
        return  Math.max(leftMax,rightMax);
    }

    //哈希表hashMap 有序表TreeMap的用法
    //哈希表hashMap 曾 删 改 查  时间复杂度o(1)
    //哈希表TreeMap 曾 删 改 查  时间复杂度o(logN)
    //hashMap  大类型 Integer map.(Integer,string)  Integer 按值传递
    public void hsahMapAndSortTest(){

    }

    @Test
    public void testenum(){

        System.out.println(SqlSelectWay.LABLE.getCode());
        int i= 1>>1;
        int a= 3/2;
        System.out.println(i);
        System.out.println(a);


    }

}
