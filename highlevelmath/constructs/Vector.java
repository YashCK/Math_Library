package highlevelmath.constructs;
import highlevelmath.constructs.util.*;

public class Vector implements Vec<Double>{

    private static final String INDEX_OUT_RANGE = "The index is out of the vector's range";
    private static final String OPER_DIFFERING_LENGTHS = "This operation cannot be applied to vectors of different lengths.";

    private double[] data;

    //Constructors

    /**
     * A constructor for Vector class
     * @param vector An array of doubles that represent the values of the vector
     */
    public Vector(double[] vector){
        this.data = vector;
    }

    /**
     * A constructor for Vector class
     * @param vector An array of ints that represent the values of the vector
     */
    public Vector(int[] vector) {
        double[] array = new double[vector.length];
        for(int i = 0; i < vector.length; i++){
            array[i] = vector[i];
        }
        this.data = array;
    }

    //Operations

    @Override
    public void add(Vec<Double> vector) throws OperationUndefinedException {
        MatrixOperation function = (d1, d2) -> {return d1 + d2;};
        applyOperation(vector, function);
    }

    @Override
    public void subtract(Vec<Double> vector) throws OperationUndefinedException {
        MatrixOperation function = (d1, d2) -> {return d1 - d2;};
        applyOperation(vector, function);
    }

    public void modulus(Vec<Double> vector) throws OperationUndefinedException{
        if(vector.contains(0)){
            throw new OperationUndefinedException("This operation cannot be applied to input vectors with value 0.");
        }
        MatrixOperation function = (d1, d2) -> {return d1 % d2;};
        applyOperation(vector, function);
    }

    @Override
    public void scale(double factor) {
        for(int i = 0; i < data.length; i++){
            data[i] *= factor;
        }
        correctRounding();
    }

    public double dot(Vec<Double> vector) throws OperationUndefinedException{
       return this.inner(vector);
    }

    @Override
    public Field<Double> inner(Vec<Double> vector) {
        if(data.length != vector.length){
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        double sum = 0;
        for(int i = 0; i < data.length; i++){
            sum += data[i] * vector.get(i);
        }
        return sum;
    }

    //General Methods

    @Override
    public void interchangePos(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data.length || col2 >= data.length){
            throw new OperationUndefinedException("A column is out of the vector's range");
        }
        double first = data[col1];
        data[col1] = data[col2];
        data[col2] = first;
    }

    @Override
    public int length() {
        return data.length;
    }

    @Override
    public boolean contains(Double value) {
        for(double val : data){
            if(val == value){
                return true;
            }
        }
        return false;
    }

    @Override
    public Double sumVals() {
        double sum = 0;
        for(double d : data){
            sum += d;
        }
        return sum;
    }

    @Override
    public Vec<Double> copy() {
        return new Vector(data);
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

    //Getters
    public Double get(int index) throws OperationUndefinedException{
        if(index >= data.length){
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        return data[index];
    }

    //Setters
    public void set(int index, Double value) throws OperationUndefinedException{
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


    //Other Methods
    protected void applyOperation(Vec<Double> vector, MatrixOperation Operation) throws OperationUndefinedException{
        if(data.length != vector.length()){
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        for(int i = 0; i < data.length; i++){
            data[i] = Operation.operation(data[i], vector.get(i));
        }
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

    /**
     * WARNING - COULD LEAD TO UNINDENTED CONSQUENCES, ESPECIALLY IN MATRICES
     * Add a certain amount of 0s at the end of a vector
     * @param num The number of 0s to add to the end or the vector
     */
    public void pad(int num){
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

    @Override
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

    @Override
    public boolean equals(Object o){
        try {
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((Vec<Double>)o).length()){
                return false;
            }
            for(int i = 0; i < data.length; i++){
                if(data[i] != ((Vec<Double>)o).get(i)){
                    return false;
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }
    
}
