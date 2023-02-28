package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

import java.util.Arrays;

/**
 * This class creates a representation of a matrix that can be used to store data
 * and perform operations upon. Matrices will be especially useful for operations in
 * linear algebra and similar areas.
 * <pre>
 * T is the type of the object stored in the Matrix
 * F is the Field of scalars of type S that is associated with the Vector Space.
 * Vec<T, S, F>is the type of Vector of each row and column of the Matrix
 *  </pre>
 */
public abstract class Matx<T, S> {

    protected static final String ROW_NUM_OUT_RANGE = "The row number is out of range.";
    protected static final String COL_NUM_OUT_RANGE = "The col number is out of range.";

    protected Vec<T, S>[] rData, cData;
    protected static boolean multiline = true;

    public final Field<T> element;
    public final Field<S> scalar;

    Matx() {
        element = setElementField();
        scalar = setScalarField();
    }

    //Operations between Matrices

    /**
     * Operation to add two matrices to one another
     * - Each entry will be added to the corresponding entry in the input Matrix
     *
     * @param matrix Matrix object that should be added
     * @throws OperationUndefinedException
     */
    public void add(Matx<T, S> matrix) throws OperationUndefinedException {
        applyOperation(matrix, element::add);
    }

    /**
     * Operation to subtract two matrices to one another
     * - Each entry will subtract the corresponding entry in the input Matrix
     *
     * @param matrix Matrix object that should be subtracted
     * @throws OperationUndefinedException
     */
    public void subtract(Matx<T, S> matrix) throws OperationUndefinedException {
        applyOperation(matrix, element::subtract);
    }

    /**
     * Operation to multiply two matrices by one another
     *
     * @param matrix Matrix object that should be multiplied
     * @return a new Matrix that is the product of the two previous matrices
     * @throws OperationUndefinedException
     */
    public abstract Matx<T, S> multiply(Matx<T, S> matrix) throws OperationUndefinedException;

    /**
     * Operation to multiply a matrix by a vector
     *
     * @param v Vector object that should be multiplied
     * @return a new Vector that is the product of the matrix and vector parameter
     * @throws OperationUndefinedException
     */
    public abstract Vec<T, S> multiply(Vec<T, S> v) throws OperationUndefinedException;

    //Manipulate Matrix

    /**
     * Adds row 2 to row 1 of the matrix
     * - (Row positions start with 0)
     *
     * @param row1 The row to be added to
     * @param row2 The row that will be added
     * @throws OperationUndefinedException
     */
    public void addRows(int row1, int row2) throws OperationUndefinedException {
        checkBounds("rData", row1, row2);
        //Row 1 = Row 1 + Row 2
        getRow(row1).add(getRow(row2));
        for (int col = 0; col < cData.length; col++) {
            cData[col].set(row1, getRow(row1).get(col));
        }
    }

    /**
     * Adds column 2 to column 1 of the matrix
     * - (Column positions start with 0)
     *
     * @param col1 The column to be added to
     * @param col2 The column that will be added
     * @throws OperationUndefinedException
     */
    public void addCols(int col1, int col2) throws OperationUndefinedException {
        checkBounds("cData", col1, col2);
        //Col 1 = Col 1 + Col 2
        getCol(col1).add(getCol(col2));
        for (int row = 0; row < rData.length; row++) {
            rData[row].set(col1, getCol(col1).get(row));
        }
    }

    /**
     * Subtracts row 2 from row 1 of the matrix
     * - (Row positions start with 0)
     *
     * @param row1 The row that will be subtracted from
     * @param row2 The row that will be subtracted
     * @throws OperationUndefinedException
     */
    public void subtractRows(int row1, int row2) throws OperationUndefinedException {
        checkBounds("rData", row1, row2);
        //Row 1 = Row 1 - Row 2
        getRow(row1).subtract(getRow(row2));
        for (int col = 0; col < cData.length; col++) {
            cData[col].set(row1, getRow(row1).get(col));
        }
    }

    /**
     * Subtracts column 2 from column 1 of the matrix
     * - (Column positions start with 0)
     *
     * @param col1 The column that will be subtracted from
     * @param col2 The column that will be subtracted
     * @throws OperationUndefinedException
     */
    public void subtractCols(int col1, int col2) throws OperationUndefinedException {
        checkBounds("cData", col1, col2);
        //Col 1 = Col 1 - Col 2
        getCol(col1).subtract(getCol(col2));
        for (int row = 0; row < rData.length; row++) {
            rData[row].set(col1, getCol(col1).get(row));
        }
    }

    /**
     * Interchange two rows of the matrix
     * - (Row positions start with 0)
     *
     * @param row1 A row number that will exchange positions with another row
     * @param row2 A row number that will exchange positions with another row
     * @throws OperationUndefinedException
     */
    public void interchangeRows(int row1, int row2) throws OperationUndefinedException {
        checkBounds("rData", row1, row2);
        Vec<T, S> firstRow = getRow(row1);
        setRow(row1, getRow(row2));
        setRow(row2, firstRow);
        for (int col = 0; col < cData.length; col++) {
            cData[col].set(row1, getRow(row1).get(col));
            cData[col].set(row2, getRow(row2).get(col));
        }
    }

