package swift.valid.gen.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class CsvUtils {

    /*
     * read csv file as parameter
     * each row and split by comma
     * use map to save the data
     * key is the index of the column
     * value is the data of the column
     */
    public static List<Map<Integer, String>> readCsvFile(File filePath) {
        List<Map<Integer, String>> dataList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");
                Map<Integer, String> map = new HashMap<>();
                for (int i = 0; i < item.length; i++) {
                    map.put(i, item[i]);
                }
                dataList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /* 读取CSV文件
     * 文件的字符集编码为UTF-8
     * 分割符为逗号
     * @param filePath 文件路径
     * @return dataList 返回的`List<Map<Integer, String>>`数据
     * @throws Exception
     */
    public static List<Map<Integer, String>> readCsvFile(String filePath) throws Exception {
        List<Map<Integer, String>> dataList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String item[] = line.split(",");
            Map<Integer, String> map = new HashMap<>();
            for (int i = 0; i < item.length; i++) {
                map.put(i, item[i]);
            }
            dataList.add(map);
        }
        return dataList;


}
