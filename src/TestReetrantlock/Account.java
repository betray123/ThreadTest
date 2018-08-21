package TestReetrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 此示例演示1000个线程如何往同一个公共账号里面存1块钱，用到线程同步，锁同一个对象。
 * 使用ReetrantLock，底层使用CAS+AQS
 */
public class Account {

    private double balance;
//    Lock lock = new ReentrantLock();

    public void topup(double money){
//        lock.lock();
        try {
            double newBalance = balance + money;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = newBalance;
        }finally {
//            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }
}

class AddMoneyThread implements Runnable{

    private Account account;
    private double money;

    public AddMoneyThread(Account account, double money){
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        account.topup(money);
    }
}

class Run{
    public static void main(String[] args) {
        Account account = new Account();
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            service.execute(new AddMoneyThread(account,1));
        }
        service.shutdown();
        while (!service.isTerminated()){}
        System.out.println("账户余额：" + account.getBalance());
    }
}
