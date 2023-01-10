package highlevelmath.constructs.structures;

import highlevelmath.constructs.Complex;
import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

public class Vector extends RealVec<Double>{

    //Constructors

    /**
     * A constructor for Vector class
     * @param vector An array of doubles that represent the values of the vector
     */
    public Vector(Double[] vector){
        this.data = vector;
    }

    /**
     * A constructor for Vector class
     * @param vector An array of ints that represent the values of the vector
     */
    public Vector(int[] vector) {
        Double[] array = new Double[vector.length];
        for(int i = 0; i < vector.length; i++){
            array[i] = (double)vector[i];
        }
        this.data = array;
    }

    //Operations
    public void add(Vec<Double, Double, RealField> vector) throws OperationUndefinedException {
        MatrixOperation<Double> function = (d1, d2) -> d1 + d2;
        applyOperation((RealVec<Double>)vector, function);
    }

    public void subtract(Vec<Double, Double, RealField> vector) throws OperationUndefinedException {
        MatrixOperation<Double> function = (d1, d2) -> d1 - d2;
        applyOperation((RealVec<Double>)vector, function);
    }

    public void modulus(RealVec<Double> vector) throws OperationUndefinedException{
        if(vector.contains(0.0))
            throw new OperationUndefinedException("This operation cannot be applied to input vectors with value 0.");
        MatrixOperation<Double> function = (d1, d2) -> d1 % d2;
        applyOperation(vector, function);
    }

    public void scale(Double factor) throws OperationUndefinedException {
        for(int i = 0; i < data.length; i++){
            data[i] *= factor;
        }
        correctRounding();
    }

    public Double dot(Vec<Double, Double, RealField> vector) throws OperationUndefinedException{
        if(data.length != vector.length())
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        double sum = 0;
        for(int i = 0; i < data.length; i++){
            sum += data[i] * vector.get(i);
        }
        return sum;
    }

    @Override
    public Double inner(Vec<Double, Double, RealField> vector) throws OperationUndefinedException {
        return this.dot(vector);
    }

    //General Methods

    @Override
    public Double sumVals() {
        double sum = 0;
        for(double d : data){
            sum += d;
        }
        return sum;
    }

    @Override
    public Vec<Double, Double, RealField> copy() {
        return new Vector(data);
    }

    //Other Methods

    public CVector toComplex(){
        Complex[] cv = new Complex[data.length];
        for(int i = 0; i < data.length; i++){
            cv[i] = new Complex(data[i], 0);
        }
        return new CVector(cv);
    }

    /**
     * Will correct any roundings issues (too much precision) with values in the matrix
     */
    public void correctRounding(){
        double threshold = 1E-2;
        for(int i = 0; i < data.length; i++){
            if(Math.abs(Math.round(data[i]) - data[i]) < threshold){
                data[i] = (double) Math.round(data[i]);
            }
        }
    }

    /**
     * WARNING - COULD LEAD TO UNINDENTED CONSQUENCES, ESPECIALLY IN MATRICES
     * Add a certain amount of 0s at the end of a vector
     * @param num The number of 0s to add to the end or the vector
     */
    public void pad(int num){
        Double[] newArray = new Double[data.length + num];
        for(int i = 0; i < data.length + num; i++){
            if(i < data.length){
                newArray[i] = data[i];
            } else {
                newArray[i] = 0.0;
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

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }
    
}
