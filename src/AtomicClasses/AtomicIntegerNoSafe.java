package AtomicClasses;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 本示例演示原子类在有逻辑性的情况下输出结果也具有随机性。
 * 需要用synchronized关键字给方法加锁。
 */
public class AtomicIntegerNoSafe {
    public static AtomicLong atomicLong = new AtomicLong();
    //在这里加锁来保证数据安全。
    public void addNum(){
        System.out.println(Thread.currentThread().getName() + "加了100之后输出的值是： " +atomicLong.addAndGet(100));
        atomicLong.addAndGet(1);
    }
}

class MyThread extends Thread{
    private AtomicIntegerNoSafe atomicIntegerNoSafe;

    public MyThread(AtomicIntegerNoSafe atomicIntegerNoSafe){
        this.atomicIntegerNoSafe = atomicIntegerNoSafe;
    }

    @Override
    public void run() {
        super.run();
        atomicIntegerNoSafe.addNum();
    }
}

class Run2{
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerNoSafe atomicIntegerNoSafe = new AtomicIntegerNoSafe();
        MyThread[] arrays = new MyThread[5];
        for (int i = 0; i < arrays.length; i++){
            arrays[i] = new MyThread(atomicIntegerNoSafe);
        }
        for (int i = 0; i < arrays.length; i++){
            arrays[i].start();
        }
        Thread.sleep(1000);
        System.out.println(AtomicIntegerNoSafe.atomicLong.get());

    }
}
