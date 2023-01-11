package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.OperationUndefinedException;

public class CMatrix extends RealMatx<Complex> {

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
    public void add(Mat<Complex, Double, RealField> matrix) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void subtract(Mat<Complex, Double, RealField> matrix) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Mat<Complex, Double, RealField> multiply(Mat<Complex, Double, RealField> matrix)
            throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vec_Old<Complex, Double, RealField> multiply(Vec_Old<Complex, Double, RealField> v)
            throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mat<Complex, Double, RealField> subMatrix(int startRow, int endRow, int startCol, int endCol)
            throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mat<Complex, Double, RealField> copy() {
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

    public Matrix toReal(){
        //TO BE IMPLEMENTED
    }
    
}
