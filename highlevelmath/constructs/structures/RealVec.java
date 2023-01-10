package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

public abstract class RealVec<T> implements Vec<T, Double, RealField> {

    protected static final String INDEX_OUT_RANGE = "The index is out of the vector's range";
    protected static final String OPER_DIFFERING_LENGTHS = "This operation cannot be applied to vectors of different lengths.";

    protected T[] data;

    //General Methods

    @Override
    public void interchangePos(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data.length || col2 >= data.length)
            throw new OperationUndefinedException("A column is out of the vector's range");
        T first = data[col1];
        data[col1] = data[col2];
        data[col2] = first;
    }

    @Override
    public int length() {
        return data.length;
    }

    @Override
    /**
     * Returns whether a particular value is present within the vector or not
     * @param value The value to search for
     * @return True if value is in the vector, False otherwise
     */
    public boolean contains(T value){
        for(T val : data){
            if(val.equals(value)){
                return true;
            }
        }
        return false;
    }

    //Getter Methods
    @Override
    public T get(int index) throws OperationUndefinedException{
        if(index >= data.length)
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        return data[index];
    }

    //Setters
    @Override
    public void set(int index, T value) throws OperationUndefinedException{
        if(index >= data.length)
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        data[index] = value;
    }

    //Other Methods
    protected void applyOperation(RealVec<T> vector, MatrixOperation<T> op) throws OperationUndefinedException{
        if(data.length != vector.length()){
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        for(int i = 0; i < data.length; i++){
            data[i] = op.operation(data[i], vector.get(i));
        }
    }

    @Override
    public boolean equals(Object o){
        try {
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((RealVec<T>)o).length()){
                return false;
            }
            for(int i = 0; i < data.length; i++){
                if(data[i] != ((RealVec<T>)o).get(i)){
                    return false;
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }
    
}
