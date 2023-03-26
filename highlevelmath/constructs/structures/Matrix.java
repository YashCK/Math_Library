package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;

import java.lang.reflect.Array;

public class Matrix<E extends Field<E>> extends Matx<E, E> {

    public Matrix(Vector<E>... vectors) {
        super(vectors);
    }

    public Matrix(boolean asColumn, Vector<E>... vectors) {
        super(asColumn, vectors);
    }

    public Matrix(E[][] matrix) {
        super(matrix);
    }

    public Matrix(boolean asColumn, E[][] matrix) {
        super(asColumn, matrix);
    }

    @Override
    public Matx<E, E> multiply(Matx<E, E> matrix) {
        if (this.ncols() != matrix.nrows()) {
            throw new RuntimeException("The columns of matrix 1 must equal the number of rows of matrix 2.");
        }
        E[][] newM = (E[][]) Array.newInstance(rData[0].get(0).getClass(), this.nrows(), matrix.ncols());
        for (int row = 0; row < rData.length; row++) {
            Vec<E, E> v = this.getRow(row);
            for (int col = 0; col < matrix.ncols(); col++) {
                newM[row][col] = v.dot(matrix.getCol(col));
            }
        }
        return new Matrix(newM);
    }

    @Override
    public Vec<E, E> multiply(Vec<E, E> v) {
        if (this.ncols() != v.length()) {
            throw new RuntimeException("The columns of the matrix must equal the length of the vector.");
        }
        E[] newV = (E[]) Array.newInstance(rData[0].get(0).getClass(), rData.length);
        for (int row = 0; row < rData.length; row++) {
            newV[row] = rData[row].dot(v);
        }
        return new Vector(newV);
    }

    @Override
    public Matx<E, E> subMatrix(int startRow, int endRow, int startCol, int endCol) {
        checkBounds("rData", startRow, endRow);
        checkBounds("cData", startCol, endCol);
        E[][] sub = (E[][]) Array.newInstance(rData[0].get(0).getClass(), endRow - startRow + 1, endCol - startCol + 1);
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                sub[i][j] = this.get(i, j);
            }
        }
        return new Matrix<>(sub);
    }

    @Override
    public Matx<E, E> copy() {
        Vector<E>[] copy = new Vector[rData.length];
        for (int i = 0; i < rData.length; i++) {
            copy[i] = (Vector<E>) rData[i].copy();
        }
        return new Matrix<>(copy);
    }

    @Override
    protected Vec<E, E> createVec(E[] values) {
        return new Vector<>(values);
    }

}
