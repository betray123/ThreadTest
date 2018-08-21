package TestCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 用实现callable接口的方式，创建线程。
 * callable的特点：实现线程执行完毕后还能return执行后的值
 */
public class MyTest implements Callable {

    private int upperBounds;

    public MyTest(int upperBounds){
        this.upperBounds = upperBounds;
    }

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <upperBounds; i++) {
            sum = sum +i;
        }
        return sum;
    }
}


class Run{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            list.add(service.submit(new MyTest((int) (Math.random()*100))));
        }

        int sum = 0;
        for (Future<Integer> future:list) {
            sum+=future.get();
        }
        System.out.println(sum);
    }
}