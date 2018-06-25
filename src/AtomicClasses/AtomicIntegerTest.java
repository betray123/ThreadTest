package AtomicClasses;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.volatile关键字只能保证多线程操作的有序性和可见性，不能保证原子性。
 * 2.线程有自己的工作内存，volatile关键字保证他们进行往自己的高速缓存读操作之前，先进行将线程高速缓存中的数据同步回主内存并且将其他线程的高速缓存置为无效，必须从主内存中读取数据。
 * 3.JVM原子操作是比较简单的基本类型赋值，i++这种操作不属于原子类型操作，需要用synchronized关键字和java.util.concurrent包下的AtomicInteger实现。
 * 该示例演示原子类进行操作，在无锁的情况下保证线程安全。
 */
public class AtomicIntegerTest extends Thread{

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10000; i++){
            System.out.println(count.incrementAndGet());
        }
    }
}


class Run{
    public static void main(String[] args) {
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        Thread t1 = new Thread(atomicIntegerTest);
        Thread t2 = new Thread(atomicIntegerTest);
        Thread t3 = new Thread(atomicIntegerTest);
        Thread t4 = new Thread(atomicIntegerTest);
        Thread t5 = new Thread(atomicIntegerTest);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}