    /**
     * Interchange two columns of the matrix
     * - (Column positions start with 0)
     *
     * @param col1 A column number that will exchange positions with another column
     * @param col2 A column number that will exchange positions with another column
     * @throws OperationUndefinedException
     */
    public void interchangeCols(int col1, int col2) throws OperationUndefinedException {
        checkBounds("cData", col1, col2);
        Vec<T, S> firstCol = getCol(col1);
        Vec<T, S> secondCol = getCol(col2);
        setCol(col1, secondCol);
        setCol(col2, firstCol);
        for (int row = 0; row < rData.length; row++) {
            rData[row].set(col1, getCol(col1).get(row));
            rData[row].set(col2, getCol(col2).get(row));
        }
    }

    /**
     * Scale a row of the matrix by a factor
     *
     * @param rowNum The row number to be scaled (first row is 0)
     * @param factor The factor by which the row is scaled
     * @throws OperationUndefinedException
     */
    public void scaleRow(int rowNum, S factor) throws OperationUndefinedException {
        checkBounds("rData", rowNum);
        getRow(rowNum).scale(factor);
        for (Vec<T, S> c : cData) {
            c.set(rowNum, getRow(rowNum).get(rowNum));
        }
    }

    /**
     * Scale a column of the matrix by a factor
     *
     * @param colNum The column number (first column is 0)
     * @param factor The factor by which the column is scaled
     * @throws OperationUndefinedException
     */
    public void scaleColumn(int colNum, S factor) throws OperationUndefinedException {
        checkBounds("cData", colNum);
        getCol(colNum).scale(factor);
        for (int row = 0; row < rData.length; row++) {
            rData[row].set(colNum, getCol(colNum).get(row));
        }
    }

    /**
     * Return a Matrix that represents a sub-matrix of the current one
     * - (Row/Column positions start with 0)
     *
     * @param startRow The row to start sub-matrix entries
     * @param endRow   The row to end sub-matrix entries (inclusive)
     * @param startCol The column to start sub-matrix entries
     * @param endCol   The column to end sub-matrix entries (inclusive)
     * @return Matrix that represents sub-matrix satisfying defined parameters
     * @throws OperationUndefinedException
     */
    public abstract Matx<T, S> subMatrix(int startRow, int endRow, int startCol, int endCol) throws OperationUndefinedException;

    public void transpose() throws OperationUndefinedException {
        Matx<T, S> copy = copy();
        for (int row = 0; row < rData.length; row++) {
            for (int col = 0; col < cData.length; col++) {
                set(row, col, copy.get(col, row));
            }
        }
    }

    /**
     * Copy the contents of the matrix into a new Matrix
     *
     * @return a new Matrix
     */
    public abstract Matx<T, S> copy();

    //Getters

    /**
     * Get the number of rows
     *
     * @return number of rows in the matrix
     */
    public int nrows() {
        return rData.length;
    }

    /**
     * Get the number of columns
     *
     * @return number of columns in the matrix
     */
    public int ncols() {
        return cData.length;
    }

    /**
     * Returns a particular row from the matrix
     *
     * @param num The row number to get (first row is 0)
     * @return A new Vector with the contents of the row chosen from the matrix
     * @throws OperationUndefinedException
     */
    protected Vec<T, S> getRow(int num) throws OperationUndefinedException {
        return rData[num];
    }

    /**
     * Returns a particular column from the matrix
     *
     * @param num The column number to get (first column is 0)
     * @return A new Vector with the contents of the column chosen from the matrix
     * @throws OperationUndefinedException
     */
    protected Vec<T, S> getCol(int num) throws OperationUndefinedException {
        return cData[num];
    }

    public Vec<T, S> copyRow(int num) throws OperationUndefinedException {
        return rData[num].copy();
    }

    public Vec<T, S> copyCol(int num) throws OperationUndefinedException {
        return cData[num].copy();
    }

    /**
     * Get the value at a particular index of the matrix
     * - (Row/Column positions start with 0)
     *
     * @param row The row position of the index
     * @param col The column position of the index
     * @return The value at the index
     * @throws OperationUndefinedException
     */
    public T get(int row, int col) throws OperationUndefinedException {
        checkBounds("rData", row);
        checkBounds("cData", col);
        return rData[row].get(col);
    }

    /**
     * Get the multiline attribute which configures how the string representation of a matrix is represented
     *
     * @return boolean of whether the class prints matrices on multiple lines or not
     */
    public static boolean getMultiline() {
        return multiline;
    }

    /**
     * Returns whether a particular value is present within the matrix or not
     *
     * @param value The value to search for | MUST be the type of values stored in the Vector
     * @return True if value is in the matrix, False otherwise
     */
    public boolean contains(T value) {
        for (Vec<T, S> v : rData) {
            if (v.contains(value)) {
                return true;
            }
        }
        return false;
    }

    //Setter Methods

