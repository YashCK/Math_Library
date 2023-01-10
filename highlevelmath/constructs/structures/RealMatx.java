package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

public abstract class RealMatx<T> implements Mat<T, Double, RealField>{

    protected static final String ROW_OUT_RANGE = "The rows are out of range.";
    protected static final String ROW_NUM_OUT_RANGE = "The row number is out of range.";
    protected static final String COL_OUT_RANGE = "The columns are out of range.";
    protected static final String COL_NUM_OUT_RANGE = "The col number is out of range.";

    protected RealVec<T>[] data;
    protected static boolean multiline = true;

    //Manipulate Matrix
    @Override
    public void addRows(int row1, int row2) throws OperationUndefinedException {
        if(row1 >= data.length || row2 >= data.length)
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        //Row 1 = Row 1 + Row 2
        getRow(row1).add(getRow(row2));
    }

    @Override
    public void addColumns(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data[0].length() || col2 >= data[0].length())
            throw new OperationUndefinedException(COL_OUT_RANGE);
        //Col 1 = Col 1 + Col 2
        getCol(col1).add(getCol(col2));
    }

    @Override
    public void subtractRows(int row1, int row2) throws OperationUndefinedException {
        if(row1 >= data.length || row2 >= data.length)
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        //Row 1 = Row 1 - Row 2
        getRow(row1).subtract(getRow(row2));
    }

    @Override
    public void subtractColumns(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data[0].length() || col2 >= data[0].length())
            throw new OperationUndefinedException(COL_OUT_RANGE);
        //Col 1 = Col 1 - Col 2
        getCol(col1).subtract(getCol(col2));
    }

    @Override
    public void interchangeRows(int row1, int row2) throws OperationUndefinedException {
        if(row1 >= data.length || row2 >= data.length)
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        RealVec<T> firstRow = (RealVec<T>)getRow(row1);
        setRow(row1, getRow(row2));
        setRow(row2, firstRow);
    }

    @Override
    public void interchangeCols(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data[0].length() || col2 >= data[0].length()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        RealVec<T> firstCol = (RealVec<T>) getCol(col1);
        RealVec<T> secondCol = (RealVec<T>) getCol(col2);
        setCol(col1, secondCol);
        setCol(col2, firstCol);
    }

    @Override
    public void scaleRow(int rowNum, Double factor) throws OperationUndefinedException {
        if(rowNum >= data.length)
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        data[rowNum].scale(factor);
    }

    @Override
    public void scaleColumn(int columnNum, Double factor) throws OperationUndefinedException {
        if(columnNum >= data[0].length())
            throw new OperationUndefinedException(COL_OUT_RANGE);
        getCol(columnNum).scale(factor);
    }

    //Getters
    @Override
    public int nrows() {
        return data.length;
    }

    @Override
    public int ncols() {
        return data[0].length();
    }

    @Override
    public T get(int row, int col) throws OperationUndefinedException {
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

    @Override
    public boolean contains(T value) throws OperationUndefinedException {
        for(RealVec<T> v : data){
            if(v.contains(value)){
                return true;
            }
        }
        return false;
    }

    //Setter Methods

    @Override
    public void setRow(int rowNum, Vec<T, Double, RealField> newRow) throws OperationUndefinedException {
        if(newRow.length() != data[0].length())
            throw new OperationUndefinedException("The vector length is out of range.");
        newRow.pad(data[0].length() - newRow.length());
        for(int col = 0; col < data[rowNum].length(); col++){
            data[rowNum].set(col, newRow.get(col));
        }
    }

    @Override
    public void setCol(int colNum, Vec<T, Double, RealField> newCol) throws OperationUndefinedException {
        if(newCol.length() >= data.length)
            throw new OperationUndefinedException("The vector length is out of range.");
        newCol.pad(data.length - newCol.length());
        for(int row = 0; row < data.length; row++){
            data[row].set(colNum, newCol.get(row));
        }
    }

    @Override
    public void set(int row, int column, T value) throws OperationUndefinedException {
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

    protected void recorrectMatrix(RealVec<T> [] matrix){
        //Find maximum row length
        int maxRowLength = 0;
        for(RealVec<T> v : matrix){
            if(v.length() > maxRowLength)
                maxRowLength = v.length();
        }
        //Fill in any empty space to make the matrix take the form of a rectangle
        for(int row = 0; row < matrix.length; row++){
            matrix[row].pad(maxRowLength - matrix[row].length());
        }
    }

    protected void applyOperation(RealMatx<T> matrix, MatrixOperation<T> op) throws OperationUndefinedException{
        sameDimensions(matrix);
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].length(); col++){
                data[row].set(col, op.operation(data[row].get(col), matrix.get(row, col)));
            }
        }
    }

    protected void sameDimensions(RealMatx<T> matrix) throws OperationUndefinedException {
        if(data.length != matrix.nrows()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if(data[0].length() != matrix.ncols()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of columns.");
        }
    }

    @Override
    public boolean equals(Object o) {
        try {
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((RealMatx<T>)o).ncols() || data[0].length() != ((RealMatx<T>)o).ncols()){
                return false;
            }
            for(int row = 0; row < data.length; row++){
                for(int col = 0; col < data[0].length(); col++){
                    if(data[row].get(col) != ((RealMatx<T>)o).get(row, col)){
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
        for(RealVec<T> v : data){
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
