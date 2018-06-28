package WaitNotify;

/**
 * 此示例演示生产者/消费者模式
 */
public class Producer_Consumer {
    public static String value = "";
}

/**
 * 创建生产者类
 */
class Producer{
    private Object lock;
    public Producer(Object lock){
        this.lock = lock;
    }

    public void setValue() throws InterruptedException {
        synchronized (lock){
            if (!Producer_Consumer.value.equals("")){
                lock.wait();
            }
            String value = System.currentTimeMillis()+"_"+System.nanoTime();
            System.out.println("set的值是" + value);
            Producer_Consumer.value = value;
            lock.notify();
        }

    }
}

/**
 * 创建消费者
 */
class Customer{
    private Object lock;
    public Customer(Object lock){
        this.lock = lock;
    }

    public String getValue() throws InterruptedException {
        synchronized (lock){
            if (Producer_Consumer.value.equals("")){
                lock.wait();
            }
            System.out.println("get的值为" + Producer_Consumer.value);
            Producer_Consumer.value = "";
            lock.notify();
        }
        return Producer_Consumer.value;
    }
}

/**
 * 创建生产者线程类
 */
class ThreadProcedure extends Thread{
    private Producer producer;
    public ThreadProcedure(Producer producer){
        this.producer = producer;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                producer.setValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 * 创建消费者线程类
 */
class ThreadCustomer extends Thread{
    private Customer customer;
    public ThreadCustomer(Customer customer){
        this.customer = customer;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                customer.getValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Run2{
    public static void main(String[] args) {
        Object lock = new Object();
        Producer producer = new Producer(lock);
        Customer customer = new Customer(lock);
        ThreadProcedure threadProcedure = new ThreadProcedure(producer);
        ThreadCustomer threadCustomer = new ThreadCustomer(customer);
        threadProcedure.start();
        threadCustomer.start();
    }
}