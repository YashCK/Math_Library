package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.OperationUndefinedException;

import java.util.Arrays;

public class Matrix extends Matx<Double, Double> {

    /**
     * A constructor for Matrix class
     *
     * @param matrix An array of Vector objects that represent the rows of the matrix
     */
    public Matrix(Vector... matrix) {
        rData = Arrays.copyOf(matrix, matrix.length);
        recorrectMatrix(rData);
        constructCData(new Vector[rData[0].length()]);
    }

    /**
     * A constructor for Matrix class
     *
     * @param matrix A 2D array of doubles that represent the structure of the matrix
     */
    public Matrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            rData[i] = new Vector(matrix[i]);
        }
        recorrectMatrix(rData);
        constructCData(new Vector[rData[0].length()]);
    }

    /**
     * A constructor for Matrix class
     *
     * @param matrix   An array of Vector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public Matrix(boolean asColumn, Vector... matrix) {
        if (asColumn) {
            cData = Arrays.copyOf(matrix, matrix.length);
            recorrectMatrix(cData);
            constructRData(new Vector[cData[0].length()]);
        } else {
            rData = Arrays.copyOf(matrix, matrix.length);
            recorrectMatrix(rData);
            constructCData(new Vector[rData[0].length()]);
        }
    }

    /**
     * A constructor for Matrix class
     *
     * @param matrix   A 2D array of doubles that represent the structure of the matrix
     * @param asColumn Whether to interpret each array as a column vector or not
     */
    public Matrix(boolean asColumn, double[][] matrix) {
        if (asColumn) {
            for (int i = 0; i < matrix.length; i++) {
                cData[i] = new Vector(matrix[i]);
            }
            recorrectMatrix(cData);
            constructRData(new Vector[cData[0].length()]);
        } else {
            for (int i = 0; i < matrix.length; i++) {
                rData[i] = new Vector(matrix[i]);
            }
            recorrectMatrix(rData);
            constructCData(new Vector[rData[0].length()]);
        }
    }

    //Operations

    @Override
    public Matx<Double, Double> multiply(Matx<Double, Double> matrix) throws OperationUndefinedException {
        if (this.ncols() != matrix.nrows()) {
            throw new OperationUndefinedException("The columns of matrix 1 must equal the number of rows of matrix 2.");
        }
        double[][] newM = new double[this.nrows()][matrix.ncols()];
        for (int row = 0; row < rData.length; row++) {
            Vec<Double, Double> v = this.getRow(row);
            for (int col = 0; col < matrix.ncols(); col++) {
                newM[row][col] = v.dot(matrix.getCol(col));
            }
        }
        return new Matrix(newM);
    }

    @Override
    public Vec<Double, Double> multiply(Vec<Double, Double> v) throws OperationUndefinedException {
        if (this.ncols() != v.length()) {
            throw new OperationUndefinedException("The columns of the matrix must equal the length of the vector.");
        }
        double[] newV = new double[rData.length];
        for (int row = 0; row < rData.length; row++) {
            newV[row] = rData[row].dot(v);
        }
        return new Vector(newV);
    }

    /**
     * Operation to take the modulus of another matrix
     *
     * @param matrix Matrix object whose entries will act as the modulus divisor
     * @throws OperationUndefinedException
     */
    public void modulus(Matx<Double, Double> matrix) throws OperationUndefinedException {
        if (matrix.contains(0.0)) {
            throw new OperationUndefinedException("This operation cannot be applied to input matrices with value 0.");
        }
        applyOperation(matrix, (d1, d2) -> d1 % d2);
    }

    //Methods to Manipulate Matrix

    @Override
    public Matx<Double, Double> subMatrix(int startRow, int endRow, int startCol, int endCol)
            throws OperationUndefinedException {
        checkBounds("rData", startRow, endRow);
        checkBounds("cData", startCol, endCol);
        double[][] sub = new double[endRow - startRow + 1][endCol - startCol + 1];
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                sub[i][j] = this.get(i, j);
            }
        }
        return new Matrix(sub);
    }

    @Override
    public Matx<Double, Double> copy() {
        Vector[] newMatrix = new Vector[rData.length];
        for(int i = 0; i < rData.length; i++){
            newMatrix[i] = (Vector) rData[i];
        }
        return new Matrix(newMatrix);
    }

    //Other Methods
    @Override
    protected Field<Double> setElementField() {
        return new RealField();
    }

    @Override
    protected Field<Double> setScalarField() {
        return new RealField();
    }

    /**
     * Will correct any roundings issues (too much precision) with values in the matrix
     */
    public void correctRounding() {
        for (Vec<Double, Double> v : rData) {
            ((Vector) v).correctRounding();
        }
    }

    protected double truncateDecimal(double value, int places) {
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen) / powerOfTen;
    }

    public CMatrix toComplex() {
        CVector[] cv = new CVector[rData.length];
        for (int i = 0; i < rData.length; i++) {
            cv[i] = ((Vector) rData[i]).toComplex();
        }
        return new CMatrix(cv);
    }

}
