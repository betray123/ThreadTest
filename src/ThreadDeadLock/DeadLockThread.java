package ThreadDeadLock;

/**
 *本示例演示多线程死锁的经典问题
 * 多线程死锁是指多个线程都在等待不可能被释放的锁，造成线程的“假死”，多线程编程一定要避免这个问题。
 * 怎样检测多线程是否死锁：
 * 1.进入cmd，到jdk的bin目录下，执行JVM自带的jps命令，找到Run的id。
 * 2.输入jstack -l jps得到的id，可以检测到每个线程获得的锁以及等待获取的锁。
 */
public class DeadLockThread implements Runnable{
    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();
    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public void run() {
        if ("a".equals(username)){
            synchronized (lock1){
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("按lock1 --->lock2 顺序执行了！");
                }
            }
        } else if ("b".equals(username)){
            synchronized (lock2){
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("按lock2 --->lock1 顺序执行了！");
                }
            }
        }
    }
}

class Run{
    public static void main(String[] args) throws InterruptedException {
        DeadLockThread deadLockThread = new DeadLockThread();
        deadLockThread.setUsername("a");
        Thread t1 = new Thread(deadLockThread);
        t1.start();
        Thread.sleep(100);
        deadLockThread.setUsername("b");
        Thread t2 = new Thread(deadLockThread);
        t2.start();
    }
}

