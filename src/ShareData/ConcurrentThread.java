package ShareData;

/**
 * 多线程共享数据造成线程不安全，需要上锁的问题。
 */
//非线程安全主要是针对多个线程对同一个对象中的同一个实例变量进行操作时会出现值被更改，不同步的情况。进而影响数据准确性。
public class ConcurrentThread implements Runnable{
    private int count = 5;
    @Override
    public synchronized void run() {
        //此示例不能用for循环，一旦使用就意味着其他线程没有运行的机会，都会由一个线程运行完。
        count--;
        System.out.println("由 " + Thread.currentThread().getName() + " 计算，count= " +count);
    }

    public static void main(String[] args) {
        ConcurrentThread concurrentThread = new ConcurrentThread();
        Thread thread1 = new Thread(concurrentThread);
        Thread thread2 = new Thread(concurrentThread);
        Thread thread3 = new Thread(concurrentThread);
        Thread thread4 = new Thread(concurrentThread);
        Thread thread5 = new Thread(concurrentThread);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
