package com.example.java.math;

import org.junit.Test;

/**
 * 双向链表-链表类
 */
public class DoubleLinkedClass {
    // 实际存储元素个数
    int size;
    // 双向链表的头节点
    private DoubleNode first;
    // 双向链表的尾节点
    private DoubleNode last;

    //头插法
    public  void addFirst(int val){
        // 暂存头节点
        DoubleNode head = first;
        // 1.先 new 一个节点
        DoubleNode node = new DoubleNode(val);
        // 2.再将头节点指向新插入的节点
        first = node;
        // 注意判空
        if (head == null) {
            // 如果链表为空，尾节点就是头节点
            last = node;
        } else { // 3.将新节点的后继与原来的第一个元素相连，再将原来第一个节点的前驱与新节点相连
            node.next = head;
            head.pre = node;
        }
        size++;
        System.out.println("dfksjfi");
        System.out.println(head.toString());
    }

    // 尾插法
    public void addLast(int val) {
        DoubleNode l = last;
        DoubleNode node = new DoubleNode(val);
        last = node;
        // 注意判空
        if (l == null) {
            // 头节点就是尾节点
            first = node;
        } else {
            node.pre = l;
            l.next = node;
        }
        size++;
        System.out.println("dfksjfi");


    }



    //初始化头节点
    private DoubleNode head = new DoubleNode(0 );
    //返回头节点
    public DoubleNode getHead(){
        return head;
    }

    /**
     * 双向链表节点的添加     节点间是相互独立的  用上下指针来进行关系链接
     *
     * @param heroNode
     */
    public void add(DoubleNode heroNode){
        DoubleNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void addByOrder(DoubleNode heroNode){
        DoubleNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.value == heroNode.value){
                flag = true;
                break;
            }
            if(temp.next.value > heroNode.value){
                break;
            }
            temp = temp.next;
        }

        if(flag){
            System.out.println("要添加的节点编号已经存在");
        }else{
            if (temp.next != null) {
                temp.next.pre = heroNode;  //heroNode和后一个节点建立链接
                heroNode.next = temp.next;
            }
            temp.next = heroNode; //heroNode和前一个节点建立链接
            heroNode.pre = temp;
        }
    }




    /**
     * 双向链表节点的修改
     * @param newHeroNode
     */
    public void update(DoubleNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        DoubleNode temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.value == newHeroNode.value){
                flag = true;
//                break;
            }
            if(flag){
                temp.name = newHeroNode.name;
                flag = false;
//            temp.nickname = newHeroNode.nickname;
            }else {
                System.out.println("没有找到编号为" + newHeroNode.value + "的节点");
            }
            temp = temp.next;
        }

    }

    /**
     * 双向链表中节点的删除
     *
     * @param no
     */
    public void delete(int no){

        //这里 与单链表不同的是，直接从head后第一个节点开始找就可以，辅助指针直接指向第一个有效节点
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        DoubleNode temp = head.next;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.value == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
//            //找到待删除的节点位置
//            temp.pre.next = temp.next;
//            //注意，如果是最后一个节点，就不需要执行下面这段话
//            if(temp.next != null) {
//                temp.next.pre = temp.pre;
//            }
            //去除的是最后一个节点
           if(temp.next.next==null){
               temp.next.pre=null;
               temp.next=null;
           }
          //如果不是最后一个节点
            if(temp.next.next!=null){
                temp.next.next.pre=temp.next.pre;
                temp.next= temp.next.next;
            }
        }else {
            System.out.println("要删除的编号为" + no + "节点不存在");
        }
    }

    /**
     * 双向链表节点的遍历
     */
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        DoubleNode temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }



    @Test
    public void testNode(){
        DoubleNode node = new DoubleNode(6);
        DoubleNode node1 = new DoubleNode(2);
        DoubleNode node2 = new DoubleNode(3);
        DoubleNode node3 = new DoubleNode(2);
        //初始化头节点
        DoubleLinkedClass linkedClass=new DoubleLinkedClass();
        linkedClass.add(node);
        linkedClass.add(node1);
        linkedClass.add(node2);
        linkedClass.addByOrder(node3);

//       first=node;
//        int val=4;
//        addFirst(val);
//        addLast(5);
        System.out.println("sdkfjshdkfh");

        linkedClass.list();
//        System.out.println("---------------------------------------------------------");
//        //修改
        DoubleNode newHeroNode = new DoubleNode(2,"nike");
        linkedClass.update(newHeroNode);
        linkedClass.list();
        System.out.println("---------------------------------------------------------");
//
//        //删除
        linkedClass.delete(2);
        linkedClass.list();
        System.out.println("---------------------------------------------------------");
//
//        //按照顺序添加
//        HeroNode2 newHeroNode2 = new HeroNode2(6,"科科","小马");
//        HeroNode2 newHeroNode3 = new HeroNode2(5,"菲菲","小羊");
//        linkedClass.addByOrder(newHeroNode2);
//        linkedClass.addByOrder(newHeroNode3);
//        linkedClass.list();
        System.out.println("---------------------------------------------------------");

    }



}
