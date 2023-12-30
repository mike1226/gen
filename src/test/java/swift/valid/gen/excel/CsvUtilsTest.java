package swift.valid.gen.excel;

import static org.junit.jupiter.api.Assertions.*;

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
    String filePath = path + "../../demo.csv";


      var data = CsvReader.readCsvFile(filePath);

      // Assert that the data is not null
      assertNotNull(data);

   }

}
