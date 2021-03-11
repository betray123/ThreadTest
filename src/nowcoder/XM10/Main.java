package nowcoder.XM10;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author zk
 * @version 1.0
 * @description: 在你面前有一个n阶的楼梯，你一步只能上1阶或2阶。请问计算出你可以采用多少种不同的方式爬完这个楼梯。
 * 输入描述:
 * 一个正整数n(n<=100)，表示这个楼梯一共有多少阶
 * 输出描述:
 * 一个正整数，表示有多少种不同的方式爬完这个楼梯
 * 示例:输入5,输出8
 * @date 2021/3/11 2:44 PM
 */
public class Main {

    /**
     * 考虑斐波那契数列 f(n) = f(n-1) + f(n-2)
     * f(1) = 1,f(2) = 2
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigInteger result = getResult(n);
        System.out.println(result);
    }

//    /**
//     * 方案1：递归实现斐波那契数列求和（从后往前）（此法实现最简单，但是容易造成栈溢出，随着n越大，栈的深度指数级增长）
//     * @param n
//     * @return
//     */
//    private static int getResult(int n) {
//        if (n==2) return 2;
//        if (n==1) return 1;
//        return getResult(n-1) + getResult(n-2);
//    }

//    /**
//     * 方案2：for循环实现斐波那契数列求和（从前往后）（此法只适用于2个连着的数，比如1+2；若为1+3则缺少一个东西存中间变量，需要用动态规划）
//     * @param n
//     * @return
//     */
//    private static BigInteger getResult(int n){
//        if (n==2) return BigInteger.valueOf(2);
//        if (n==1) return BigInteger.ONE;
//
//        BigInteger one = BigInteger.ONE;
//        BigInteger two = BigInteger.valueOf(2);
//        BigInteger res = BigInteger.ZERO;
//        if (n>=3){
//            for (int i = 3; i<= n; i++){
//                res = one.add(two);
//                one = two;
//                two = res;
//            }
//        }
//        return res;
//    }

    /**
     * 方案3：动态规划实现斐波那契数列(此法最通用，推荐此法)
     * @param n
     * @return
     */
    private static BigInteger getResult(int n){
        if (n==2) return BigInteger.valueOf(2);
        if (n==1) return BigInteger.ONE;

        BigInteger res = BigInteger.ZERO;
        BigInteger[] arr = new BigInteger[n];
        arr[0] = BigInteger.ONE;
        arr[1] = BigInteger.valueOf(2);
        if (n>=3){
            for (int i = 2; i< n; i++){
                arr[i] = arr[i-1].add(arr[i-2]);
            }
        }
        return arr[n-1];
    }
}
