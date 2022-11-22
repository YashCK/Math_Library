package highlevelmath.constructs;

public class Matrix {

    protected double[][] data;

    public Matrix(double[][] matrix){
        this.data = recorrectMatrix(matrix);
    }

    //Operations between Matrices
    public void add(Matrix matrix) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 + d2;};
        applyOperation(matrix, function);
    }

    public void subtract(Matrix matrix) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 - d2;};
        applyOperation(matrix, function);
    }

    public void multiply(Matrix matrix) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 * d2;};
        applyOperation(matrix, function);
    }

    public void modulus(Matrix matrix) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 % d2;};
        applyOperation(matrix, function);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        if(data.length != ((Matrix)o).getNumRows() || data[0].length != ((Matrix)o).getNumCols()){
            return false;
        }
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].length; col++){
                if(data[row][col] == ((Matrix)o).getIndex(row, col)){
                    return false;
                }
            }
        }
        return true;
    }

    //Methods to Manipulate Matrix
    public void addRows(int row1, int row2){
        //Row 1 = Row 1 + Row 2
        for(int col = 0; col < data[row1].length; col++){
            data[row1][col] += data[row2][col];
        }
    }

    public void addColumns(int col1, int col2){
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data[0].length; row++){
            data[row][col1] += data[row][col2];
        }
    }

    public void subtractRows(int row1, int row2){
        //Row 1 = Row 1 - Row 2
        for(int col = 0; col < data[row1].length; col++){
            data[row1][col] -= data[row2][col];
        }
    }

    public void subtractColumns(int col1, int col2){
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data[0].length; row++){
            data[row][col1] -= data[row][col2];
        }
    }

    public void interchangeRows(int row1, int row2){
        double[] firstRow = getRow(row1);
        setRow(row1, getRow(row2));
        setRow(row2, firstRow);
    }

    public void interchangeCols(int col1, int col2){
        double[] firstCol = getColumn(col1);
        double[] secondCol = getColumn(col2);
        setColumn(col1, secondCol);
        setColumn(col2, firstCol);
    }

    public void scaleRow(int rowNum, double factor){
        for(int i = 0; i < data[rowNum].length; i++){
            data[rowNum][i] *= factor;
        }
    }

    public void scaleColumn(int columnNum, double factor){
        for(int i = 0; i < data.length; i++){
            data[i][columnNum] *= factor;
        }
    }

    // Setter Methods
    public void setRow(int rowNum, double[] newRow){
        for(int col = 0; col < data[rowNum].length; col++){
            data[rowNum][col] = newRow[col];
        }
    }

    public void setColumn(int colNum, double[] newCol){
        for(int row = 0; row < data.length; row++){
            data[row][colNum] = newCol[row];
        }
    }

    public void setIndex(int row, int column, double value){
        data[row][column] = value;
    }

    //Getter Methods
    public double[] getRow(int rowNum){
        double[] row = new double[data[0].length];
        for(int columnNum = 0; columnNum < data[0].length; columnNum++){
            row[columnNum] = data[rowNum][columnNum];
        }
        return row;
    }

    public double[] getColumn(int columnNum){
        double[] column = new double[data.length];
        for(int rowNum = 0; rowNum < data.length; rowNum++){
            column[rowNum] = data[rowNum][columnNum];
        }
        return column;
    }

    public double getIndex(int row, int col){
        return data[row][col];
    }

    public int getNumRows(){
        return data.length;
    }

    public int getNumCols(){
        return data[0].length;
    }

    //Other Methods
    private double[][] recorrectMatrix(double[][] matrix){
        //Find maximum row length
        int maxRowLength = 0;
        for(double[] row : matrix){
            if(row.length > maxRowLength){
                maxRowLength = row.length;
            }
        }
        //Matrix to be returned
        double[][] cMatrix = new double[matrix.length][maxRowLength];
        //Fill in any empty space to make the matrix take the form of a rectangle
        for(int row = 0; row < matrix.length; row++){
            for(int column = 0; column < maxRowLength; column++){
                if(column > matrix[row].length){
                    cMatrix[row][column] = matrix[row][column];
                } else {
                    cMatrix[row][column] = 0;
                }  
            }
        }
        return cMatrix;
    }

    protected void applyOperation(Matrix matrix, MatrixOperation Operation) throws OperationUndefinedException{
        if(data.length != matrix.getNumRows()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if(data[0].length != matrix.getNumCols()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of columns.");
        }
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].length; col++){
                data[row][col] = Operation.operation(data[row][col], matrix.getIndex(row, col));
            }
        }
    }

    @Override
    public String toString() {
        String str = "[";
        for(double[] row : data){
            str += "[";
            for(double element: row){
                str += element == row[0] ? "" : " ";
                str += truncateDecimal(element, 2);
            }
            str += "]";  
        }
        str += "]";
        return str;
    }

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }
    
    
}
