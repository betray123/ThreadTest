package nowcoder.WY75;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author zk
 * @version 1.0
 * @description: https://www.nowcoder.com/practice/100cf29316344bfe95daaf25afbee5ee?tpId=182&tqId=34349&rp=1&ru=%2Fta%2Fexam-all&qru=%2Fta%2Fexam-all%2Fquestion-ranking&tab=answerKey
 *
 * 题目描述
 * 小Q是篮球训练队的教练，篮球队新加入了N名队员，第i名队员的篮球水平值为ai。
 * 小Q现在要把他们按照以下的要求分为A队和B队进行训练:
 * 1、A队的队员水平值之和严格大于B队的队员水平值之和
 * 2、对于A队中的任意一名队员，如果把他分配到B队，A队的水平值之和就会严格小于B队的水平值之和。
 * 3、每个队员必须要加入一个队伍
 * 小Q现在想知道有多少种方案可以按照以上要求完成分队。
 *
 * 输入描述:
 * 输入包括两行, 输入的第一行为一个正整数n(2 <= N <= 50), 表示队员的数量。
 * 第二行包括N个正整数 ai(1 <= ai <= 6 x 104), 表示每名队员的篮球水平值, 以空格分割。
 *
 * 输出描述:
 * 输出一个正整数, 表示方案数。
 *
 * 示例1
 * 输入
 * 4
 * 5 4 7 6
 * 输出
 * 2
 * @date 2021/3/10 3:46 PM
 */
public class Main {

    /**
     * 思路：
     * 转化为0-1背包问题 设目前有A包，共n个商品，依次决定是否放入商品i，使商品总价值
     * 达到指定数目(未放入的，则默认加入B包)
     * 定义数组dp，其中dp[i][j]表示前i个商品中，使A包总价值为j的放置方案数量
     * dp[i][j]=dp[i-1][j]+dp[i-1][j-arr[i]]; (不放入商品i，和放入商品i的情况。
     * 加号后一项需满足条件j-arr[i]>=0)
     * 本问题的特殊性
     * 在计算dp前，对价值数组arr进行降序(降序是为方便计算条件2中A包的商品最小价值)；
     * 条件2表示 取A包内最小价值的商品放入B包，新A包总价值 < 新B包总价值
     * 数组dp中，dp[i][j]表示前i个商品中，使A包总价值为j的放置方案数量。此时，
     * 如商品i放入A包，则A包内的商品最小价值为arr[i](降序)。
     * 若满足j>n_sum-j && j-arr[i]<n-（j-arr[i]）,表示当前放置方案满足1,2,3条件,
     * (A包总价值j，i往后的商品都放入B包，B包总价值n_sum-j,且A包内商品最小价值arr[i])
     * 则 ans+=dp[i-1][j-arr[i]](ans记录满足条件1,2,3的方案总数)
     *  如定义dp[n][n_sum+1]，则通过率60%，内存超限。因此定义dp[2][n_sum+1],因为每次
     * 计算一行仅仅利用了上面一行的元素值。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        String[] strs = scanner.nextLine().split(" ");
        //价值数组
        int[] arrs = new int[length];
        //价值总和
        int n_sum = 0;
        //方案总和
        long ans = 0;
        for (int i = 0; i < length; i++){
            arrs[i] = Integer.parseInt(strs[i]);
            n_sum += arrs[i];
        }
        //降序,确保j本身就是加入后价值最小的价值。（当前外面最大的价值，A包加入后此价值就是A包中最小的价值）
        int temp = 0;
        for (int i = 0; i < arrs.length-1; i++){
            for (int j = i+1; j < arrs.length;j++){
                if (arrs[i] < arrs[j]){
                    temp = arrs[i];
                    arrs[i] = arrs[j];
                    arrs[j] = temp;
                }
            }
        }

        long [][] dp = new long[2][n_sum+1]; // dp[0]表示在arr[i]之前的商品,放置方案数量，dp[1]表示加入商品arr[i]后，方案数量

        dp[0][0]=1; // 0个商品，总价值数为0的方案数量为1

        for(int j=1;j<=n_sum;j++){// 0个商品，总价值数为j(j>=1)的方案数量为0
            dp[0][j]=0;
        }

        for (int i = 0; i < arrs.length; i++){
            for (int j = 1; j < n_sum; j++){
                dp[1][j] = dp[0][j]; //未加入商品价值arr[i]

                if (j - arrs[i] >= 0){// 当前有东西放进来
                    dp[1][j] += dp[0][j-arrs[i]]; // 加入商品价值arr[i]

                    if (j > n_sum - j && j-arrs[i] < n_sum-(j-arrs[i])){ //若当前价值大于剩余价值，且当前价值-放该商品前价值 小于 总价值 - （当前价值-放该商品前价值）
                        ans += dp[0][j-arrs[i]]; //满足条件1,2,3，则修改计数ans

                    }
                }
            }

            for(int j=1; j<n_sum; j++){//更新dp[0]
                dp[0][j]=dp[1][j];
            }
        }

        System.out.println(ans);
    }
}
