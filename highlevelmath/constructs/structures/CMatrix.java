package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.ComplexField;
import highlevelmath.constructs.util.OperationUndefinedException;

import java.util.Arrays;

public class CMatrix extends Matx<Complex, Complex> {

    //Constructors

    /**
     * A constructor for CMatrix class
     *
     * @param matrix An array of CVector objects that represent the rows of the matrix
     */
    public CMatrix(CVector... matrix) {
        rData = Arrays.copyOf(matrix, matrix.length);
        recorrectMatrix(rData);
        constructCData(new CVector[rData[0].length()]);
    }

    /**
     * A constructor for CMatrix class
     *
     * @param matrix An array of Vector objects that represent the rows of the matrix
     */
    public CMatrix(Vector... matrix) {
        for (int i = 0; i < matrix.length; i++) {
            rData[i] = matrix[i].toComplex();
        }
        recorrectMatrix(rData);
        constructCData(new CVector[rData[0].length()]);
    }

    /**
     * A constructor for CMatrix class
     *
     * @param matrix A 2d array of Complex objects that represent the rows of the matrix
     */
    public CMatrix(Complex[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            rData[i] = new CVector(matrix[i]);
        }
        recorrectMatrix(rData);
        constructCData(new CVector[rData[0].length()]);
    }

    /**
     * A constructor for CMatrix class
     *
     * @param matrix   An array of CVector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(boolean asColumn, CVector... matrix) {
        if (asColumn) {
            cData = Arrays.copyOf(matrix, matrix.length);
            recorrectMatrix(cData);
            constructRData(new CVector[cData[0].length()]);
        } else {
            rData = Arrays.copyOf(matrix, matrix.length);
            recorrectMatrix(rData);
            constructCData(new CVector[rData[0].length()]);
        }
    }

    /**
     * A constructor for CMatrix class
     *
     * @param matrix   An array of Vector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(Vector[] matrix, boolean asColumn) {
        if (asColumn) {
            for (int i = 0; i < matrix.length; i++) {
                cData[i] = matrix[i].toComplex();
            }
            recorrectMatrix(cData);
            constructRData(new CVector[cData[0].length()]);
        } else {
            for (int i = 0; i < matrix.length; i++) {
                rData[i] = matrix[i].toComplex();
            }
            recorrectMatrix(rData);
            constructCData(new CVector[rData[0].length()]);
        }
    }

    /**
     * A constructor for CMatrix class
     *
     * @param matrix   A 2d array of Complex objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public CMatrix(Complex[][] matrix, boolean asColumn) {
        if (asColumn) {
            for (int i = 0; i < matrix.length; i++) {
                cData[i] = new CVector(matrix[i]);
            }
            recorrectMatrix(cData);
            constructRData(new CVector[cData[0].length()]);
        } else {
            for (int i = 0; i < matrix.length; i++) {
                rData[i] = new CVector(matrix[i]);
            }
            recorrectMatrix(rData);
            constructCData(new CVector[rData[0].length()]);
        }
    }

    @Override
    public Matx<Complex, Complex> multiply(Matx<Complex, Complex> matrix) throws OperationUndefinedException {
        if (this.ncols() != matrix.nrows()) {
            throw new OperationUndefinedException("The columns of matrix 1 must equal the number of rows of matrix 2.");
        }
        Complex[][] newM = new Complex[this.nrows()][matrix.ncols()];
        for (int row = 0; row < rData.length; row++) {
            for (int col = 0; col < matrix.ncols(); col++) {
                newM[row][col] = getRow(row).dot(matrix.getCol(col));
            }
        }
        return new CMatrix(newM);
    }

    @Override
    public Vec<Complex, Complex> multiply(Vec<Complex, Complex> v) throws OperationUndefinedException {
        if (this.ncols() != v.length()) {
            throw new OperationUndefinedException("The columns of the matrix must equal the length of the vector.");
        }
        Complex[] newV = new Complex[rData.length];
        for (int row = 0; row < rData.length; row++) {
            newV[row] = rData[row].dot(v);
        }
        return new CVector(newV);
    }

    @Override
    public Matx<Complex, Complex> subMatrix(int startRow, int endRow, int startCol, int endCol)
            throws OperationUndefinedException {
        checkBounds("rData", startRow, endRow);
        checkBounds("cData", startCol, endCol);
        Complex[][] sub = new Complex[endRow - startRow + 1][endCol - startCol + 1];
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                sub[i][j] = this.get(i, j);
            }
        }
        return new CMatrix(sub);
    }

    @Override
    public Matx<Complex, Complex> copy() {
        CVector[] copy = new CVector[rData.length];
        for (int i = 0; i < rData.length; i++) {
            copy[i] = (CVector) rData[i];
        }
        return new CMatrix(copy);
    }

    @Override
    protected Field<Complex> setElementField() {
        return new ComplexField();
    }

    @Override
    protected Field<Complex> setScalarField() {
        return new ComplexField();
    }

    public Matrix getRealComplement() {
        Vector[] v = new Vector[rData.length];
        for (int i = 0; i < rData.length; i++) {
            v[i] = ((CVector) rData[i]).getRealComplement();
        }
        return new Matrix(v);
    }

    public CMatrix getComplexComplement() {
        CVector[] v = new CVector[rData.length];
        for (int i = 0; i < rData.length; i++) {
            v[i] = ((CVector) rData[i]).getComplexComplement();
        }
        return new CMatrix(v);
    }

    public Matrix toReal() throws OperationUndefinedException {
        if (isComplex()) {
            throw new OperationUndefinedException("The Matrix is Complex. It cannot be converted to a Real Matrix.");
        }
        return getRealComplement();
    }

    public boolean isComplex() {
        for (Vec<Complex, Complex> c : rData) {
            if (!((CVector) c).isComplex()) {
                return false;
            }
        }
        return true;
    }

}
