package ThreadStop;

/**
 * 停止线程的方法：
 * 1.调用thread.interrupt();方法，给线程加一个停止的标记
 *   通过for循环内加判断是否是标记位，再加抛异常停止线程
 */
public class StopThreadMethod extends Thread{

    @Override
    public void run() {
        try {
            for (int i = 0; i < 500000; i++){
                if (this.isInterrupted()){
                    System.out.println("已经是停止状态了！我要退出了！");
                    throw new InterruptedException();
                }
                System.out.println("i = " + (i+1));
            }
            System.out.println("我在for下面！！！");
        }catch (InterruptedException e){
            System.out.println("进入StopThreadMethod类的run方法中的catch方法了！");
            e.printStackTrace();
        }
    }
}

class Run{
    public static void main(String[] args) {
        try {
            StopThreadMethod stopThreadMethod = new StopThreadMethod();
            stopThreadMethod.start();
            Thread.sleep(2000);
            stopThreadMethod.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch!!!");
            e.printStackTrace();
        }
        System.out.println("end!!!");
    }
}
