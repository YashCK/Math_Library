package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.ComplexField;
import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.OperationUndefinedException;

public class CVector extends Vec<Complex, Double>{

    /**
     * A constructor for CVector class
     * @param vector An array of Complex numbers that represent the values of the vector
     */
    public CVector(Complex[] vector){
        super();
        this.data = vector;
    }

    /**
     * A constructor for CVector class
     * @param vector An array of double numbers that represent the real values of the vector
     * NOTE: All the complex values will be set to 0
     */
    public CVector(double[] vector){
        super();
        Complex[] array = new Complex[vector.length];
        for(int i = 0; i < vector.length; i++){
            array[i] = new Complex(vector[i], 0);
        }
        this.data = array;
    }

    /**
     * A constructor for CVector class
     * @param vector An array of Strings that represent the values of the vector
     *  - Each string must take the format of either a + bi, a - bi, a, or bi
     */
    public CVector(String[] vector) throws ConstructFormatException{
        super();
        Complex[] array = new Complex[vector.length];
        for(int i = 0; i < vector.length; i++){
            array[i] = new Complex(vector[i]);
        }
        this.data = array;
    }

    //Operations
    @Override
    public void scale(Double factor) throws OperationUndefinedException {
        for(int i = 0; i < data.length; i++){
            data[i].scale(factor);
        }
    }

    @Override
    public Double dot(Vec<Complex, Double> vector) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double inner(Vec<Complex, Double> vector) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }
    
    //General Methods
    @Override
    public Vec<Complex, Double> copy() {
        return new CVector(data);
    }

    Vector getRealComplement(){
        Double[] vals = new Double[data.length];
        int i = 0;
        for(Complex c : data){
            vals[i] = c.getReal();
            i++;
        }
        return new Vector(vals);
    }

    CVector getComplexComplement(){
        Complex[] vals = new Complex[data.length];
        for(int i = 0; i < data.length; i++){
            vals[i] = new Complex(0, data[i].getImag());
        }
        return new CVector(vals);
    }

    Vector toRealVector() throws OperationUndefinedException{
        if(isComplex())
            throw new OperationUndefinedException("The vector is Complex. It cannot be converted to a Real vector.");
        return getRealComplement();
    }

    public void set(int index, double value) throws OperationUndefinedException {
        if(index >= data.length)
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        data[index] = new Complex(value, 0);
    }

    //Other Methods
    @Override
    protected Field<Complex> setElementField() {
        return new ComplexField();
    }

    @Override
    protected Field<Double> setScalarField() {
        return new RealField();
    }

    boolean isComplex(){
        for(int i = 0; i < data.length; i++){
            if(data[i].getImag() != 0)
                return false;
        }
        return true;
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

}
