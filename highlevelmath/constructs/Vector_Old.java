package highlevelmath.constructs;

/**
 * This class creates a represntation of a vector which can be used to store data,
 * represent coordinates, or any other quantities. These are especially useful in
 * Linear Algebra, Physics, and other similar areas.
 */
public class Vector_Old {

    private static final String INDEX_OUT_RANGE = "The index is out of the vector's range";
    private static final String OPER_DIFFERING_LENGTHS = "This operation cannot be applied to vectors of different lengths.";

    private double[] data;
    private Complex[] cData;
    private boolean isComplex = false;

    public final int length;

    //Constructors
    /**
     * A constructor for Vector class
     * @param vector An array of doubles that represent the values of the vector
     */
    public Vector_Old(double[] vector) {
        this.data = vector;
        this.length = vector.length;
    }

    /**
     * A constructor for Vector class
     * @param vector An array of ints that represent the values of the vector
     */
    public Vector_Old(int[] vector) {
        double[] array = new double[vector.length];
        for(int i = 0; i < vector.length; i++){
            array[i] = vector[i];
        }
        this.data = array;
        this.length = vector.length;
    }

    public Vector_Old(String[] vector) throws ConstructFormatException {
        Complex[] array = new Complex[vector.length];
        for(int i = 0; i < vector.length; i++){
            array[i] = new Complex(vector[i]);
        }
        this.cData = array;
        this.isComplex = true;
        this.length = vector.length;
    }

    //Operations between Vectors
    /**
     * Operation to add two vectors to one another
     * @param vector Vector object that should be added
     * @throws OperationUndefinedException
     */
    public void add(Vector_Old vector) throws OperationUndefinedException{
        if(this.isComplex){
            MatrixOperation function = (d1, d2) -> {return d1 + d2;};
            applyComplexOperation(vector, function);
        } else {
            MatrixOperation function = (d1, d2) -> {return d1 + d2;};
            applyOperation(vector, function);
        }
    }

    /**
     * Operation to subtract two vectors from one another
     * @param vector Vector object that should be subtracted
     * @throws OperationUndefinedException
     */
    public void subtract(Vector_Old vector) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 - d2;};
        applyOperation(vector, function);
    }

    /**
     * Operation to dot two vectors (for vectors in Rn)
     * @param vector Vector object to dot
     * @throws OperationUndefinedException
     */
    public double dot(Vector_Old vector) throws OperationUndefinedException{
        if(data.length != vector.length){
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        double sum = 0;
        for(int i = 0; i < data.length; i++){
            sum += data[i] * vector.get(i);
        }
        return sum;
    }

    /**
     * Operation to take the modulus of another matrix
     * @param vector Vector object whose entires will act as the modulus divisor
     * @throws OperationUndefinedException
     */
    public void modulus(Vector_Old vector) throws OperationUndefinedException{
        if(vector.contains(0)){
            throw new OperationUndefinedException("This operation cannot be applied to input vectors with value 0.");
        }
        MatrixOperation function = (d1, d2) -> {return d1 % d2;};
        applyOperation(vector, function);
    }

    @Override
    public boolean equals(Object o){
        try {
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((Vector_Old)o).length){
                return false;
            }
            for(int i = 0; i < data.length; i++){
                if(data[i] != ((Vector_Old)o).get(i)){
                    return false;
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

    //Methods to Manipulate Vector
    /**
     * Scale the vector by some factor
     * @param factor The factor by which the vector should be scaled by
     */
    public void scale(double factor){
        for(int i = 0; i < data.length; i++){
            data[i] *= factor;
        }
        correctRounding();
    }

    /**
     * Exchange positions of two values of the vector
     * @param col1 The first index or column number
     * @param col2 The other index or column number
     * @throws OperationUndefinedException
     */
    public void interchangeCols(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data.length || col2 >= data.length){
            throw new OperationUndefinedException("A column is out of the vector's range");
        }
        double first = data[col1];
        data[col1] = data[col2];
        data[col2] = first;
    }

    public Vector_Old copy(){
        return new Vector_Old(data);
    }

    //Setters
    /**
     * Sets an index of the vector to particular value
     * @param index The index that should be set
     * @param value The value to set to
     * @throws OperationUndefinedException
     */
    public void set(int index, double value) throws OperationUndefinedException{
        if(index >= data.length){
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        data[index] = value;
    }

    /**
     * Sets an index of the vector to particular value
     * @param index The index that should be set
     * @param value The value to set to
     * @throws OperationUndefinedException
     */
    public void set(int index, int value) throws OperationUndefinedException{
        if(index >= data.length){
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        data[index] = value;
    }

    //Getters

    /**
     * Gets the value at a certain index
     * @param index The index that should be gotten
     * @return The value at the index passed in
     * @throws OperationUndefinedException
     */
    public double get(int index) throws OperationUndefinedException{
        if(index >= data.length){
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        return data[index];
    }

    /**
     * Returns whether a particular value is present within the vector or not
     * @param value The value to search for
     * @return True if value is in the vector, False otherwise
     */
    public boolean contains(double value){
        for(double val : data){
            if(val == value){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether a particular value is present within the vector or not
     * @param value The value to search for
     * @return True if value is in the vector, False otherwise
     */
    public boolean contains(int value){
        for(double val : data){
            if(val == value){
                return true;
            }
        }
        return false;
    }

    public double sumVals(){
        double sum = 0;
        for(double d : data){
            sum += d;
        }
        return sum;
    }

    //Other Methods
    /**
     * WARNING - COULD LEAD TO UNINDENTED CONSQUENCES, ESPECIALLY IN MATRICES
     * Add a certain amount of 0s at the end of a vector
     * @param num The number of 0s to add to the end or the vector
     */
    public void recorrect(int num){
        double[] newArray = new double[data.length + num];
        for(int i = 0; i < data.length + num; i++){
            if(i < data.length){
                newArray[i] = data[i];
            } else {
                newArray[i] = 0;
            }
        }
        this.data = newArray;
    }

    /**
     * Will correct any roundings issues (too much precision) with values in the matrix
     */
    public void correctRounding(){
        double threshold = 1E-2;
        for(int i = 0; i < data.length; i++){
            if(Math.abs(Math.round(data[i]) - data[i]) < threshold){
                data[i] = Math.round(data[i]);
            }
        }
    }

    public String toString() {
        String str = "[";
        int index = 0;
        for(double element : data){
            str += (index == 0) ? "" : ", ";
            str += truncateDecimal(element, 2);
            index++;
        }
        str += "]";
        return str;
    }

    protected void applyOperation(Vector_Old vector, MatrixOperation Operation) throws OperationUndefinedException{
        if(data.length != vector.length){
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        if(isComplex){
            for(int i = 0; i < data.length; i++){
                cData[i] = Operation.operation(cData[i], vector.get(i));
            }
        } else{
            for(int i = 0; i < data.length; i++){
                data[i] = Operation.operation(data[i], vector.get(i));
            }
        }
    }

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }

}
    
