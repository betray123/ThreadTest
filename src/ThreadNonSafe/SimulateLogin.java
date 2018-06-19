package ThreadNonSafe;

/**
 * 模拟登录线程不安全环境
 */
public class SimulateLogin {
    private static String usernameRef;
    private static String passwordRef;

    public static synchronized void doPost(String username, String password){
        usernameRef = username;//a进来睡的时候，b进来跑完数据，usernameRef指向的是b，passwordRef指向bb，a醒来后passwordRef修改为aa，但是usernameRef依然指向b
        if ("a".equals(username)){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        usernameRef = username;//这样做就避免了b把a的值覆盖掉,另一种方法就是给这个方法加锁
        passwordRef = password;
        System.out.println("username = " + usernameRef + " password = " + passwordRef);
    }
}

class Alogin implements Runnable{

    @Override
    public void run() {
        SimulateLogin.doPost("a","aa");
    }
}

class Blogin implements Runnable{

    @Override
    public void run() {
        SimulateLogin.doPost("b","bb");
    }
}

class Run{
    public static void main(String[] args) {
        Thread threadA = new Thread(new Alogin());
        Thread threadB = new Thread(new Blogin());
        threadA.start();
        threadB.start();
    }
}