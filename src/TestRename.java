import java.io.File;

/**
 * Created by zk on 19/6/18.
 */
public class TestRename {

    public static void main(String[] args) {
        String path = "/Users/zk/Desktop/ts_on_test";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files){
            String fileName = file.getName();
            String[] nameSplit = fileName.split("_");
            String newFileName = nameSplit[0] + "_" + nameSplit[1] + "_2_" + nameSplit[2];
            file.renameTo(new File("/Users/zk/Desktop/ts_on_test/" + newFileName));
        }
    }
}
