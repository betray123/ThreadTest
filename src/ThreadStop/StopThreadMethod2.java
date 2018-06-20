package ThreadStop;

/**
 * 停止线程的方法：
 * 2.线程在sleep状态下停止线程。
 *   如果在sleep状态下停止某一线程，会进入catch语句，并且清除停止状态值，使之变成false
 */
public class StopThreadMethod2 extends Thread{

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin ");
            Thread.sleep(200000);
            System.out.println("run end ");
        } catch (InterruptedException e) {
            System.out.println("在沉睡中被停止！进入catch！" + this.isInterrupted());
            e.printStackTrace();
        }
    }
}

class Run2{
    public static void main(String[] args) {
        try {
            StopThreadMethod2 stopThreadMethod2 = new StopThreadMethod2();
            stopThreadMethod2.start();
            Thread.sleep(200);
            stopThreadMethod2.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch ");
            e.printStackTrace();
        }
        System.out.println("end ");
    }
}
