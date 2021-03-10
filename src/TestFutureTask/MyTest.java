package TestFutureTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MyTest {
    public static void main(String[] args) {
        //创建任务线程对象（包含任务call方法）
        List<FutureTask<Map<String,String>>> list = createTask();
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //线程池提交任务线程对象
        for (FutureTask<Map<String,String>> task : list){
            executorService.submit(task);
        }
        //shutdown线程池，但不终止已经加载进池的任务
        executorService.shutdown();
        //设置超时时间
        try {
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //查看执行结果
        for (FutureTask<Map<String,String>> task : list){
            try {
                Map<String,String> map = task.get(30,TimeUnit.MILLISECONDS);
                System.out.println(map.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<FutureTask<Map<String,String>>> createTask(){
        TestFutureTask t = new TestFutureTask("A","1");
        FutureTask<Map<String,String>> task1 = new FutureTask<>(t);
//        FutureTask<Map<String,String>> task4 = new FutureTask<>(t);
        FutureTask<Map<String,String>> task2 = new FutureTask<>(new TestFutureTask1("B","2"));
        FutureTask<Map<String,String>> task3 = new FutureTask<>(new TestFutureTask("C","3"));
        List<FutureTask<Map<String,String>>> list = new ArrayList<>();
        list.add(task1);
        list.add(task1);
        list.add(task2);
        list.add(task3);
        return list;
    }
}
