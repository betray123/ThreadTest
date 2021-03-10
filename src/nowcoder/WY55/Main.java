package nowcoder.WY55;

import java.util.Scanner;

/**
 * @author zk
 * @version 1.0
 * @description:https://www.nowcoder.com/practice/3e9d7d22b7dd4daab695b795d243315b?tpId=182&tqId=34329&rp=1&ru=%2Fta%2Fexam-all&qru=%2Fta%2Fexam-all%2Fquestion-ranking&tab=answerKey
 * 给定5个正整数, 它们的最小的众倍数是指的能够被其中至少三个数整除的最小正整数。 给定5个不同的正整数, 请计算输出它们的最小众倍数。
 * 示例：
 * 输入：1 2 3 4 5
 * 输出：4
 * @date 2021/3/10 2:35 PM
 */
public class Main {

    /**
     * 思路：
     * 1.将5个数做C53的操作（排列组合）
     *
     * 2.然后将选出来的3个数进行计算最小公倍数
     *
     * 3.求前两个的最小公倍数，然后拿结果再与第三个数求最小公倍数，得到的就是三个数的最小公倍数
     *
     *  3.1求最小公倍数方案1：穷举法，将两个数中最小的找到，然后for循环从最小的到最大的遍历，每次+1，如果能同时整除两个数，则为最小公倍数
     *
     *  3.2求最小公倍数方案2：两个数的最小公倍数=a*b/（a和b的最大公约数），可以演变成求a和b的最大公约数，然后也能求出最小公倍数
     *
     * 4.每次选择最小的那个最小公倍数即可
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strings = str.split(" ");
        int[] arrs = new int[strings.length];
        for (int i = 0; i < strings.length; i++){
            arrs[i] = Integer.parseInt(strings[i]);
        }
        //初始化最小公倍数值
        int res = Integer.MAX_VALUE;

        //正式开始
        for (int i = 0; i < 3; i++){
            for (int j = i+1; j < 4; j++){
                for (int k = j+1; k < 5; k++){
                    //方案1：递归求最小公倍数
//                    res = Math.min(res,fun(fun(arrs[i],arrs[j]),arrs[k]));
                    //方案2：穷举求最小公倍数
                    res = Math.min(res,fun1(fun1(arrs[i],arrs[j]),arrs[k]));

                }
            }
        }
        System.out.println(res);
    }

    //穷举求最小公倍数:max到a*b，但凡有能整除a和b的，就是a和b的最小众倍数
    private static int fun1(int a, int b) {
        int max = Math.max(a,b);
        for (int i = max; i <= a*b; i++){
            if (i%a == 0 && i%b == 0){
                return i;
            }
        }
        return a*b;
    }

    //递归求最小公倍数
    private static int fun(int a,int b){
        // a*b/最大公约数
        return a*b/gcd(a,b);
    }

    //递归求最大公约数
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b,a%b);
    }

}
