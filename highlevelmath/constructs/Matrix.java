package highlevelmath.constructs;

public class Matrix {

    private static final String ROW_OUT_RANGE = "The rows are out of range.";
    private static final String ROW_NUM_OUT_RANGE = "The row number is out of range.";
    private static final String COL_OUT_RANGE = "The columns are out of range.";
    private static final String COL_NUM_OUT_RANGE = "The col number is out of range.";

    protected Vector[] data;
    protected boolean multiline;

    public Matrix(Vector[] matrix){
        recorrectMatrix(matrix);
        this.data = matrix;
        this.multiline = false;
    }

    public Matrix(Vector[] matrix, boolean multiline){
        recorrectMatrix(matrix);
        this.data = matrix;
        this.multiline = multiline;
    }

    public Matrix(double[][] matrix){
        Vector[] array = new Vector[matrix.length];
        int i = 0;
        for(double[] row : matrix){
            Vector v = new Vector(row);
            array[i] = v;
            i++;
        }
        this.data = array;
        this.multiline = false;
    }

    public Matrix(double[][] matrix, boolean multiline){
        Vector[] array = new Vector[matrix.length];
        int i = 0;
        for(double[] row : matrix){
            Vector v = new Vector(row);
            array[i] = v;
            i++;
        }
        this.data = array;
        this.multiline = multiline;
    }

    public Matrix(Vector[] matrix, boolean asColumn, boolean multiline){
        int maxRowLength = 0;
        for(Vector v : matrix){
            if(v.getLength() > maxRowLength){
                maxRowLength = v.getLength();
            }
        }
        try {
            Vector[] newMat = new Vector[maxRowLength];
            if(asColumn){
                for(int col = 0; col < matrix.length; col++){
                    for(int row = 0; row < matrix[col].getLength(); row++){
                        newMat[col].set(row, matrix[col].get(row));
                    }
                }
                recorrectMatrix(newMat);
                this.data = matrix;
            } else {
                recorrectMatrix(matrix);
                this.data = matrix;
            }
            this.multiline = multiline;
        } catch(OperationUndefinedException e){
            e.printStackTrace();
        }
        
    }

    //Operations between Matrices
    public void add(Matrix matrix) throws OperationUndefinedException{
        // MatrixOperation function = (d1, d2) -> {return d1 + d2;};
        MatrixOperation function = (d1, d2) -> d1 + d2;
        applyOperation(matrix, function);
    }

