package WaitNotify;

/**
 * 此示例演示两个线程交替执行。
 * 使用volatile关键字和等待通知机制进行.
 * 注意：判断prevIsA用while不用if的原因：
 *      一个生产者消费者模型的任务队列，一个生产者一次可能放入多个任务，然后用notifyAll通知消费者，
 *      但是并非所有被唤醒的消费者都能取到一个任务，那么队列被读空了之后的消费者肯定得继续await。
 *      如果你用if来判断，这个消费者第二次被notify的时候就不会再次判断!(ok to proceed)这个条件了，
 *      如果这个时候这个消费者又一次没抢到任务，但是代码还是往下执行了，轻则空指针异常，重了干出什么事情来都说不定了。
 *
 * 所以必须用while来检查!(ok to proceed)，这样可以保证每次被唤醒都会检查一次条件。
 */
public class Wait_Notify_Alternate_Insert {

    //使用如下代码作为标记
    private volatile boolean prevIsA = false;

    public synchronized void backupA() throws InterruptedException {

        while (prevIsA == true){
            this.wait();
        }
        for (int i =  0; i < 3; i++){
            System.out.println("333333");
        }
        prevIsA = true;
        this.notifyAll();
    }

    public synchronized void backupB() throws InterruptedException {

        while (prevIsA == false){
            this.wait();
        }
        for (int i =  0; i < 3; i++){
            System.out.println("666666");
        }
        prevIsA = false;
        this.notifyAll();
    }
}


class ThreadA implements Runnable{

    private Wait_Notify_Alternate_Insert alternate_insert;
    public ThreadA(Wait_Notify_Alternate_Insert alternate_insert){
        this.alternate_insert = alternate_insert;
    }

    @Override
    public void run() {
        try {
            alternate_insert.backupA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB implements Runnable{

    private Wait_Notify_Alternate_Insert alternate_insert;
    public ThreadB(Wait_Notify_Alternate_Insert alternate_insert){
        this.alternate_insert = alternate_insert;
    }

    @Override
    public void run() {
        try {
            alternate_insert.backupB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run3{
    public static void main(String[] args) {
        Wait_Notify_Alternate_Insert alternate_insert = new Wait_Notify_Alternate_Insert();
        for (int i = 0; i < 20; i++){
        Thread threadA = new Thread(new ThreadA(alternate_insert));
        Thread threadB = new Thread(new ThreadB(alternate_insert));
        threadB.start();
        threadA.start();
        }
    }
}