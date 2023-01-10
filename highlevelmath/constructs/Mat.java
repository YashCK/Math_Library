package highlevelmath.constructs;

import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

/**
 * This class creates a represntation of a matrix that can be used to store data
 * and perform operations upon. Matrices will be especially useful for operations in
 * linear algebra and similar areas. 
 * <p>
 * Each row and column of the matrix are treated as Vectors.
 * </p>
 */
public abstract class Mat<E, S, V extends Vec<E, S, ?>> {

    protected static final String ROW_OUT_RANGE = "The rows are out of range.";
    protected static final String ROW_NUM_OUT_RANGE = "The row number is out of range.";
    protected static final String COL_OUT_RANGE = "The columns are out of range.";
    protected static final String COL_NUM_OUT_RANGE = "The col number is out of range.";

    protected V[] data;
    protected static boolean multiline = true;

    //Operations between Matrices

    /**
     * Operation to add two matrices to one another
     *  - Each entry will be added to the corresponding entry in the input Matrix
     * @param matrix Matrix object that should be added
     * @throws OperationUndefinedExceptfion
     */
    public abstract void add(Mat<E, S, V> matrix) throws OperationUndefinedException;

    /**
     * Operation to subtract two matrices to one another
     *  - Each entry will subtract the corresponding entry in the input Matrix
     * @param matrix Matrix object that should be subtracted
     * @throws OperationUndefinedException
     */
    public abstract void subtract(Mat<E, S, V> matrix) throws OperationUndefinedException;

    /**
     * Operation to multiply two matrices by one another
     * @param matrix Matrix object that should be multiplied
     * @return a new Matrix that is the product of the two previous matrices
     * @throws OperationUndefinedException
     */
    public abstract Mat<E, S, V> multiply(Mat<E, S, V> matrix) throws OperationUndefinedException;

    /**
     * Operation to multiply a matrix by a vector
     * @param v Vector object that should be multiplied
     * @return a new Vector that is the product of the matrix and vector parameter
     * @throws OperationUndefinedException
     */
    public abstract V multiply(V v) throws OperationUndefinedException;

    //Methods to Manipulate Matrix

    /**
     * Return a Matrix that represents a sub-matrix of the current one
     *  - (Row/Column positions start with 0)
     * @param startRow The row to start sub-matrix entries
     * @param endRow The row to end sub-matrix entries (inclusive)
     * @param startCol The column to start sub-matrix entries
     * @param endCol The column to end sub-matrix entries (inclusive)
     * @return Matrix that represents sub-matrix satisying defined parameters
     * @throws OperationUndefinedException
     */
    public abstract Mat<E, S, V> subMatrix(int startRow, int endRow, int startCol, int endCol) throws OperationUndefinedException;

    /**
     * Adds row 2 to row 1 of the matrix
     *  - (Row positions start with 0)
     * @param row1 The row to be added to
     * @param row2 The row that will be added
     * @throws OperationUndefinedException
     */
    public abstract void addRows(int row1, int row2) throws OperationUndefinedException;

    /**
     * Adds column 2 to column 1 of the matrix
     *  - (Column positions start with 0)
     * @param col1 The column to be added to
     * @param col2 The column that will be added
     * @throws OperationUndefinedException
     */
    public abstract void addColumns(int col1, int col2) throws OperationUndefinedException;

    /**
     * Subtracts row 2 from row 1 of the matrix
     *  - (Row positions start with 0)
     * @param row1 The row that will be subtracted from
     * @param row2 The row that will be subtracted
     * @throws OperationUndefinedException
     */
    public abstract void subtractRows(int row1, int row2) throws OperationUndefinedException;

    /**
     * Subtracts column 2 from column 1 of the matrix
     *  - (Column positions start with 0)
     * @param col1 The column that will be subtracted from
     * @param col2 The column that will be subtracted
     * @throws OperationUndefinedException
     */
    public abstract void subtractColumns(int col1, int col2) throws OperationUndefinedException;

