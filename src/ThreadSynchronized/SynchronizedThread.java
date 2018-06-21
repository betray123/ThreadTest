package ThreadSynchronized;

/**
 * sychronized关键字修饰的方法与锁对象
 * 多个线程访问同一个对象中的成员变量时，需要加锁，保证数据的准确性，这是线程的同步。
 * 如果多个线程之间是互斥的，那就可以各跑各的，互不影响，这是线程的异步。
 * synchronized修饰方法时，锁的是这个方法所在的对象，不是方法。
 * 示例展示两个结论：
 * 1.A线程先持有对象的锁，那么B线程依然能以异步的方式调用对象中的任何非synchronized方法。
 * 2.A线程先持有对象的锁，那么B线程想要访问对象的其他synchronized修饰的方法，就得排队等待锁释放。（这就证明了锁的是对象，而不是对象中的方法）
 */
public class SynchronizedThread {
    //方法A加synchronized修饰，B不加，演示结论1
    //方法A加synchronized修饰，B也加，演示结论2
    public synchronized void methodA(){
        try {
            System.out.println("begin methodA ThreadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end time = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodB(){
        try {
            System.out.println("begin methodB ThreadName = " + Thread.currentThread().getName() + "begin time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadA extends Thread{
    private SynchronizedThread synchronizedThread;
    public ThreadA(SynchronizedThread synchronizedThread){
        this.synchronizedThread = synchronizedThread;
    }
    @Override
    public void run() {
        super.run();
        synchronizedThread.methodA();
    }
}

class ThreadB extends Thread{
    private SynchronizedThread synchronizedThread;
    public ThreadB(SynchronizedThread synchronizedThread){
        this.synchronizedThread = synchronizedThread;
    }
    @Override
    public void run() {
        super.run();
        synchronizedThread.methodB();
    }
}

class Run{
    public static void main(String[] args) {
        SynchronizedThread synchronizedThread = new SynchronizedThread();
        ThreadA threadA = new ThreadA(synchronizedThread);
        ThreadB threadB = new ThreadB(synchronizedThread);
        threadA.setName("A");
        threadB.setName("B");
        threadA.start();
        threadB.start();
    }
}
