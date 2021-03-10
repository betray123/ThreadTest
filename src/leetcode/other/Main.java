package leetcode.other;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main{
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        String[] strs = str.split(";");
//        List<String> list = Arrays.asList(strs).stream().filter(s -> regexStr(s)).collect(Collectors.toList());
////        System.out.println(regexStr("W78"));
//        if (null != list && list.size() > 0){
//            Geo geo = new Geo(0,0);
//            for (String s : list){
//                String flag = s.substring(0,1);
//                Integer count = Integer.parseInt(s.substring(1));
//                switch (flag){
//                    case "A":
//                        geo.setX(geo.getX() - count);
//                        break;
//                    case "D":
//                        geo.setX(geo.getX() + count);
//                        break;
//                    case "W":
//                        geo.setY(geo.getY() + count);
//                        break;
//                    case "S":
//                        geo.setY(geo.getY() - count);
//                        break;
//                    default: break;
//                }
//            }
//            System.out.println(geo.toString());
//        }
//
//    }

    public static void main(String[] args) {
        System.out.println(regexStr("D05"));
    }

    public static boolean regexStr(String str){
        Pattern pattern = Pattern.compile("(A|S|W|D){1}[0-9]{1,2}$");
        Matcher match = pattern.matcher(str);
        boolean flag = match.matches();
        return flag;
    }

    static class Geo{

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        private Integer x = 0;
        private Integer y = 0;

        public Geo(Integer x,Integer y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
           return x + "," + y;
        }
    }
}
