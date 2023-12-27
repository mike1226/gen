package swift.valid.gen.excel;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellReference;

public class ExcelUtils {

    /**
     * Opens an Excel file located at the specified file path.
     *
     * @param file the File object representing the Excel file to be opened
     * @return the Workbook object representing the opened Excel file, or null if an
     *         error occurs
     */
    public static Workbook openExcelFile(File filePath) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * Opens a specific sheet in the given workbook.
     *
     * @param workbook  the Workbook object representing the opened Excel file
     * @param sheetName the name of the sheet to be opened
     * @return the Sheet object representing the opened sheet, or null if the sheet
     *         does not exist
     */
    public static Sheet openSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet;
    }

    /**
     * Reads the value of a defined name in the workbook.
     *
     * @param workbook the workbook containing the defined name
     * @param sheet    the sheet containing the defined name
     * @param name     the name of the defined name
     * @return the value referred to by the defined name, or null if the defined
     *         name does not exist
     */
    public static String readDefinedNameValue(Workbook workbook, Sheet sheet, String name) {
        Name definedName = workbook.getName(name);
        if (definedName != null) {
            String refersToFormula = definedName.getRefersToFormula();
            CellReference cellReference = new CellReference(refersToFormula);
            Row row = sheet.getRow(cellReference.getRow());
            Cell cell = row.getCell(cellReference.getCol());
            return cell.getStringCellValue();
        }
        return null;
    }

    /**
     * Gets the position of a defined name in the workbook.
     *
     * @param workbook the workbook containing the defined name
     * @param sheet    the sheet containing the defined name
     * @param name     the name of the defined name
     * @return the position of the defined name, or null if the defined name does
     *         not exist
     */
    public static DefinePosition getDefinedNamePosition(Workbook workbook, Sheet sheet, String name) {
        Name definedName = workbook.getName(name);

        DefinePosition position = new DefinePosition(0, 0);
        
        if (definedName != null) {
            String refersToFormula = definedName.getRefersToFormula();
            CellReference cellReference = new CellReference(refersToFormula);

            position.setRow(cellReference.getRow());
            position.setCol(cellReference.getCol());

        }

        return position;
    }

    /**
     * Reads the value of a cell at the specified row and column.
     *
     * @param sheet the Sheet object representing the sheet containing the cell
     * @param row   the row number of the cell
     * @param col   the column number of the cell
     * @return the value of the cell as a string, or null if the cell is empty
     */
    public static String readCellValue(Sheet sheet, int row, int col) {
        Row sheetRow = sheet.getRow(row);
        if (sheetRow != null) {
            Cell cell = sheetRow.getCell(col);
            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        return cell.getStringCellValue();
                    case NUMERIC:
                        return String.valueOf(cell.getNumericCellValue());
                    case BOOLEAN:
                        return String.valueOf(cell.getBooleanCellValue());
                    case FORMULA:
                        return cell.getCellFormula();
                    case BLANK:
                        return "";
                    default:
                        return "";
                }
            }
        }
        return "";
    }

}
