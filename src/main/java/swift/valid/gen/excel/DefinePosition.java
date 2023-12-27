package swift.valid.gen.excel;

import lombok.Data;

/**
 * Represents a position defined by a row and column in a spreadsheet.
 */
@Data
public class DefinePosition {
    
    /**
     * Represents the row position in an Excel sheet.
     */
    private int row;

    /**
     * Represents the column position.
     */
    private int col;

    /**
     * Constructs a new DefinePosition object with the specified row and column.
     * 
     * @param row the row index
     * @param col the column index
     */
    public DefinePosition(int row, int col){
        this.row = row;
        this.col = col;
    }
}
