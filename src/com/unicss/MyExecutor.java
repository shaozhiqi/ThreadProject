package com.unicss;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MyExecutor extends Thread {
private int index;
public MyExecutor(int i){
    this.index=i;
}
public void run(){
    try{
     System.out.println("["+this.index+"] start....");
     Thread.sleep((int)(Math.random()*10000));
     System.out.println("["+this.index+"] end.");
    }
    catch(Exception e){
     e.printStackTrace();
    }
}
public static void main(String args[]){
    ExecutorService service=Executors.newFixedThreadPool(4);
    for(int i=0;i<10;i++){
     service.execute(new MyExecutor(i));
     //service.submit(new MyExecutor(i));
    }
    System.out.println("submit finish");
    service.shutdown();
}
      /**
     * 1.创建了一个可重用的固定线程池，池中有四个线程。
     * 2.循环，提交了10个多线程任务
     * 3.10个任务中随机选了4个分给了池子中的4个线程
     * 4.4个线程运行任务，任务2/0/1/3并行运行，随机睡了，2和0睡的时间长，所以醒的晚。
     * 5.任务1睡的最短，所以醒的最早，最早执行结束。结束后就有一个线程空闲出来，可以看到任务4得到了该空闲线程，开始执行任务。以此类推。。。
     * run view=====================================
     *
     * submit finish
     * [2] start....sleep..7284
     * [0] start....sleep..7203
     * [1] start....sleep..4361
     * [3] start....sleep..5404
     * [1] end.
     * [4] start....sleep..3712
     * [3] end.
     * [5] start....sleep..7987
     * [0] end.
     * [6] start....sleep..8176
     * [2] end.
     * [7] start....sleep..4403
     * [4] end.
     * [8] start....sleep..628
     * [8] end.
     * [9] start....sleep..1873
     * [9] end.
     * [7] end.
     * [5] end.
     * [6] end.
     *
     */
}

