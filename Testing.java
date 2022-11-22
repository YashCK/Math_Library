import highlevelmath.*;

public class Testing {
    public static void main(String[] args){
        double[][] matrix = {  {0, 3, -6, 6, 4, -5},
                            {3, -7, 8, -5, 8, 9}, 
                            {3, - 9, 12, -9, 6, 15}};
        LinAlg.rowReduction(matrix);
    }

}

