package WaitNotify.WaitNotifydemo;



public class NumberHolder {
    private volatile int number;
    public synchronized void increase(){
        try {
            while (0 != number) {
                wait();
            }
            // 能执行到这里说明已经被唤醒
            number++;
            System.out.println(number);
            notifyAll();
            } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void decrease(){
        try{
            while (0 == number){
                wait();
            }
            number--;
            System.out.println(number);
            notifyAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


class IncreaseThread implements Runnable{

    private NumberHolder numberHolder;

    public IncreaseThread(NumberHolder numberHolder){
        this.numberHolder = numberHolder;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
        numberHolder.increase();
        }
    }
}

class DecreaseThread implements Runnable{

    private NumberHolder numberHolder;

    public DecreaseThread(NumberHolder numberHolder){
        this.numberHolder = numberHolder;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            numberHolder.decrease();
        }
    }
}

class Run1{
    public static void main(String[] args) {
        NumberHolder numberHolder = new NumberHolder();
        Thread a = new Thread(new IncreaseThread(numberHolder));
        Thread b = new Thread(new DecreaseThread(numberHolder));
        Thread c = new Thread(new IncreaseThread(numberHolder));
        Thread d = new Thread(new DecreaseThread(numberHolder));
        a.start();
        b.start();
        c.start();
        d.start();
    }
}