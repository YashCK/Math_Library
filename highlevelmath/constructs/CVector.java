package highlevelmath.constructs;

import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

public class CVector implements Vec<Complex, Double, RealField>{

    private static final String INDEX_OUT_RANGE = "The index is out of the vector's range";
    private static final String OPER_DIFFERING_LENGTHS = "This operation cannot be applied to vectors of different lengths.";

    private Complex[] data;

    /**
     * A constructor for CVector class
     * @param vector An array of Complex numbers that represent the values of the vector
     */
    public CVector(Complex[] vector){
        this.data = vector;
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
    }

    //Operations
    @Override
    public void add(Vec<Complex, Double, RealField> vector) throws OperationUndefinedException {
        MatrixOperation<Complex> function = (c1, c2) -> {return Complex.add(c1, c2);};
        applyOperation(vector, function);
    }

    @Override
    public void subtract(Vec<Complex, Double, RealField> vector) throws OperationUndefinedException {
        MatrixOperation<Complex> function = (c1, c2) -> {return Complex.subtract(c1, c2);};
        applyOperation(vector, function);
    }

    @Override
    public void scale(Double factor) throws OperationUndefinedException {
        for(int i = 0; i < data.length; i++){
            data[i].scale(factor);
        }
    }

    @Override
    public Double inner(Vec<Complex, Double, RealField> vector) {
        //TO BE IMPLEMENTED
        return 0.0;
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

    public void defineInnerProduct(){
        //
    }

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
    public Vec<Complex, Double, RealField> copy() {
        return new CVector(data);
    }

    Vec<Double, Double, RealField> getRealComplement(){
        double[] vals = new double[data.length];
        int i = 0;
        for(Complex c : data){
            vals[i] = c.getReal();
            i++;
        }
        return new Vector(vals);
    }

    Vec<Complex, Double, RealField> getComplexComplement(){
        Complex[] vals = new Complex[data.length];
        int i = 0;
        for(Complex c : data){
            vals[i] = new Complex(0, c.getImag());
            i++;
        }
        return new CVector(vals);
    }

    Vec<Double, Double, RealField> toRealVector() throws OperationUndefinedException{
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

    protected void applyOperation(Vec<Complex, Double, RealField> vector, MatrixOperation<Complex> function) throws OperationUndefinedException{
        if(data.length != vector.length()){
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        for(int i = 0; i < data.length; i++){
            data[i] = function.operation(data[i], vector.get(i));
        }
    }

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
            if(data.length != ((Vec<Complex, Double, RealField>)o).length()){
                return false;
            }
            for(int i = 0; i < data.length; i++){
                if(data[i] != ((Vec<Complex, Double, RealField>)o).get(i)){
                    return false;
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

}
