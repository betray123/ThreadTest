package leetcode.threadSortAlert;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int count = scanner.nextInt();
            Test test = new Test();
            for(int i = 0; i < count; i++){
                CountDownLatch countDown = new CountDownLatch(4);
                new Thread(() -> {
                    try{
                        test.printA();
                    }finally{
                        countDown.countDown();
                    }
                }).start();
                new Thread(() -> {
                    try{
                        test.printB();
                    }finally{
                        countDown.countDown();
                    }
                }).start();
                new Thread(() -> {
                    try{
                        test.printC();
                    }finally{
                        countDown.countDown();
                    }
                }).start();
                new Thread(() -> {
                    try{
                        test.printD();
                    }finally{
                        countDown.countDown();
                    }
                }).start();
                try{
                    countDown.await();
                }catch(InterruptedException e){

                }
//                 System.out.print("--------");
            }
        }
        System.out.println();
    }
}

class Test{
    private ReentrantLock lock = new ReentrantLock();
    private Condition conA = lock.newCondition();
    private Condition conB = lock.newCondition();
    private Condition conC = lock.newCondition();
    private Condition conD = lock.newCondition();
    private int number = 1;

    public void printA(){
        lock.lock();
        try{
            if(number != 1){
                conA.await();
            }
            System.out.print("A");
            number = 2;
            conB.signal();
        }catch(InterruptedException e){

        }finally{
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try{
            if(number != 2){
                conB.await();
            }
            System.out.print("B");
            number = 3;
            conC.signal();
        }catch(InterruptedException e){

        }finally{
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try{
            if(number != 3){
                conC.await();
            }
            System.out.print("C");
            number = 4;
            conD.signal();
        }catch(InterruptedException e){

        }finally{
            lock.unlock();
        }
    }

    public void printD(){
        lock.lock();
        try{
            if(number != 4){
                conD.await();
            }
            System.out.print("D");
            number = 1;
            conA.signal();
        }catch(InterruptedException e){

        }finally{
            lock.unlock();
        }
    }
}
