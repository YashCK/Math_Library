package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public abstract class Matx<T extends Field<T>, S extends Field<S>> {

    protected static final String ROW_NUM_OUT_RANGE = "The row number is out of range.";
    protected static final String COL_NUM_OUT_RANGE = "The col number is out of range.";

    protected Vec<T, S>[] rData, cData;
    protected static boolean multiline = true;

    //Constructors

    /**
     * A constructor for Matrix class
     *
     * @param vectors An array of Vector objects that represent the rows of the matrix
     */
    public Matx(Vec<T, S>... vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("At least one value is required");
        }
        rData = Arrays.copyOf(vectors, vectors.length);
        recorrectMatrix(rData);
        constructCData(new Vec[rData[0].length()]);
    }

    public Matx(T[][] matrix) {
        rData = new Vec[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rData[i] = createVec(matrix[i]);
        }
        recorrectMatrix(rData);
        constructCData(new Vec[rData[0].length()]);
    }

    /**
     * A constructor for Matrix class
     *
     * @param vectors  An array of Vector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public Matx(boolean asColumn, Vec<T, S>... vectors) {
        if (asColumn) {
            cData = Arrays.copyOf(vectors, vectors.length);
            recorrectMatrix(cData);
            constructRData(new Vector[cData[0].length()]);
        } else {
            rData = Arrays.copyOf(vectors, vectors.length);
            recorrectMatrix(rData);
            constructCData(new Vector[rData[0].length()]);
        }
    }

    /**
     * A constructor for Matrix class
     *
     * @param matrix   A 2D array of doubles that represent the structure of the matrix
     * @param asColumn Whether to interpret each array as a column vector or not
     */
    public Matx(boolean asColumn, T[][] matrix) {
        if (asColumn) {
            cData = new Vec[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                cData[i] = createVec(matrix[i]);
            }
            recorrectMatrix(cData);
            constructRData(new Vec[cData[0].length()]);
        } else {
            rData = new Vec[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                rData[i] = createVec(matrix[i]);
            }
            recorrectMatrix(rData);
            constructCData(new Vec[rData[0].length()]);
        }
    }

    //Operations between Matrices

    /**
     * Operation to add two matrices to one another
     * - Each entry will be added to the corresponding entry in the input Matrix
     *
     * @param matrix Matrix object that should be added
     */
    public void add(Matx<T, S> matrix) {
        sameDimensions(matrix);
        for (int row = 0; row < rData.length; row++) {
            for (int col = 0; col < rData[0].length(); col++) {
                get(row, col).add(matrix.get(row, col));
            }
        }
    }

    /**
     * Operation to subtract two matrices to one another
     * - Each entry will subtract the corresponding entry in the input Matrix
     *
     * @param matrix Matrix object that should be subtracted
     */
    public void subtract(Matx<T, S> matrix) {
        sameDimensions(matrix);
        for (int row = 0; row < rData.length; row++) {
            for (int col = 0; col < rData[0].length(); col++) {
                get(row, col).subtract(matrix.get(row, col));
            }
        }
    }

    /**
     * Operation to multiply two matrices by one another
     *
     * @param matrix Matrix object that should be multiplied
     * @return a new Matrix that is the product of the two previous matrices
     */
    public abstract Matx<T, S> multiply(Matx<T, S> matrix);

    /**
     * Operation to multiply a matrix by a vector
     *
     * @param v Vector object that should be multiplied
     * @return a new Vector that is the product of the matrix and vector parameter
     */
    public abstract Vec<T, S> multiply(Vec<T, S> v);

    //Manipulate Matrix

    /**
     * Adds row 2 to row 1 of the matrix
     * - (Row positions start with 0)
     *
     * @param row1 The row to be added to
     * @param row2 The row that will be added
     */
    public void addRows(int row1, int row2) {
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
     */
    public void addCols(int col1, int col2) {
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
     */
    public void subtractRows(int row1, int row2) {
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
     */
    public void subtractCols(int col1, int col2) {
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
     */
    public void interchangeRows(int row1, int row2) {
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
     */
    public void interchangeCols(int col1, int col2) {
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
     */
    public void scaleRow(int rowNum, S factor) {
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
     */
    public void scaleColumn(int colNum, S factor) {
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
     */
    public abstract Matx<T, S> subMatrix(int startRow, int endRow, int startCol, int endCol);

    public void transpose() {
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
     */
    public Vec<T, S> getRow(int num) {
        return rData[num];
    }

    /**
     * Returns a particular column from the matrix
     *
     * @param num The column number to get (first column is 0)
     * @return A new Vector with the contents of the column chosen from the matrix
     */
    public Vec<T, S> getCol(int num) {
        return cData[num];
    }

    public Vec<T, S> copyRow(int num) {
        return rData[num].copy();
    }

    public Vec<T, S> copyCol(int num) {
        return cData[num].copy();
    }

    /**
     * Get the value at a particular index of the matrix
     * - (Row/Column positions start with 0)
     *
     * @param row The row position of the index
     * @param col The column position of the index
     * @return The value at the index
     */
    public T get(int row, int col) {
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
     */
    public void setRow(int row, Vec<T, S> newRow) {
        if (newRow.length() > cData.length) {
            throw new RuntimeException("The vector length is out of range.");
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
     */
    public void setCol(int col, Vec<T, S> newCol) {
        if (newCol.length() > rData.length) {
            throw new RuntimeException("The vector length is out of range.");
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
     */
    public void set(int row, int column, T value) {
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

    //Miscellaneous

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

    protected void sameDimensions(Matx<T, S> matrix) {
        if (rData.length != matrix.nrows()) {
            throw new RuntimeException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if (cData.length != matrix.ncols()) {
            throw new RuntimeException("This operation cannot be applied to matrices with different numbers of columns.");
        }
    }

    protected void checkBounds(String dataType, int... positions) {
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
                throw new RuntimeException(message);
            }
        }
    }

    protected abstract Vec<T, S> createVec(T[] values);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Matx oMatx) {
            if (rData.length != oMatx.nrows() || cData.length != oMatx.ncols()) {
                return false;
            }
            for (int row = 0; row < rData.length; row++) {
                if (!rData[row].equals(oMatx.rData[row])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object[]) rData);
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

    protected void constructCData(Vec[] emptyCData) {
        for (int col = 0; col < rData[0].length(); col++) {
            T[] vals = (T[]) Array.newInstance(rData[0].get(0).getClass(), rData.length);
            for (int row = 0; row < rData.length; row++) {
                vals[row] = rData[row].get(col);
            }
            emptyCData[col] = createVec(vals);
        }
        cData = emptyCData;
    }

    protected void constructRData(Vec[] emptyRData) {
        for (int row = 0; row < cData[0].length(); row++) {
            T[] vals = (T[]) Array.newInstance(cData[0].get(0).getClass(), cData.length);
            for (int col = 0; col < cData.length; col++) {
                vals[col] = cData[col].get(row);
            }
            emptyRData[row] = createVec(vals);
        }
        rData = emptyRData;
    }

}
