package TestFutureTask;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class TestFutureTask implements Callable<Map<String,String>> {

    private String name;

    private String sort;

    public TestFutureTask(String name,String sort){
        this.name = name;
        this.sort = sort;
    }

    @Override
    public Map<String, String> call() throws Exception {
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("name",name);
        map.put("sort",sort);
        System.out.println("thread id : " + Thread.currentThread().getId() + " result : " + map.toString());
        return map;
    }
}
