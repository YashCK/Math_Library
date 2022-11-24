package highlevelmath.constructs;

public class Vector {

    private double[] data;

    //Constructors
    public Vector(double[] vector) {
        this.data = vector;
    }

    public Vector(int[] vector) {
        double[] array = new double[vector.length];
        for(int i = 0; i < vector.length; i++){
            array[i] = vector[i];
        }
        this.data = array;
    }

    //Operations between Vectors
    public void add(Vector vector) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 + d2;};
        applyOperation(vector, function);
    }

    public void subtract(Vector vector) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 - d2;};
        applyOperation(vector, function);
    }

    public void multiply(Vector vector) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> {return d1 * d2;};
        applyOperation(vector, function);
    }

    public void modulus(Vector vector) throws OperationUndefinedException{
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
            if(data.length != ((Vector)o).getLength()){
                return false;
            }
            for(int i = 0; i < data.length; i++){
                if(data[i] != ((Vector)o).get(i)){
                    return false;
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

    //Methods to Manipulate Vector
    public void scale(double factor){
        for(int i = 0; i < data.length; i++){
            data[i] *= factor;
        }
    }

    public void interchangeCols(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data.length || col2 >= data.length){
            throw new OperationUndefinedException("A column is out of the vector's range");
        }
        double first = data[col1];
        data[col1] = data[col2];
        data[col2] = first;
    }

    //Setters
    public void set(int index, double value) throws OperationUndefinedException{
        if(index >= data.length){
            throw new OperationUndefinedException("The index is out of the vector's range");
        }
        data[index] = value;
    }

    public void set(int index, int value) throws OperationUndefinedException{
        if(index >= data.length){
            throw new OperationUndefinedException("The index is out of the vector's range");
        }
        data[index] = value;
    }

    //Getters
    public int getLength(){
        return data.length;
    }

    public double get(int index) throws OperationUndefinedException{
        if(index >= data.length){
            throw new OperationUndefinedException("The index is out of the vector's range");
        }
        return data[index];
    }

    public boolean contains(double value){
        for(double val : data){
            if(val == value){
                return true;
            }
        }
        return false;
    }

    public boolean contains(int value){
        for(double val : data){
            if(val == value){
                return true;
            }
        }
        return false;
    }

    //Other Methods
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

    public String toString() {
        String str = "[";
        int index = 0;
        for(double element : data){
            str += (index == 0) ? "" : ", ";
            // str += truncateDecimal(element, 2);
            str += element;
            index++;
        }
        str += "]";
        return str;
    }

    protected void applyOperation(Vector vector, MatrixOperation Operation) throws OperationUndefinedException{
        if(data.length != vector.getLength()){
            throw new OperationUndefinedException("This operation cannot be applied to vectors of different lengths.");
        }
        for(int i = 0; i < data.length; i++){
            data[i] = Operation.operation(data[i], vector.get(i));
        }
    }

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }

}
    
