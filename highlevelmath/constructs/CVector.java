package highlevelmath.constructs;

import highlevelmath.constructs.util.*;

public class CVector implements Vec<Complex>{

    private Complex[] data;
    public final int length;

    /**
     * A constructor for CVector class
     * @param vector An array of Complex numbers that represent the values of the vector
     */
    public CVector(Complex[] vector){
        this.data = vector;
        this.length = vector.length;
    }

    /**
     * A constructor for CVector class
     * @param vector An array of Strings that represent the values of the vector
     *  - Each string must take the format of either a + bi, a - bi, a, or bi
     */
    public CVector(String[] vector) throws ConstructFormatException{
        Complex[] array = new Complex[vector.length];
        for(int i = 0; i < vector.length; i++){
            array[i] = new Complex(vector[i]);
        }
        this.data = array;
        this.length = vector.length;
    }

    //Operations
    @Override
    public void add(Vec<Complex> vector) throws OperationUndefinedException {
        
    }

    @Override
    public void subtract(Vec<Complex> vector) throws OperationUndefinedException {

    }

    @Override
    public void scale(Field<?> vector) {

    }

    @Override
    public Field<?> inner(Vec<Complex> vector) {

    }

    //Getters
    @Override
    public Complex get(int index) throws OperationUndefinedException {
        if(index >= data.length){
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        return data[index];
    }

    //Setters
    @Override
    public void set(int index, Complex value) throws OperationUndefinedException {
        if(index >= data.length){
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        data[index] = value;
    }

    public void set(int index, double value) throws OperationUndefinedException {
        if(index >= data.length){
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        data[index] = new Complex(value, 0);
    }

    public void set(int index, int value) throws OperationUndefinedException {
        if(index >= data.length){
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        data[index] = new Complex(value, 0);
    }
    
    //General Methods
    @Override
    public void interchangePos(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data.length || col2 >= data.length){
            throw new OperationUndefinedException("A column is out of the vector's range");
        }
        Complex first = data[col1];
        data[col1] = data[col2];
        data[col2] = first;
    }

    //Define Inner Product Function

    @Override
    public int length() {
        return data.length;
    }

    @Override
    public boolean contains(Complex value) {
        for(Complex val : data){
            if(val.equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Complex sumVals() {
        Complex sum = new Complex(0, 0);
        for(Complex d : data){
            sum = Complex.add(sum,  d);
        }
        return sum;
    }

    @Override
    public Vec<Complex> copy() {
        return new CVector(data);
    }

    Vec<Double> getRealComplement(){
        double[] vals = new double[data.length];
        int i = 0;
        for(Complex c : data){
            vals[i] = c.getReal();
            i++;
        }
        return new Vector(vals);
    }

    Vec<Complex> getComplexComplement(){
        Complex[] vals = new Complex[data.length];
        int i = 0;
        for(Complex c : data){
            vals[i] = new Complex(0, c.getImag());
            i++;
        }
        return new CVector(vals);
    }

    Vec<Double> toRealVector() throws OperationUndefinedException{
        if(isComplex()){
            throw new OperationUndefinedException("The vector is Complex. It cannot be converted to a Real vector.");
        }
        return getRealComplement();
    }

    //Other Methods
    boolean isComplex(){
        for(int i = 0; i < data.length; i++){
            if(data[i].getImag() != 0){
                return false;
            }
        }
        return true;
    }

    protected void applyOperation(Vec<Double> vector, MatrixOperation Operation) throws OperationUndefinedException{
        if(data.length != vector.length()){
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        for(int i = 0; i < data.length; i++){
            data[i] = Operation.operation(data[i], vector.get(i));
        }
    }

    //Correct Rounding

     /**
     * WARNING - COULD LEAD TO UNINDENTED CONSQUENCES, ESPECIALLY IN MATRICES
     * Add a certain amount of 0s at the end of a vector
     * @param num The number of 0s to add to the end or the vector
     */
    public void pad(int num){
        Complex[] newArray = new Complex[data.length + num];
        for(int i = 0; i < data.length + num; i++){
            if(i < data.length){
                newArray[i] = data[i];
            } else {
                newArray[i] = new Complex(0, 0);
            }
        }
        this.data = newArray;
    }

    @Override
    public String toString() {
        String str = "[";
        int index = 0;
        for(Complex element : data){
            str += (index == 0) ? "" : ", ";
            str += element.toString();
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
            if(data.length != ((Vec<Complex>)o).length()){
                return false;
            }
            for(int i = 0; i < data.length; i++){
                if(data[i] != ((Vec<Complex>)o).get(i)){
                    return false;
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

}
