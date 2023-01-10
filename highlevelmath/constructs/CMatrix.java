package highlevelmath.constructs;

import highlevelmath.constructs.util.OperationUndefinedException;

public class CMatrix extends Mat<Complex, Double, CVector> {

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

    }

    /**
     * A constructor for Matrix class
     * @param matrix An array of CVector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(CVector[] matrix, boolean asColumn){
        
    }

    /**
     * A constructor for Matrix class
     * @param matrix An array of Vector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(Vector[] matrix, boolean asColumn){
        
    }

    /**
     * A constructor for Matrix class
     * @param matrix An 2d array of Complex objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(Complex[][] matrix, boolean asColumn){
        
    }

    @Override
    public void add(Mat<Complex, Double, CVector> matrix) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void subtract(Mat<Complex, Double, CVector> matrix) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Mat<Complex, Double, CVector> multiply(Mat<Complex, Double, CVector> matrix)
            throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CVector multiply(CVector v) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mat<Complex, Double, CVector> subMatrix(int startRow, int endRow, int startCol, int endCol)
            throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addRows(int row1, int row2) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addColumns(int col1, int col2) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void subtractRows(int row1, int row2) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void subtractColumns(int col1, int col2) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void scaleColumn(int columnNum, double factor) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Mat<Complex, Double, CVector> copy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CVector getRow(int num) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CVector getCol(int num) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    public Matrix getRealComplement(){
        //TO BE IMPLEMENTED
    }

    public CMatrix getComplexComplement(){
        //TO BE IMPLEMENTED
    }

    public Matrix toRela(){
        //TO BE IMPLEMENTED
    }
    
}
