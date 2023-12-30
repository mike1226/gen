package swift.valid.gen.excel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CsvUtils {

    /*
     * read csv file as parameter
     * each row and split by comma
     * use map to save the data
     * key is the index of the column
     * value is the data of the column
     */
    public static List<Map<Integer, String>> readCsvFile(String filePath) {
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

}
