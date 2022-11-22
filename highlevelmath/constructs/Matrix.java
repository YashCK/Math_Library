package highlevelmath.constructs;

public class Matrix {

    protected static double[][] matrix;

    public Matrix(double[][] matrix){
        this.matrix = recorrectMatrix(matrix);
    }

    //Methods to Manipulate Matrix
    public static double[] subtractRows(int row1, int row2){
        // double[] diffs = new double[matrix[row1].length];
        // for(int i = 0; i < matrix[row1].length; i++){
        //     diffs[i] = matrix[row1][i] - matrix[row2][i];
        // }
        // return diffs;
        ;
    }

    public static void interchangeRows(int row1, int row2){
        double[] firstRow = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = firstRow;
    }

    public void scaleRow(int rowNum, double factor){
        for(int i = 0; i < matrix[rowNum].length; i++){
            matrix[rowNum][i] *= factor;
        }
    }

    public void scaleColumn(int columnNum, double factor){
        for(int i = 0; i < matrix.length; i++){
            matrix[i][columnNum] *= factor;
        }
    }

    // Setter Methods
    public void setRow(int[] row){
        ;
    }

    public void setColumn(int[] column){
        ;
    }

    public void setIndex(int row, int column){
        ;
    }

    //Getter Methods
    public double[] getRow(int rowNum){
        double[] row = new double[matrix[0].length];
        for(int columnNum = 0; columnNum < matrix[0].length; columnNum++){
            row[columnNum] = matrix[rowNum][columnNum];
        }
        return row;
    }

    public double[] getColumn(int columnNum){
        double[] column = new double[matrix.length];
        for(int rowNum = 0; rowNum < matrix.length; rowNum++){
            column[rowNum] = matrix[rowNum][columnNum];
        }
        return column;
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
                if(column > matrix[row].length){
                    cMatrix[row][column] = matrix[row][column];
                } else {
                    cMatrix[row][column] = 0;
                }  
            }
        }
        return cMatrix;
    }

    @Override
    public String toString() {
        String str = "[";
        for(double[] row : matrix){
            str += "[";
            for(double element: row){
                str += element == row[0] ? "" : " ";
                str += truncateDecimal(element, 2);
            }
            str += "]";  
        }
        str += "]";
        return str;
    }

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }
    
    
}
