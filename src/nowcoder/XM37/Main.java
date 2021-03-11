package nowcoder.XM37;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author zk
 * @version 1.0
 * @description:
 * 在你面前有一个n阶的楼梯(n > = 100且n < 500)，你一步只能上1阶或3阶。
 * 请问计算出你可以采用多少种不同的方式爬完这个楼梯（到最后一层为爬完）。
 * (注意超大数据)
 *
 * 输入描述:
 * 一个正整数，表示这个楼梯一共有多少阶
 * 输出描述:
 * 一个正整数，表示有多少种不同的方式爬完这个楼梯
 * @date 2021/3/11 4:00 PM
 */
public class Main {

    /**
     * 递归思想最简单，最通用的解法是动态规划
     *
     * 动态规划可以求出每一次1-n个台阶，各自对应的具体m种攀爬方式
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        BigInteger[] arr = new BigInteger[n];
        arr[0] = BigInteger.ONE;//n=1时的爬楼梯方式
        arr[1] = BigInteger.ONE;//n=2时的爬楼梯方式
        arr[2] = BigInteger.valueOf(2);//n=3时的爬楼梯方式
        for (int i = 3; i < n; i++){
            arr[i] = arr[i-1].add(arr[i-3]);
        }
        System.out.println(arr[n-1]);
    }
}
