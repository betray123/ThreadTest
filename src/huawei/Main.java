package huawei;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zk
 * @version 1.0
 * @description:  输入n，k;1<=n<=9,1<=k<=n! 输出排在第k位置的数字
 * @date 2021/2/5 8:51 PM
 */
public class Main {

    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            if (n < 1 || n > 9){
                System.out.println("n的值不合法，请输入1-9范围");
                continue;
            }
            //求n的阶乘
            int nk = getJieCheng(n);

            //判断k是否在n的阶乘范围内
            if (k<1 || k>nk){
                System.out.println(String.format("k的值不合法，请输入1-%s范围",nk));
                continue;
            }

            //求全排列
            List<Integer> list = new ArrayList<Integer>(nk+1);
            list.stream().sorted(Comparator.comparing(Integer::longValue,Comparator.reverseOrder())).collect(Collectors.toList());
//            getNewArray(n,list,nk);
//            System.out.println(list.get(k));
            List<Integer> m = new ArrayList<>();
            for (int i = 1; i <= n;i++){
                m.add(i);
            }
            f(m,n,0);

        }
        scanner.close();
    }


    private static void f(List<Integer> shu, int targ, int cur) {
        // TODO Auto-generated method stub
        if(cur == targ) {
            System.out.println(stack);
            return;
        }

        for(int i=0;i<shu.size();i++) {
            if(!stack.contains(shu.get(i))) {
                stack.add(shu.get(i));
                f(shu, targ, cur+1);
                stack.pop();
            }

        }
    }

    public static void getNewArray(int n, List<String> list, int nk) {
        if (n == 1) {
            list.add(String.valueOf(n));
            return;
        }
//        //转为数组
//        List<Integer> ins = new ArrayList<>(n+1);
//        for (int i = 1; i <= n; i++){
//            ins.add(i);
//        }
        getStr(n,list);
    }

    public static String getStr(Integer in, List<String> list) {
        if (in == 1) return "1";
        return String.valueOf(in) + getStr(in-1,list);
    }

    public static int getJieCheng(int n){
        if (n == 1){
            return 1;
        }else {
            return n * getJieCheng(n-1);
        }
    }

}
