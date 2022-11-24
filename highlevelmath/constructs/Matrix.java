package highlevelmath.constructs;

public class Matrix {

    // protected double[][] data;
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

    //Operations between Matrices
    public void add(Matrix matrix) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 + d2;};
        applyOperation(matrix, function);
    }

    public void subtract(Matrix matrix) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 - d2;};
        applyOperation(matrix, function);
    }

    // public void multiply(Matrix matrix) throws OperationUndefinedException{
    //     MatrixOperation function = (d1, d2) -> {return d1 * d2;};
    //     applyOperation(matrix, function);
    // }

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
            throw new OperationUndefinedException("The rows are out of range.");
        }
        //Row 1 = Row 1 + Row 2
        for(int col = 0; col < data[row1].getLength(); col++){
            data[row1].set(col, data[row1].get(col) + data[row2].get(col));
        }
    }

    public void addColumns(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
            throw new OperationUndefinedException("The columns are out of range.");
        }
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data.length; row++){
            data[row].set(col1, data[row].get(col1) + data[row].get(col2));
        }
    }

    public void subtractRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException("The rows are out of range.");
        }
        //Row 1 = Row 1 - Row 2
        for(int col = 0; col < data[row1].getLength(); col++){
            data[row1].set(col, data[row1].get(col) - data[row2].get(col));
        }
    }

    public void subtractColumns(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
            throw new OperationUndefinedException("The columns are out of range.");
        }
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data.length; row++){
            data[row].set(col1, data[row].get(col1) - data[row].get(col2));
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
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
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
        data[rowNum].scale(factor);
    }

    public void scaleColumn(int columnNum, double factor) throws OperationUndefinedException{
        if(columnNum >= data[0].getLength()){
            throw new OperationUndefinedException("The columns are out of range.");
        }
        for(int i = 0; i < data.length; i++){
            data[i].set(columnNum, data[i].get(columnNum)*factor);
        }
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
            throw new OperationUndefinedException("The row number is out of range.");
        } else if(column > data[0].getLength()){
            throw new OperationUndefinedException("The col number is out of range.");
        }
        data[row].set(column, value);
    }

    public void set(int row, int column, int value) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException("The row number is out of range.");
        } else if(column > data[0].getLength()){
            throw new OperationUndefinedException("The col number is out of range.");
        }
        data[row].set(column, (double)value);
    }

    public void setMultiline(boolean multiline){
        this.multiline = multiline;
    }

    //Getter Methods
    public Vector getRow(int rowNum) throws OperationUndefinedException{
        if(rowNum >= data.length){
            throw new OperationUndefinedException("The row number is out of range.");
        }
        double[] row = new double[data[0].getLength()];
        for(int columnNum = 0; columnNum < data[0].getLength(); columnNum++){
            row[columnNum] = data[rowNum].get(columnNum);
        }
        return new Vector(row);
    }

    public Vector getColumn(int columnNum) throws OperationUndefinedException{
        if(columnNum >= data[0].getLength()){
            throw new OperationUndefinedException("The col number is out of range.");
        }
        double[] column = new double[data.length];
        for(int rowNum = 0; rowNum < data.length; rowNum++){
            column[rowNum] = data[rowNum].get(columnNum);
        }
        return new Vector(column);
    }

    public double get(int row, int col) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException("The row number is out of range.");
        } else if(col > data[0].getLength()){
            throw new OperationUndefinedException("The col number is out of range.");
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

    protected void applyOperation(Matrix matrix, MatrixOperation Operation) throws OperationUndefinedException{
        if(data.length != matrix.getNumRows()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if(data[0].getLength() != matrix.getNumCols()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of columns.");
        }
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].getLength(); col++){
                data[row].set(col, Operation.operation(data[row].get(col), matrix.get(row, col)));
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
