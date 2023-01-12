package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.ComplexField;
import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.OperationUndefinedException;

public class CMatrix extends Matx<Complex, Complex> {

    //Constructors

    /**
     * A constructor for Matrix class
     * @param matrix An array of CVector objects that represent the rows of the matrix
     */
    public CMatrix(CVector[] matrix){
        recorrectMatrix(matrix);
        this.data = matrix;
    }

    /**
     * A constructor for Matrix class
     * @param matrix An array of Vector objects that represent the rows of the matrix
     */
    public CMatrix(Vector[] matrix){
        CVector[] array = new CVector[matrix.length];
        for(int i = 0; i < array.length; i++){
            array[i] = matrix[i].toComplex();
        }
        recorrectMatrix(array);
        this.data = array;
    }

    /**
     * A constructor for Matrix class
     * @param matrix An 2d array of Complex objects that represent the rows of the matrix
     */
    public CMatrix(Complex[][] matrix){
        CVector[] array = new CVector[matrix.length];
        for(int i = 0; i < array.length; i++){
            array[i] = new CVector(matrix[i]);
        }
        recorrectMatrix(array);
        this.data = array;
    }

    /**
     * A constructor for Matrix class
     * @param matrix An array of CVector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(CVector[] matrix, boolean asColumn){
        if(asColumn){
            int maxRowLength = 0;
            for(CVector v : matrix){
                if(v.length() > maxRowLength)
                    maxRowLength = v.length();
            }
            CVector[] newMat = new CVector[maxRowLength];
            try {
                for(int r = 0; r < maxRowLength; r++){
                    Complex[] rowVec = new Complex[matrix.length];
                    for(int c = 0; c < matrix.length; c++){
                        rowVec[c] = matrix[c].get(r);
                    }
                    newMat[r] = new CVector(rowVec);
                }
            } catch (OperationUndefinedException e) {
                e.printStackTrace();
            }
            matrix = newMat;
        }
        recorrectMatrix(matrix);
        this.data = matrix;
    }

    /**
     * A constructor for Matrix class
     * @param matrix An array of Vector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(Vector[] matrix, boolean asColumn){
        CVector[] convMatrix = new CVector[matrix.length];
        if(asColumn){
            int maxRowLength = 0;
            for(Vector v : matrix){
                if(v.length() > maxRowLength)
                    maxRowLength = v.length();
            }
            CVector[] newMat = new CVector[maxRowLength];
            try {
                for(int r = 0; r < maxRowLength; r++){
                    Double[] rowVec = new Double[matrix.length];
                    for(int c = 0; c < matrix.length; c++){
                        rowVec[c] = matrix[c].get(r);
                    }
                    Vector temp = new Vector(rowVec);
                    newMat[r] = temp.toComplex();
                }
            } catch (OperationUndefinedException e) {
                e.printStackTrace();
            }
            convMatrix = newMat;
        } else {
            for(int i = 0; i < convMatrix.length; i++){
                convMatrix[i] = matrix[i].toComplex();
            }
        }
        recorrectMatrix(convMatrix);
        this.data = convMatrix;
    }

    /**
     * A constructor for Matrix class
     * @param matrix An 2d array of Complex objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(Complex[][] matrix, boolean asColumn){
        CVector[] convMatrix = new CVector[matrix.length];
        if(asColumn){
            int maxRowLength = 0;
            for(Complex[] cv : matrix){
                if(cv.length > maxRowLength)
                    maxRowLength = cv.length;
            }
            CVector[] newMat = new CVector[maxRowLength];
            for(int r = 0; r < maxRowLength; r++){
                Complex[] rowVec = new Complex[matrix.length];
                for(int c = 0; c < matrix.length; c++){
                    rowVec[c] = matrix[c][r];
                }
                newMat[r] = new CVector(rowVec);
            }
            convMatrix = newMat;
        } else {
            for(int i = 0; i < convMatrix.length; i++){
                convMatrix[i] = new CVector(matrix[i]);
            }
        }
        recorrectMatrix(convMatrix);
        this.data = convMatrix;
    }

    @Override
    public Matx<Complex, Complex> multiply(Matx<Complex, Complex> matrix) throws OperationUndefinedException {
        if(this.ncols() != matrix.nrows())
            throw new OperationUndefinedException("The columns of matrix 1 must equal the number of rows of matrix 2.");
        Complex[][] newM = new Complex[this.nrows()][matrix.ncols()];
        for(int row = 0; row < data.length; row++){
            CVector v = this.getRow(row);
            for(int col = 0; col < matrix.ncols(); col++){
                newM[row][col] = v.dot(matrix.getCol(col));
            }
        }
        return new CMatrix(newM);
    }

    @Override
    public Vec<Complex, Complex> multiply(Vec<Complex, Complex> v) throws OperationUndefinedException {
        if(this.ncols() != v.length())
            throw new OperationUndefinedException("The columns of the matrix must equal the length of the vector.");
        Complex[] newV = new Complex[data.length];
        for(int row = 0; row < data.length; row++){
            newV[row] = data[row].dot(v);
        }
        return new CVector(newV);
    }

    @Override
    public Matx<Complex, Complex> subMatrix(int startRow, int endRow, int startCol, int endCol)
            throws OperationUndefinedException {
        if(startRow < 0 || endRow > data.length || startCol < 0 || endCol > data[0].length())
            throw new OperationUndefinedException("A row or column parameter was out of the matrix's range.");
        Complex[][] sub = new Complex[endRow - startRow + 1][endCol - startCol + 1];
        for(int i = startRow; i <= endRow; i++){
            for(int j = startCol; j <= endCol; j++){
                sub[i][j] = this.get(i, j);
            }
        }
        return new CMatrix(sub);
    }

    @Override
    public Matx<Complex, Complex> copy() {
        CVector[] copy = new CVector[data.length];
        for(int i = 0; i < data.length; i++){
            copy[i] = (CVector)data[i];
        }
        return new CMatrix(copy);
    }

    @Override
    public CVector getRow(int num) throws OperationUndefinedException {
        if(num >= data.length)
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        Complex[] row = new Complex[data[0].length()];
        for(int cNum = 0; cNum < data[0].length(); cNum++){
            row[cNum] = data[num].get(cNum);
        }
        return new CVector(row);
    }

    @Override
    public CVector getCol(int num) throws OperationUndefinedException {
        if(num >= data[0].length())
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        Complex[] column = new Complex[data.length];
        for(int rowNum = 0; rowNum < data.length; rowNum++){
            column[rowNum] = data[rowNum].get(num);
        }
        return new CVector(column);
    }

    @Override
    protected Field<Complex> setElementField() {
        return new ComplexField();
    }

    @Override
    protected Field<Complex> setScalarField() {
        return new ComplexField();
    }

    public Matrix getRealComplement(){
        Vector[] v = new Vector[data.length];
        for(int i = 0; i < data.length; i++){
            v[i] = ((CVector)data[i]).getRealComplement();
        }
        return new Matrix(v);
    }

    public CMatrix getComplexComplement(){
        CVector[] v = new CVector[data.length];
        for(int i = 0; i < data.length; i++){
            v[i] = ((CVector)data[i]).getComplexComplement();
        }
        return new CMatrix(v);
    }
    
    public Matrix toReal() throws OperationUndefinedException{
        if(isComplex())
            throw new OperationUndefinedException("The Matrix is Complex. It cannot be converted to a Real Matrix.");
        return getRealComplement();
    }

    public boolean isComplex(){
        for(Vec<Complex, Complex> c: data){
            if(!((CVector)c).isComplex())
                return false;
        }
        return true;
    }
    
}
