package WaitNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * 此案例演示等待/通知机制，实现线程间的通信
 * 1.wait()/notify()方法是Object类实现的方法。他们执行的条件是：必须在同步锁对象的场景下执行，即线程必须获得该对象的
 * 对象级别锁，也就是说只能在同步方法或同步代码块中运行wait()/notify()方法。且两个方法获得的对象锁是同一个对象锁。
 * 2.wait()方法执行，会使线程在wait()所在代码处停止，并释放对象锁，将线程置入对象锁的阻塞队列，等待被唤醒。
 * 3.notify()方法执行，会使线程在notify()所在代码处发出唤醒对象锁阻塞队列中的线程命令，但不会立即释放对象锁，
 * 等到调用notify()方法的线程将程序执行完毕后，释放锁，随机唤醒阻塞队列中的线程到对象锁的就绪队列，等待获取cpu时间片执行
 */
public class WaitNotifyDemo {
    List list = new ArrayList();
    public void add(){
        list.add("AA");
    }
    public int size(){
        return list.size();
    }
}

class Thread1 extends Thread{
    private Object lock;
    private WaitNotifyDemo list;
    public Thread1(Object lock,WaitNotifyDemo list){
        this.lock = lock;
        this.list = list;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock){
            if (list.size() != 5){
                System.out.println("wait begin" + System.currentTimeMillis());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait end" + System.currentTimeMillis());
            }
        }
    }
}

class Thread2 extends Thread{
    private Object lock;
    private WaitNotifyDemo list;
    public Thread2(Object lock,WaitNotifyDemo list){
        this.lock = lock;
        this.list = list;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock){
            for (int i = 0; i < 10; i++){
                list.add();
                if (list.size() == 5){
                    System.out.println("begin notify");
                    lock.notify();
                }
                System.out.println("添加了" + (i+1) +"个元素");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Run{
    public static void main(String[] args) throws InterruptedException {
        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();
        Object lock = new Object();
        Thread1 thread1 = new Thread1(lock,waitNotifyDemo);
        Thread2 thread2 = new Thread2(lock,waitNotifyDemo);
        thread1.start();
        Thread.sleep(3000);
        thread2.start();
    }
}
