package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.ComplexField;
import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.OperationUndefinedException;

public class CMatrix extends Matx<Complex, Double> {

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
    public Matx<Complex, Double> multiply(Matx<Complex, Double> matrix) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vec<Complex, Double> multiply(Vec<Complex, Double> v) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Matx<Complex, Double> subMatrix(int startRow, int endRow, int startCol, int endCol)
            throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Matx<Complex, Double> copy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vec<Complex, Double> getRow(int num) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vec<Complex, Double> getCol(int num) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Field<Complex> setElementField() {
        return new ComplexField();
    }

    @Override
    protected Field<Double> setScalarField() {
        return new RealField();
    }

    public Vector getRealComplement(){
        // TODO Auto-generated method stub
        return null;
    }

    public Vector getComplexComplement(){
        // TODO Auto-generated method stub
        return null;
    }
    
    public Vector toReal(){
        // TODO Auto-generated method stub
        return null;
    }
    
}
