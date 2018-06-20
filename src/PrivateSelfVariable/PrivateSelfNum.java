package PrivateSelfVariable;

/**
 * "非线程安全"只存在于实例变量，也就是成员变量，如果是方法内的局部变量，则不存在“非线程安全问题”
 * 以下代码示例方法内部变量不存在非线程安全问题.
 */
public class PrivateSelfNum {
    public void addI(String name){
        int num = 0;
            try {
                if ("a".equals(name)) {
                    num = 100;
                    System.out.println("a set over");
                    Thread.sleep(2000);
                }else {
                    num = 200;
                    System.out.println("b set over");
                }
                System.out.println(name + "num = " + num);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}

class ThreadA extends Thread{
    private PrivateSelfNum privateSelfNum;
    public ThreadA(PrivateSelfNum privateSelfNum){
        this.privateSelfNum = privateSelfNum;
    }

    @Override
    public void run() {
        super.run();
        privateSelfNum.addI("a");
    }
}

class ThreadB extends Thread{
    private PrivateSelfNum privateSelfNum;
    public ThreadB(PrivateSelfNum privateSelfNum){
        this.privateSelfNum = privateSelfNum;
    }

    @Override
    public void run() {
        super.run();
        privateSelfNum.addI("b");
    }
}

class Run{
    public static void main(String[] args) {
        PrivateSelfNum privateSelfNum = new PrivateSelfNum();
        ThreadA a = new ThreadA(privateSelfNum);
        ThreadB b = new ThreadB(privateSelfNum);
        a.start();
        b.start();
    }
}