    public void subtract(Matrix matrix) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> d1 - d2;
        applyOperation(matrix, function);
    }

    // public void multiply(Matrix matrix) throws OperationUndefinedException{
        // ;
    // }

    public void modulus(Matrix matrix) throws OperationUndefinedException{
        if(matrix.contains(0)){
            throw new OperationUndefinedException("This operation cannot be applied to input matrices with value 0.");
        }
        MatrixOperation function = (d1, d2) -> d1 % d2;
        applyOperation(matrix, function);
    }

    @Override
    public boolean equals(Object o){
        try {
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((Matrix)o).getNumRows() || data[0].getLength() != ((Matrix)o).getNumCols()){
                return false;
            }
            for(int row = 0; row < data.length; row++){
                for(int col = 0; col < data[0].getLength(); col++){
                    if(data[row].get(col) != ((Matrix)o).get(row, col)){
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
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        }
        //Row 1 = Row 1 + Row 2
        for(int col = 0; col < data[row1].getLength(); col++){
            data[row1].set(col, data[row1].get(col) + data[row2].get(col));
        }
    }

    public void addColumns(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data.length; row++){
            data[row].set(col1, data[row].get(col1) + data[row].get(col2));
        }
    }

    public void subtractRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        }
        //Row 1 = Row 1 - Row 2
        for(int col = 0; col < data[row1].getLength(); col++){
            data[row1].set(col, data[row1].get(col) - data[row2].get(col));
        }
    }

    public void subtractColumns(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data.length; row++){
            data[row].set(col1, data[row].get(col1) - data[row].get(col2));
        }
    }

    public void interchangeRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        }
        Vector firstRow = getRow(row1);
        setRow(row1, getRow(row2));
        setRow(row2, firstRow);
    }

    public void interchangeCols(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        Vector firstCol = getColumn(col1);
        Vector secondCol = getColumn(col2);
        setColumn(col1, secondCol);
        setColumn(col2, firstCol);
    }

    public void scaleRow(int rowNum, double factor) throws OperationUndefinedException{
        if(rowNum >= data.length){
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        }
        data[rowNum].scale(factor);
    }

    public void scaleColumn(int columnNum, double factor) throws OperationUndefinedException{
        if(columnNum >= data[0].getLength()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        for(int i = 0; i < data.length; i++){
            data[i].set(columnNum, data[i].get(columnNum)*factor);
        }
        correctRounding();
    }

    // Setter Methods
    public void setRow(int rowNum, Vector newRow) throws OperationUndefinedException{
        if(newRow.getLength() != data[0].getLength()){
            throw new OperationUndefinedException("The vector length is out of range.");
        }
        newRow.recorrect(data[0].getLength() - newRow.getLength());
        for(int col = 0; col < data[rowNum].getLength(); col++){
            data[rowNum].set(col, newRow.get(col));
        }
    }

    public void setColumn(int colNum, Vector newCol) throws OperationUndefinedException{
        if(newCol.getLength() >= data.length){
            throw new OperationUndefinedException("The vector length is out of range.");
        }
        newCol.recorrect(data.length - newCol.getLength());
        for(int row = 0; row < data.length; row++){
            data[row].set(colNum, newCol.get(row));
        }
    }

    public void set(int row, int column, double value) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        } else if(column > data[0].getLength()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        data[row].set(column, value);
    }

    public void set(int row, int column, int value) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        } else if(column > data[0].getLength()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        data[row].set(column, (double)value);
    }

    public void setMultiline(boolean multiline){
        this.multiline = multiline;
    }

    //Getter Methods
    public Vector getRow(int rowNum) throws OperationUndefinedException{
        if(rowNum >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        }
        double[] row = new double[data[0].getLength()];
        for(int columnNum = 0; columnNum < data[0].getLength(); columnNum++){
            row[columnNum] = data[rowNum].get(columnNum);
        }
        return new Vector(row);
    }

    public Vector getColumn(int columnNum) throws OperationUndefinedException{
        if(columnNum >= data[0].getLength()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        double[] column = new double[data.length];
        for(int rowNum = 0; rowNum < data.length; rowNum++){
            column[rowNum] = data[rowNum].get(columnNum);
        }
        return new Vector(column);
    }

    public double get(int row, int col) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        } else if(col > data[0].getLength()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        return data[row].get(col);
    }

    public int getNumRows(){
        return data.length;
    }

    public int getNumCols(){
        return data[0].getLength();
    }

    public boolean getMultiline(){
        return multiline;
    }

    public boolean contains(double value){
        for(Vector v : data){
            if(v.contains(value)){
                return true;
            }
        }
        return false;
    }
    
    public boolean contains(int value){
        for(Vector v : data){
            if(v.contains(value)){
                return true;
            }
        }
        return false;
    }

    //Other Methods
    private void recorrectMatrix(Vector[] matrix){
        //Find maximum row length
        int maxRowLength = 0;
        for(Vector v : matrix){
            if(v.getLength() > maxRowLength){
                maxRowLength = v.getLength();
            }
        }
        //Fill in any empty space to make the matrix take the form of a rectangle
        for(int row = 0; row < matrix.length; row++){
            matrix[row].recorrect(maxRowLength - matrix[row].getLength());
        }
    }

    public void correctRounding(){
        for(Vector v : data){
            v.correctRounding();
        }
    }

    protected void applyOperation(Matrix matrix, MatrixOperation op) throws OperationUndefinedException{
        if(data.length != matrix.getNumRows()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if(data[0].getLength() != matrix.getNumCols()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of columns.");
        }
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].getLength(); col++){
                data[row].set(col, op.operation(data[row].get(col), matrix.get(row, col)));
            }
        }
    }

    @Override
    public String toString() {
        String str = "[";
        int rowNum = 0;
        for(Vector v : data){
            str += v.toString();
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
