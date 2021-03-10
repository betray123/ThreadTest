package huawei;

import java.util.*;

/**
 * @author zk
 * @version 1.0
 * @description: ABC三个策略，相邻的用户不能使用同一个策略，多个满足要求的选最后一个
 * @date 2021/2/5 10:09 PM
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
           Integer userCount = Integer.parseInt(scanner.nextLine());
           Map<Integer, List<Integer>> map = new HashMap<>();
           for (int i = 0;i<userCount;i++){
               String str = scanner.nextLine();
               String[] strs = str.split(" ");
               List<Integer> in = new ArrayList<>();
               for (String s : strs){
                   in.add(Integer.parseInt(s));
               }
               map.put(i,in);
           }
           //动态规划
            int xy [][] = new int[userCount][3];

           //用于记录上一次用的计划
           for (int i = 0; i < userCount; i++){
               for (int j = 0; j < 3; j++){
                   // 如果为第一个用户
                       //15 8 17
                       //12 9 20
                       //3 7 5
                   if (i == 0 && j == 0){
                       xy[0][0] = map.get(0).get(0);
                       xy[0][1] = map.get(0).get(1);
                       xy[0][2] = map.get(0).get(2);
                   }

                   //如果为第二个用户，第一个用户选了a方案
                   if (i > 0 && j == 0){
                       xy[i][j] = map.get(i).get(j) + Math.min(xy[i-1][j+1],xy[i-1][j+2]);
                       xy[i][j+1] = map.get(i).get(j+1) + Math.min(xy[i-1][j],xy[i-1][j+2]);
                       xy[i][j+2] = map.get(i).get(j+2) + Math.min(xy[i-1][j],xy[i-1][j+1]);
                       continue;
                   }
                   //如果为第二个用户，第一个用户选了b方案
                   if (i > 0 && j == 1){
                       xy[i][j] = map.get(i).get(j) + Math.min(xy[i-1][j-1],xy[i-1][j+1]);
                       xy[i][j-1] = map.get(i).get(j-1) + Math.min(xy[i-1][j],xy[i-1][j+1]);
                       xy[i][j+1] = map.get(i).get(j+1) + Math.min(xy[i-1][j-1],xy[i-1][j]);
                   }
                   //如果为第二个用户，第一个用户选了c方案
                   if (i > 0 && j == 2){
                       xy[i][j] = map.get(i).get(j) + Math.min(xy[i-1][j-2],xy[i-1][j-1]);
                       xy[i][j-1] = map.get(i).get(j-1) + Math.min(xy[i-1][j-2],xy[i-1][j]);
                       xy[i][j-2] = map.get(i).get(j-2) + Math.min(xy[i-1][j-1],xy[i-1][j]);
                   }
               }
           }
            System.out.println(xy[userCount-1][2]);
        }
    }
}