    /**
     * Interchange two rows of the matrix
     *  - (Row positions start with 0)
     * @param row1 A row number that will exchange positions with another row
     * @param row2 A row number that will exchange positions with another row
     * @throws OperationUndefinedException
     */
    public void interchangeRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length)
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        V firstRow = getRow(row1);
        setRow(row1, getRow(row2));
        setRow(row2, firstRow);
    }
    
    /**
     * Interchange two columns of the matrix
     *   - (Column positions start with 0)
     * @param col1 A column number that will exchange positions with another column
     * @param col2 A column number that will exchange positions with another column
     * @throws OperationUndefinedException
     */
    public  void interchangeCols(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].length() || col2 >= data[0].length()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        V firstCol = getCol(col1);
        V secondCol = getCol(col2);
        setCol(col1, secondCol);
        setCol(col2, firstCol);
    }

    /**
     * Scale a row of the matrix by a factor
     * @param rowNum The row number to be scaled (first row is 0)
     * @param factor The factor by which the row is scaled
     * @throws OperationUndefinedException
     */
    public void scaleRow(int rowNum, S factor) throws OperationUndefinedException {
        if(rowNum >= data.length)
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        data[rowNum].scale(factor);
    }

    /**
     * Scale a column of the matrix by a factor
     * @param columnNum The column number (first column is 0)
     * @param factor The factor by which the column is scaled
     * @throws OperationUndefinedException
     */
    public abstract void scaleColumn(int columnNum, double factor) throws OperationUndefinedException;

    public abstract Mat<E, S, V> copy();

    //Getter Methods

    /**
     * Get the number of rows
     * @return number of rows in the matrix
     */
    public int nrows(){
        return data.length;
    }

    /**
     * Get the number of columns
     * @return number of columns in the matrix
     */
    public int ncols(){
        return data[0].length();
    }

    /**
     * Get a particular row from the matrix
     * @param rowNum The row number to get (first row is 0)
     * @return A new Vector with the contents of the row chosen from the matrix
     * @throws OperationUndefinedException
     */
    public abstract V getRow(int num) throws OperationUndefinedException;

    /**
     * Get a particular column from the matrix
     * @param columnNum The column number to get (first column is 0)
     * @return A new Vector with the contents of the column chosen from the matrix
     * @throws OperationUndefinedException
     */
    public abstract V getCol(int num) throws OperationUndefinedException;
    
    /**
     * Get the value at a particular index of the matrix
     *  - (Row/Column positions start with 0)
     * @param row The row position of the index
     * @param col The column positoin of the index
     * @return The value at the index
     * @throws OperationUndefinedException
     */
    public E get(int row, int col) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        } else if(col > data[0].length()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        return data[row].get(col);
    }

    /**
     * Get the multline attribute which configures how the string representation of a matrix is represented
     * @return boolean of whether the class prints matricies on multiple lines or not
     */
    public static boolean getMultiline(){
        return multiline;
    }

    /**
     * Returns whether a particular value is present within the matrix or not
     * @param value The value to search for | MUST be the type of values stored in the Vector
     * @return True if value is in the matrix, False otherwise
     * @throws OperationUndefinedException
     */
    public boolean contains(E value) throws OperationUndefinedException{
        for(V v : data){
            if(v.contains(value)){
                return true;
            }
        }
        return false;
    }

    //Setter Methods

    /**
     * Set a particular row number to a new Vector
     * @param rowNum The row number that should be set to a new Vector (first row is 0)
     * @param newRow A Vector that represents the new row
     * @throws OperationUndefinedException
     */
    public void setRow(int rowNum, V newRow) throws OperationUndefinedException{
        if(newRow.length() != data[0].length())
            throw new OperationUndefinedException("The vector length is out of range.");
        newRow.pad(data[0].length() - newRow.length());
        for(int col = 0; col < data[rowNum].length(); col++){
            data[rowNum].set(col, newRow.get(col));
        }
    }

    /**
     * Set a particular column number to a new Vector
     * @param colNum The column number that should be set to a new Vector (first column is 0)
     * @param newCol A Vector that represents the new column
     * @throws OperationUndefinedException
     */
    public void setCol(int colNum, V newCol) throws OperationUndefinedException{
        if(newCol.length() >= data.length)
            throw new OperationUndefinedException("The vector length is out of range.");
        newCol.pad(data.length - newCol.length());
        for(int row = 0; row < data.length; row++){
            data[row].set(colNum, newCol.get(row));
        }
    }

    /**
     * Sets a particular index of the matrix to a new value
     *  - (Row/Column positions start with 0)
     * @param row The row position of the index
     * @param column The column position of the index
     * @param value The value the position should be set to
     * @throws OperationUndefinedException
     */
    public void set(int row, int column, E value) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        } else if(column > data[0].length()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        data[row].set(column, value);
    }

    /**
     * The multline attribute configures whether the string representation of a matrix
     * should all be displayed on one line, or across many lines (multiline output)
     * @param multiline The state the multiline attribute should be set to
     */
    public static void setMultiline(boolean mline){
        multiline = mline;
    }

    //Other Methods
    protected void recorrectMatrix(V[] matrix){
        //Find maximum row length
        int maxRowLength = 0;
        for(V v : matrix){
            if(v.length() > maxRowLength)
                maxRowLength = v.length();
        }
        //Fill in any empty space to make the matrix take the form of a rectangle
        for(int row = 0; row < matrix.length; row++){
            matrix[row].pad(maxRowLength - matrix[row].length());
        }
    }

    protected void applyOperation(Mat<E, S, V> matrix, MatrixOperation<E> op) throws OperationUndefinedException{
        sameDimensions(matrix);
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].length(); col++){
                data[row].set(col, op.operation(data[row].get(col), matrix.get(row, col)));
            }
        }
    }

    protected void sameDimensions(Mat<E, S, V> matrix) throws OperationUndefinedException {
        if(data.length != matrix.nrows()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if(data[0].length() != matrix.ncols()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of columns.");
        }
    }

    @Override
    public boolean equals(Object o){
        try {
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((Mat<E, S, V>)o).ncols() || data[0].length() != ((Mat<E, S, V>)o).ncols()){
                return false;
            }
            for(int row = 0; row < data.length; row++){
                for(int col = 0; col < data[0].length(); col++){
                    if(data[row].get(col) != ((Mat<E, S, V>)o).get(row, col)){
                        return false;
                    }
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

    @Override
    public String toString() {
        String str = "[";
        int rowNum = 0;
        for(V v : data){
            str += v.toString();
            if(multiline && rowNum < data.length - 1){
                str += "\n ";
            }
            rowNum++;
        }
        str += "]";
        return str;
    }
    
}
