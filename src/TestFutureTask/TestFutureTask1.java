package TestFutureTask;

import java.util.Map;

public class TestFutureTask1 extends TestFutureTask{
    public TestFutureTask1(String name, String sort) {
        super(name, sort);
    }

    @Override
    public Map<String, String> call() throws Exception {
        Thread.sleep(30*1000);
        return super.call();
    }
}
