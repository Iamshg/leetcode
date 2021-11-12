package MyThread;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
 
public class RunoobTest extends Object {
 
    private List synchedList;
 
    public RunoobTest() {
        // 创建一个同步列表
        synchedList = Collections.synchronizedList(new LinkedList());
    }
 
    // 删除列表中的元素
    public String removeElement() throws InterruptedException {
        synchronized (synchedList) {  // wait 释放锁 所以加不加都一样了？？？
 
            // 列表为空就等待
            while (synchedList.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + "List is empty...");
                synchedList.wait();
                System.out.println(Thread.currentThread().getName() + "Waiting...");
            }
//            System.out.println(Thread.currentThread().getName()+"抢先完成synchedList.isEmpty() 判断");
            String element = (String) synchedList.remove(0);
            System.out.println(Thread.currentThread().getName()+"抢先完成synchedList.isEmpty() 判断");
            return element;
        }
    }
 
    // 添加元素到列表
    public void addElement(String element) {
        System.out.println("Opening...");
        synchronized (synchedList) {  // 这里得添加是锁是因为防止多个线程同时插入，出错
 
            // 添加一个元素，并通知元素已存在
            synchedList.add(element);
            System.out.println("New Element:'" + element + "'");
 
            synchedList.notifyAll();
            System.out.println("notifyAll called!");
        }
        System.out.println("Closing...");
    }
 
    public static void main(String[] args) {
        final RunoobTest demo = new RunoobTest();
 
        Runnable runA = new Runnable() {
 
            public void run() {
                try {
                    String item = demo.removeElement();
                    System.out.println("" + item);
                } catch (InterruptedException ix) {
                    System.out.println("Interrupted Exception!");
                } catch (Exception x) {
                    System.out.println("Exception thrown.");
                }
            }
        };
 
        Runnable runB = new Runnable() {
 
            // 执行添加元素操作，并开始循环
            public void run() {
                demo.addElement("Hello!");
            }
        };

        // notifyAll 所有wait 线程，  这些线程 还得重新竞争锁，从原来开始的位置开始执行， 为甚这么认为，因为  我们的   Runoob抢先完成synchedList.isEmpty() 判断  始终在   GoogleWaiting...   之前输出


        //GoogleList is empty...   // Google 线程先进入 同步块，拿到锁，但是 synchedList  为空 ，所以wait  ，此时它释放了锁
        //RunoobList is empty...   // Runoob 有机会拿到了锁， 也同样遇到了Google 的问题，所以wait  ，此时它释放了锁
        //Opening...   // 开始插入
        //New Element:'Hello!'
        //notifyAll called!  // 通知所有线程，不用等待了，
        //RunoobWaiting...
        //Runoob抢先完成synchedList.isEmpty() 判断 // 只有一个元素插入成功，两个线程，所以（只有一个线程能重新竞争到锁，从原来wait的位置开始执行，最终得到remove成功，）（括号里面是同步块中的一起完成的），另一个线程没有竞争到锁，与上次一样的， 判断 synchedList  为空 ，所以wait  ，此时它释放了锁
        //Closing...
        //GoogleWaiting...
        //GoogleList is empty... // 此例中 Google没有抢到锁，等Runoob 取出元素 ，释放锁之后， Google才进去， 所以此时就是空了， 还得wait
        //Hello!
        //Interrupted Exception!  // 因为线程 Runoob 取出元素就退出了， 此时只有 线程 Google在wait了，所以，这个nterrupted Exception 是打断了线程 Google的 wait 才抛出的
        try {
            Thread threadA1 = new Thread(runA, "Google");
            threadA1.start();
 
            Thread.sleep(500);
 
            Thread threadA2 = new Thread(runA, "Runoob");
            threadA2.start();
 
            Thread.sleep(500);
 
            Thread threadB = new Thread(runB, "Taobao");
            threadB.start();
 
            Thread.sleep(1000);
 
            threadA1.interrupt();
            threadA2.interrupt();
        } catch (InterruptedException x) {
        }
    }
}