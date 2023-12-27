package swift.valid.gen.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull; // Add missing import statement

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ExcelUtilsTest {

    @Test
    void testOpenExcelFile() throws IOException {
        String resourceName = "classpath:input/testTemplate.xlsx";

        File file = ResourceUtils.getFile(resourceName);

        Workbook workbook = ExcelUtils.openExcelFile(file);

        // open excel file
        assertNotNull(workbook);

        // open sheet
        Sheet sheet = ExcelUtils.openSheet(workbook, "test-1");

        assertNotNull(sheet);

        // read defined name
        String definedNameValue = ExcelUtils.readDefinedNameValue(workbook, sheet, "METHOD");

        assertNotNull(definedNameValue);

        // defined name value should be "test-1"
        assert (definedNameValue.equals("CreateMT535"));
    }

    @Test
    void testValidCase1() throws IOException, IllegalAccessException, InvocationTargetException {

        String resourceName = "classpath:input/testTemplate.xlsx";

        File file = ResourceUtils.getFile(resourceName);

        Workbook workbook = ExcelUtils.openExcelFile(file);

        // open sheet
        Sheet sheet = ExcelUtils.openSheet(workbook, "test-1");

        // get package name from excel
        String packageString = ExcelUtils.readDefinedNameValue(workbook, sheet, "PACKAGE_NM");

        // get method name from excel
        String methodString = ExcelUtils.readDefinedNameValue(workbook, sheet, "METHOD");

        // Resolve class using package name and class name
        String className = packageString;
        try {
            Class<?> resolvedClass = Class.forName(className);

            // Get all methods declared in the class
            Method[] methods = resolvedClass.getDeclaredMethods();

            // DefinePosition inputPosition = ExcelUtils.getDefinedNamePosition(workbook,
            // sheet, "INPUT_ROW_START");
            DefinePosition inputParameterPostion = ExcelUtils.getDefinedNamePosition(workbook, sheet, "INPUT_NM");

            DefinePosition returnPosition = ExcelUtils.getDefinedNamePosition(workbook, sheet, "OUTPUT_NM");

            // Create a map to save the name and object type
            Map<String, Class<?>> nameObjectTypeMap = new HashMap<>();

            Map<String, Class<?>> returnObjectTypeMap = new HashMap<>(); // Add missing semicolon

            // Create a map to save the name and value ex.case 1
            Map<String, Object> nameValueMap = new HashMap<>();
            Map<String, Object> returnValueMap = new HashMap<>(); // Add missing semicolon

            String objectName = "";
            String objectType = "";
            String objectValue = "";
            Object object = null;

            // Iterate over the rows and cells
            for (int row = inputParameterPostion.getRow() + 1; row <= sheet.getLastRowNum(); row++) {
                // Read the cell value
                objectName = ExcelUtils.readCellValue(sheet, row, inputParameterPostion.getCol());
                // when the object name is empty, break the loop
                if (objectName == "") {
                    break;
                }

                // Read the object type
                objectType = ExcelUtils.readCellValue(sheet, row, inputParameterPostion.getCol() + 1);

                // add objectName and objectType to the map
                nameObjectTypeMap.put(objectName, DataUtils.getDataClassType(objectType));

                // save value of the object
                objectName = ExcelUtils.readCellValue(sheet, row, inputParameterPostion.getCol());

                // Read the object value
                objectValue = ExcelUtils.readCellValue(sheet, row, inputParameterPostion.getCol() + 2);

                object = DataUtils.convertToClassType(objectValue, DataUtils.getDataClassType(objectType));

                // add objectName and objectValue to the map
                nameValueMap.put(objectName, object);

            }

            // Iterate over the rows and cells
            for(int row = returnPosition.getRow() + 1; row <= sheet.getLastRowNum(); row++){

                // Read the cell value
                objectName = ExcelUtils.readCellValue(sheet, row, returnPosition.getCol());
                // when the object name is empty, break the loop
                if (objectName == "") {
                    break;
                }

                // Read the object type
                objectType = ExcelUtils.readCellValue(sheet, row, returnPosition.getCol() + 1);

                // add objectName and objectType to the map
                returnObjectTypeMap.put(objectName, DataUtils.getDataClassType(objectType));

                // save value of the object
                objectName = ExcelUtils.readCellValue(sheet, row, returnPosition.getCol());

                // Read the object value
                objectValue = ExcelUtils.readCellValue(sheet, row, returnPosition.getCol() + 2);

                object = DataUtils.convertToClassType(objectValue, DataUtils.getDataClassType(objectType));

                // add objectName and objectValue to the map
                returnValueMap.put(objectName, object);

            }

         
            for (Method method : methods) {
                // Get the method name
                methodString = method.getName();

                // Get the method parameters
                Parameter[] parameters = method.getParameters();

                // set parameter values from nameValueMap
                Object[] parameterValues = new Object[parameters.length];

                for (int i = 0; i < parameters.length; i++) {
                    parameterValues[i] = nameValueMap.get(parameters[i].getName());
                }

                // Invoke the method
                Object result = method.invoke(null, parameterValues);

                assertNotNull(result);

                assertEquals(result, returnValueMap.get("returnCode"));
            }

        } catch (ClassNotFoundException e) {
            // Handle class not found exception
        }
    }
}
