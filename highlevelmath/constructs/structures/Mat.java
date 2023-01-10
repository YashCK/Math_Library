package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.OperationUndefinedException;

/**
 * This class creates a represntation of a matrix that can be used to store data
 * and perform operations upon. Matrices will be especially useful for operations in
 * linear algebra and similar areas. 
  * <pre>
    *T is the type of the object stored in the Matrix
    *F is the Field of scalars of type S that is associated with the Vector Space.
    *Vec<T, S, F>is the type of Vector of each row and column of the Matrix
 *  </pre>
 */
public interface Mat<T, S, F extends Field<S>> {

    //Operations between Matrices

    /**
     * Operation to add two matrices to one another
     *  - Each entry will be added to the corresponding entry in the input Matrix
     * @param matrix Matrix object that should be added
     * @throws OperationUndefinedExceptfion
     */
    public abstract void add(Mat<T, S, F> matrix) throws OperationUndefinedException;

    /**
     * Operation to subtract two matrices to one another
     *  - Each entry will subtract the corresponding entry in the input Matrix
     * @param matrix Matrix object that should be subtracted
     * @throws OperationUndefinedException
     */
    public abstract void subtract(Mat<T, S, F> matrix) throws OperationUndefinedException;

    /**
     * Operation to multiply two matrices by one another
     * @param matrix Matrix object that should be multiplied
     * @return a new Matrix that is the product of the two previous matrices
     * @throws OperationUndefinedException
     */
    public abstract Mat<T, S, F> multiply(Mat<T, S, F> matrix) throws OperationUndefinedException;

    /**
     * Operation to multiply a matrix by a vector
     * @param v Vector object that should be multiplied
     * @return a new Vector that is the product of the matrix and vector parameter
     * @throws OperationUndefinedException
     */
    public abstract Vec<T, S, F>multiply(Vec<T, S, F> v) throws OperationUndefinedException;

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
    public abstract Mat<T, S, F> subMatrix(int startRow, int endRow, int startCol, int endCol) throws OperationUndefinedException;

    /**
     * Adds row 2 to row 1 of the matrix
     *  - (Row positions start with 0)
     * @param row1 The row to be added to
     * @param row2 The row that will be added
     * @throws OperationUndefinedException
     */
    void addRows(int row1, int row2) throws OperationUndefinedException;

    /**
     * Adds column 2 to column 1 of the matrix
     *  - (Column positions start with 0)
     * @param col1 The column to be added to
     * @param col2 The column that will be added
     * @throws OperationUndefinedException
     */
    void addColumns(int col1, int col2) throws OperationUndefinedException;

    /**
     * Subtracts row 2 from row 1 of the matrix
     *  - (Row positions start with 0)
     * @param row1 The row that will be subtracted from
     * @param row2 The row that will be subtracted
     * @throws OperationUndefinedException
     */
    void subtractRows(int row1, int row2) throws OperationUndefinedException;
    /**
     * Subtracts column 2 from column 1 of the matrix
     *  - (Column positions start with 0)
     * @param col1 The column that will be subtracted from
     * @param col2 The column that will be subtracted
     * @throws OperationUndefinedException
     */
    void subtractColumns(int col1, int col2) throws OperationUndefinedException;

    /**
     * Interchange two rows of the matrix
     *  - (Row positions start with 0)
     * @param row1 A row number that will exchange positions with another row
     * @param row2 A row number that will exchange positions with another row
     * @throws OperationUndefinedException
     */
    void interchangeRows(int row1, int row2) throws OperationUndefinedException;

    /**
     * Interchange two columns of the matrix
     *   - (Column positions start with 0)
     * @param col1 A column number that will exchange positions with another column
     * @param col2 A column number that will exchange positions with another column
     * @throws OperationUndefinedException
     */
    void interchangeCols(int col1, int col2) throws OperationUndefinedException;

    /**
     * Scale a row of the matrix by a factor
     * @param rowNum The row number to be scaled (first row is 0)
     * @param factor The factor by which the row is scaled
     * @throws OperationUndefinedException
     */
    void scaleRow(int rowNum, S factor) throws OperationUndefinedException;

    /**
     * Scale a column of the matrix by a factor
     * @param columnNum The column number (first column is 0)
     * @param factor The factor by which the column is scaled
     * @throws OperationUndefinedException
     */
    void scaleColumn(int columnNum, S factor) throws OperationUndefinedException;

    /**
     * Copy the contents of the matrix into a new Matrix
     * @return a new Matrix
     */
    public abstract Mat<T, S, F> copy();

    //Getter Methods

    /**
     * Get the number of rows
     * @return number of rows in the matrix
     */
    int nrows();

    /**
     * Get the number of columns
     * @return number of columns in the matrix
     */
    int ncols();

    /**
     * Get a particular row from the matrix
     * @param rowNum The row number to get (first row is 0)
     * @return A new Vector with the contents of the row chosen from the matrix
     * @throws OperationUndefinedException
     */
    public abstract Vec<T, S, F> getRow(int num) throws OperationUndefinedException;

    /**
     * Get a particular column from the matrix
     * @param columnNum The column number to get (first column is 0)
     * @return A new Vector with the contents of the column chosen from the matrix
     * @throws OperationUndefinedException
     */
    public abstract Vec<T, S, F> getCol(int num) throws OperationUndefinedException;
    
    /**
     * Get the value at a particular index of the matrix
     *  - (Row/Column positions start with 0)
     * @param row The row position of the index
     * @param col The column positoin of the index
     * @return The value at the index
     * @throws OperationUndefinedException
     */
    T get(int row, int col) throws OperationUndefinedException;

    /**
     * Returns whether a particular value is present within the matrix or not
     * @param value The value to search for | MUST be the type of values stored in the Vector
     * @return True if value is in the matrix, False otherwise
     * @throws OperationUndefinedException
     */
    boolean contains(T value) throws OperationUndefinedException;

    //Setter Methods

    /**
     * Set a particular row number to a new Vector
     * @param rowNum The row number that should be set to a new Vector (first row is 0)
     * @param newRow A Vector that represents the new row
     * @throws OperationUndefinedException
     */
    void setRow(int rowNum, Vec<T, S, F>newRow) throws OperationUndefinedException;

    /**
     * Set a particular column number to a new Vector
     * @param colNum The column number that should be set to a new Vector (first column is 0)
     * @param newCol A Vector that represents the new column
     * @throws OperationUndefinedException
     */
    void setCol(int colNum, Vec<T, S, F>newCol) throws OperationUndefinedException;

    /**
     * Sets a particular index of the matrix to a new value
     *  - (Row/Column positions start with 0)
     * @param row The row position of the index
     * @param column The column position of the index
     * @param value The value the position should be set to
     * @throws OperationUndefinedException
     */
    void set(int row, int column, T value) throws OperationUndefinedException;

    //Other Methods
    @Override
    boolean equals(Object o);

    @Override
    String toString();
    
}