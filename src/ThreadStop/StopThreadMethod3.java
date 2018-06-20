package ThreadStop;

/**
 * 停止线程的方法：
 * 3.将方法interrupt();方法与return结合使用，也能实现停止线程的效果。
 */
public class StopThreadMethod3 extends Thread{
    @Override
    public void run() {
        super.run();
        while (true){
            if (this.isInterrupted()){
                System.out.println("线程停止了");
                return;
            }
            System.out.println("timer = " + System.currentTimeMillis());
        }
    }
}

class Run3{
    public static void main(String[] args) {
        try {
            StopThreadMethod3 stopThreadMethod3 = new StopThreadMethod3();
            stopThreadMethod3.start();
            Thread.sleep(2000);
            stopThreadMethod3.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}