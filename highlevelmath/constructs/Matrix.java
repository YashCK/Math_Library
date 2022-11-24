package highlevelmath.constructs;

public class Matrix {

    protected double[][] data;
    protected boolean multiline;

    public Matrix(double[][] matrix){
        this.data = recorrectMatrix(matrix);
        this.multiline = false;
    }

    public Matrix(int[][] matrix){
        this.data = recorrectMatrix(matrix);
        this.multiline = false;
    }

    public Matrix(double[][] matrix, boolean multiline){
        this.data = recorrectMatrix(matrix);
        this.multiline = multiline;
    }

    public Matrix(){
        double[][] matrix = {{0}};
        this.data = matrix;
        this.multiline = false;
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
        if(matrix.contains(0)){
            throw new OperationUndefinedException("This operation cannot be applied to input matrices with value 0.");
        }
        MatrixOperation function = (d1, d2) -> {return d1 % d2;};
        applyOperation(matrix, function);
    }

    @Override
    public boolean equals(Object o){
        try {
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((Matrix)o).getNumRows() || data[0].length != ((Matrix)o).getNumCols()){
                return false;
            }
            for(int row = 0; row < data.length; row++){
                for(int col = 0; col < data[0].length; col++){
                    if(data[row][col] != ((Matrix)o).get(row, col)){
                        return false;
                    }
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

    //Methods to Manipulate Matrix
    public void addRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException("The rows are out of range.");
        }
        //Row 1 = Row 1 + Row 2
        for(int col = 0; col < data[row1].length; col++){
            data[row1][col] += data[row2][col];
        }
    }

    public void addColumns(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].length || col2 >= data[0].length){
            throw new OperationUndefinedException("The columns are out of range.");
        }
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data.length; row++){
            data[row][col1] += data[row][col2];
        }
    }

    public void subtractRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException("The rows are out of range.");
        }
        //Row 1 = Row 1 - Row 2
        for(int col = 0; col < data[row1].length; col++){
            data[row1][col] -= data[row2][col];
        }
    }

    public void subtractColumns(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].length || col2 >= data[0].length){
            throw new OperationUndefinedException("The columns are out of range.");
        }
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data.length; row++){
            data[row][col1] -= data[row][col2];
        }
    }

    public void interchangeRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException("The rows are out of range.");
        }
        Vector firstRow = getRow(row1);
        setRow(row1, getRow(row2));
        setRow(row2, firstRow);
    }

    public void interchangeCols(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].length || col2 >= data[0].length){
            throw new OperationUndefinedException("The columns are out of range.");
        }
        Vector firstCol = getColumn(col1);
        Vector secondCol = getColumn(col2);
        setColumn(col1, secondCol);
        setColumn(col2, firstCol);
    }

    public void scaleRow(int rowNum, double factor) throws OperationUndefinedException{
        if(rowNum >= data.length){
            throw new OperationUndefinedException("The rows are out of range.");
        }
        for(int i = 0; i < data[rowNum].length; i++){
            data[rowNum][i] *= factor;
        }
    }

    public void scaleColumn(int columnNum, double factor) throws OperationUndefinedException{
        if(columnNum >= data[0].length){
            throw new OperationUndefinedException("The columns are out of range.");
        }
        for(int i = 0; i < data.length; i++){
            data[i][columnNum] *= factor;
        }
    }

    // Setter Methods
    public void setRow(int rowNum, double[] newRow) throws OperationUndefinedException{
        if(newRow.length != data[0].length){
            throw new OperationUndefinedException("The new row length is out of range.");
        }
        newRow = recorrectArray(newRow, true);
        for(int col = 0; col < data[rowNum].length; col++){
            data[rowNum][col] = newRow[col];
        }
    }

    public void setRow(int rowNum, Vector newRow) throws OperationUndefinedException{
        if(newRow.getLength() != data[0].length){
            throw new OperationUndefinedException("The vector length is out of range.");
        }
        newRow.recorrect(data[0].length - newRow.getLength());
        for(int col = 0; col < data[rowNum].length; col++){
            data[rowNum][col] = newRow.get(col);
        }
    }

    public void setRow(int rowNum, int[] newRow) throws OperationUndefinedException{
        if(newRow.length >= data[0].length){
            throw new OperationUndefinedException("The new row length is out of range.");
        }
        double[] newArr = recorrectArray(newRow, true);
        for(int col = 0; col < data[rowNum].length; col++){
            data[rowNum][col] = newArr[col];
        }
    }

    public void setColumn(int colNum, double[] newCol) throws OperationUndefinedException{
        if(newCol.length >= data.length){
            throw new OperationUndefinedException("The new column length is out of range.");
        }
        newCol = recorrectArray(newCol, false);
        for(int row = 0; row < data.length; row++){
            data[row][colNum] = newCol[row];
        }
    }

    public void setColumn(int colNum, Vector newCol) throws OperationUndefinedException{
        if(newCol.getLength() >= data.length){
            throw new OperationUndefinedException("The vector length is out of range.");
        }
        newCol.recorrect(data.length - newCol.getLength());
        for(int row = 0; row < data.length; row++){
            data[row][colNum] = newCol.get(row);
        }
    }

    public void setColumn(int colNum, int[] newCol) throws OperationUndefinedException{
        if(newCol.length >= data.length){
            throw new OperationUndefinedException("The new column length is out of range.");
        }
        double[] newArr = recorrectArray(newCol, true);
        for(int row = 0; row < data.length; row++){
            data[row][colNum] = newArr[row];
        }
    }

    public void set(int row, int column, double value) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException("The row number is out of range.");
        } else if(column > data[0].length){
            throw new OperationUndefinedException("The col number is out of range.");
        }
        data[row][column] = value;
    }

    public void set(int row, int column, int value) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException("The row number is out of range.");
        } else if(column > data[0].length){
            throw new OperationUndefinedException("The col number is out of range.");
        }
        data[row][column] = (double)value;
    }

    public void setMultiline(boolean multiline){
        this.multiline = multiline;
    }

    //Getter Methods
    public Vector getRow(int rowNum) throws OperationUndefinedException{
        if(rowNum >= data.length){
            throw new OperationUndefinedException("The row number is out of range.");
        }
        double[] row = new double[data[0].length];
        for(int columnNum = 0; columnNum < data[0].length; columnNum++){
            row[columnNum] = data[rowNum][columnNum];
        }
        return new Vector(row);
    }

    public Vector getColumn(int columnNum) throws OperationUndefinedException{
        if(columnNum >= data[0].length){
            throw new OperationUndefinedException("The col number is out of range.");
        }
        double[] column = new double[data.length];
        for(int rowNum = 0; rowNum < data.length; rowNum++){
            column[rowNum] = data[rowNum][columnNum];
        }
        return new Vector(column);
    }

    public double get(int row, int col) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException("The row number is out of range.");
        } else if(col > data[0].length){
            throw new OperationUndefinedException("The col number is out of range.");
        }
        return data[row][col];
    }

    public int getNumRows(){
        return data.length;
    }

    public int getNumCols(){
        return data[0].length;
    }

    public boolean getMultiline(){
        return multiline;
    }

    public boolean contains(double value){
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].length; col++){
                if(data[row][col] == value){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean contains(int value){
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].length; col++){
                if(data[row][col] == value){
                    return true;
                }
            }
        }
        return false;
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
                if(column < matrix[row].length){
                    cMatrix[row][column] = matrix[row][column];
                } else {
                    cMatrix[row][column] = 0;
                }  
            }
        }
        return cMatrix;
    }

    private double[][] recorrectMatrix(int[][] matrix){
        //Find maximum row length
        int maxRowLength = 0;
        for(int[] row : matrix){
            if(row.length > maxRowLength){
                maxRowLength = row.length;
            }
        }
        //Matrix to be returned
        double[][] cMatrix = new double[matrix.length][maxRowLength];
        //Fill in any empty space to make the matrix take the form of a rectangle
        for(int row = 0; row < matrix.length; row++){
            for(int column = 0; column < maxRowLength; column++){
                if(column < matrix[row].length){
                    cMatrix[row][column] = matrix[row][column];
                } else {
                    cMatrix[row][column] = 0;
                }  
            }
        }
        return cMatrix;
    }

    private double[] recorrectArray(double[] array, boolean row){
        int cRowLength;
        if(row){
            cRowLength = data[0].length;
        } else {
            cRowLength = data.length;
        }
        double[] cRow = new double[cRowLength];
        for(int i = 0; i < cRowLength; i++){
            if(i < array.length){
                cRow[i] = array[i];
            } else {
                cRow[i] = 0;
            }
        }
        return cRow;
    }

    private double[] recorrectArray(int[] array, boolean row){
        int cRowLength;
        if(row){
            cRowLength = data[0].length;
        } else {
            cRowLength = data.length;
        }
        double[] cRow = new double[cRowLength];
        for(int i = 0; i < cRowLength; i++){
            if(i < array.length){
                cRow[i] = array[i];
            } else {
                cRow[i] = 0;
            }
        }
        return cRow;
    }

    protected void applyOperation(Matrix matrix, MatrixOperation Operation) throws OperationUndefinedException{
        if(data.length != matrix.getNumRows()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if(data[0].length != matrix.getNumCols()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of columns.");
        }
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].length; col++){
                data[row][col] = Operation.operation(data[row][col], matrix.get(row, col));
            }
        }
    }

    @Override
    public String toString() {
        String str = "[";
        int rowNum = 0;
        for(double[] row : data){
            str += "[";
            int index = 0;
            for(double element: row){
                str += (index == 0) ? "" : ", ";
                str += truncateDecimal(element, 2);
                index++;
            }
            str += "]";  
            if(multiline && rowNum < data.length - 1){
                str += "\n ";
            }
            rowNum++;
        }
        str += "]";
        return str;
    }

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }
       
}
