package swift.valid.gen.excel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CsvUtilsTest {

   @Autowired
   private CsvUtils CsvReader;

   @Test
   public void testReadCsvFile() throws IOException {

      // get application run path
      String path = System.getProperty("user.dir");
      // get csv file path
      String filePath = path + "\\demo.csv";

      String excelPath = path + "\\demo.xlsx";

      // atfer test delete the excel file
      File file = new File(excelPath);
      if (file.exists()) {
         file.delete();
      }

      var data = CsvUtils.readCsvFile(filePath);

      // Assert that the data is not null
      assertNotNull(data);

      // output csv to excel
      ExcelUtils.createExcelFile(excelPath, data);

      
      boolean fileExists = file.exists();
      assertTrue(fileExists);
   }

}