    /**
     * Set a particular row number to a new Vector
     *
     * @param row    The row number that should be set to a new Vector (first row is 0)
     * @param newRow A Vector that represents the new row
     * @throws OperationUndefinedException
     */
    public void setRow(int row, Vec<T, S> newRow) throws OperationUndefinedException {
        if (newRow.length() >= cData.length) {
            throw new OperationUndefinedException("The vector length is out of range.");
        }
        newRow.pad(cData.length - newRow.length());
        for (Vec<T, S> c : cData) {
            c.set(row, newRow.get(row));
        }
        rData[row] = newRow;
    }

    /**
     * Set a particular column number to a new Vector
     *
     * @param col    The column number that should be set to a new Vector (first column is 0)
     * @param newCol A Vector that represents the new column
     * @throws OperationUndefinedException
     */
    public void setCol(int col, Vec<T, S> newCol) throws OperationUndefinedException {
        if (newCol.length() >= rData.length) {
            throw new OperationUndefinedException("The vector length is out of range.");
        }
        newCol.pad(rData.length - newCol.length());
        for (int row = 0; row < rData.length; row++) {
            rData[row].set(col, newCol.get(row));
        }
        cData[col] = newCol;
    }

    /**
     * Sets a particular index of the matrix to a new value
     * - (Row/Column positions start with 0)
     *
     * @param row    The row position of the index
     * @param column The column position of the index
     * @param value  The value the position should be set to
     * @throws OperationUndefinedException
     */
    public void set(int row, int column, T value) throws OperationUndefinedException {
        checkBounds("rData", row);
        checkBounds("cData", column);
        rData[row].set(column, value);
        cData[column].set(row, value);
    }

    /**
     * The multiline attribute configures whether the string representation of a matrix
     * should all be displayed on one line, or across many lines (multiline output)
     *
     * @param mline The state the multiline attribute should be set to
     */
    public static void setMultiline(boolean mline) {
        multiline = mline;
    }

    //Other Methods
    protected abstract Field<T> setElementField();

    protected abstract Field<S> setScalarField();

    protected void recorrectMatrix(Vec<T, S>[] matrix) {
        //Find maximum row length
        int maxRowLength = 0;
        for (Vec<T, S> v : matrix) {
            if (v.length() > maxRowLength)
                maxRowLength = v.length();
        }
        //Fill in any empty space to make the matrix take the form of a rectangle
        for (Vec<T, S> r : matrix) {
            r.pad(maxRowLength - r.length());
        }
    }

    protected void applyOperation(Matx<T, S> matrix, MatrixOperation<T> op) throws OperationUndefinedException {
        sameDimensions(matrix);
        for (int row = 0; row < rData.length; row++) {
            for (int col = 0; col < rData[0].length(); col++) {
                set(row, col, op.operation(get(row, col), matrix.get(row, col)));
            }
        }
    }

    protected void sameDimensions(Matx<T, S> matrix) throws OperationUndefinedException {
        if (rData.length != matrix.nrows()) {
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if (cData.length != matrix.ncols()) {
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of columns.");
        }
    }

    protected void checkBounds(String dataType, int... positions) throws OperationUndefinedException {
        Vec<T, S>[] data;
        String message;
        if (dataType.equals("rData")) {
            data = rData;
            message = ROW_NUM_OUT_RANGE;
        } else if (dataType.equals("cData")) {
            data = cData;
            message = COL_NUM_OUT_RANGE;
        } else {
            throw new IllegalArgumentException("String does not correspond to correct data type");
        }
        for (int p : positions) {
            if (p >= data.length || p < 0) {
                throw new OperationUndefinedException(message);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        try {
            if (this == o) {
                return true;
            }
            if (o instanceof Matx oMatx) {
                if (rData.length != oMatx.nrows() || cData.length != oMatx.ncols()) {
                    return false;
                }
                for (int row = 0; row < rData.length; row++) {
                    for (int col = 0; col < rData[0].length(); col++) {
                        if (!get(row, col).equals(oMatx.get(row, col))) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        } catch (OperationUndefinedException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("[");
        int rowNum = 0;
        for (Vec<T, S> v : rData) {
            bld.append(v.toString());
            if (multiline && rowNum < rData.length - 1) {
                bld.append("\n");
            }
            rowNum++;
        }
        bld.append("]");
        return bld.toString();
    }

    protected void constructCData(Vec<T, S>[] emptyCData) {
        try {
            for (int col = 0; col < rData[0].length(); col++) {
                for (int row = 0; row < rData.length; row++) {
                    emptyCData[col].set(row, rData[row].get(col));
                }
            }
            cData = emptyCData;
        } catch (OperationUndefinedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void constructRData(Vec<T, S>[] emptyRData) {
        try {
            for (int row = 0; row < cData[0].length(); row++) {
                for (int col = 0; col < cData.length; col++) {
                    emptyRData[row].set(col, cData[col].get(row));
                }
            }
            cData = emptyRData;
        } catch (OperationUndefinedException e) {
            throw new RuntimeException(e);
        }

    }

}
