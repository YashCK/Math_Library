package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.UndefinedException;

import java.util.HashMap;
import java.util.Map;

public class MatrixBuilder<E extends Field<E>> {

    public enum Type {
        Integer, Double, String;

        public <T> Matrix<?> create(Class<?> className, boolean asColumn, T[][] values) {
            try {
                switch (this) {
                    case Integer -> {
                        return mapFactories.get(className)[0].create(values, asColumn);
                    }
                    case Double -> {
                        return mapFactories.get(className)[1].create(values, asColumn);
                    }
                    case String -> {
                        return mapFactories.get(className)[2].create(values, asColumn);
                    }
                }
                return null;
            } catch (UndefinedException e){
                throw new RuntimeException(e);
            }
        }

    }

    private static final Map<Class, MatrixFactory[]> mapFactories = new HashMap<>();

    private Matrix<E> m;
    private Type s;
    private Class<E> className;

    public MatrixBuilder(){
        initializeFactories();
        s = null;
        className = null;
    }

    public <T> MatrixBuilder(Type s, Class<E> className, boolean asColumn, T[][] values) {
        initializeFactories();
        if (values.length >= 1) {
            createMatrix(s, className, asColumn, values);
        }
    }

    public <T> Matrix<E> construct(T[][] values, boolean asColumn) {
        if(s == null || className == null){
            throw new RuntimeException("The Type of Input and/or the className must be initialized to use this method.");
        }
        if (values.length >= 1) {
            createMatrix(s, className, asColumn, values);
            return m;
        }
        throw new RuntimeException("The vector must contain at least one value.");
    }

    private <T> void createMatrix(Type s, Class<E> className, boolean asColumn, T[][] values) {
        switch (s){
            case Integer -> {
                Integer[][] data = new Integer[values.length][values[0].length];
                for (int i = 0; i < values.length; i++) {
                    for(int j = 0; j < values[0].length; j++){
                        data[i][j] = (Integer) values[i][j];
                    }
                }
                m = (Matrix<E>) s.create(className, asColumn, data);
            }
            case Double -> {
                Double[][] data = new Double[values.length][values[0].length];
                for (int i = 0; i < values.length; i++) {
                    for(int j = 0; j < values[0].length; j++){
                        data[i][j] = (Double) values[i][j];
                    }
                }
                m = (Matrix<E>) s.create(className, asColumn, data);
            }
            case String -> {
                String[][] data = new String[values.length][values[0].length];
                for (int i = 0; i < values.length; i++) {
                    for(int j = 0; j < values[0].length; j++){
                        data[i][j] = (String) values[i][j];
                    }
                }
                m = (Matrix<E>) s.create(className, asColumn, data);
            }
        }
    }

    public Matrix<E> create() {
        return m;
    }

    public void set(Type s){
        this.s = s;
    }

    public void set(Class<E> className){
        this.className = className;
    }

    public void set(Type s, Class<E> className){
        this.s = s;
        this.className = className;
    }

    public void register(Class<?> className, VectorBuilder.Type t, MatrixFactory factory) {
        if (!mapFactories.containsKey(className)) {
            MatrixFactory[] factories = new MatrixFactory[3];
            mapFactories.put(className, factories);
        }
        switch (t) {
            case Integer -> mapFactories.get(className)[1] = factory;
            case Double -> mapFactories.get(className)[2] = factory;
            case String -> mapFactories.get(className)[3] = factory;
        }
    }

    private void initializeFactories() {
        MatrixFactory[] cFactories = new MatrixFactory[3];
        MatrixFactory<Integer> intMethod = this::complexFromInts;
        cFactories[0] = intMethod;
        MatrixFactory<Double> doubleMethod = this::complexFromDoubles;
        cFactories[1] = doubleMethod;
        MatrixFactory<String> stringMethod = this::complexFromStrings;
        cFactories[2] = stringMethod;
        mapFactories.put(Complex.class, cFactories);
        MatrixFactory[] rFactories = new MatrixFactory[3];
        MatrixFactory<Integer> intMethod2 = this::realFromInts;
        rFactories[0] = intMethod2;
        MatrixFactory<Double> doubleMethod2 = this::realFromDoubles;
        rFactories[1] = doubleMethod2;
        MatrixFactory<String> stringMethod2 = this::realFromStrings;
        rFactories[2] = stringMethod2;
        mapFactories.put(Real.class, rFactories);
    }

    private Matrix<Real> realFromDoubles(Double[][] values, boolean asColumn) {
        Real[][] reals = new Real[values.length][values[0].length];
        for (int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[0].length; j++){
                reals[i][j] = new Real(values[i][j]);
            }
        }
        return new Matrix<>(asColumn, reals);
    }

    private Matrix<Real> realFromInts(Integer[][] values, boolean asColumn){
        Real[][] reals = new Real[values.length][values[0].length];
        for (int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[0].length; j++){
                reals[i][j] = new Real(values[i][j]);
            }
        }
        return new Matrix<>(asColumn, reals);
    }

    private Matrix<Real> realFromStrings(String[][] values, boolean asColumn) throws UndefinedException {
        throw new UndefinedException("VectorBuilder is not defined to create Vector<Real> from strings.");
    }

    private Matrix<Complex> complexFromInts(Integer[][] values, boolean asColumn) {
        Complex[][] data = new Complex[values.length][values[0].length];
        for (int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[0].length; j++){
                data[i][j] = new Complex(values[i][j], 0);
            }
        }
        return new Matrix<>(asColumn, data);
    }

    private Matrix<Complex> complexFromDoubles(Double[][] values, boolean asColumn) {
        Complex[][] data = new Complex[values.length][values[0].length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                data[i][j] = new Complex(values[i][j], 0);
            }
        }
        return new Matrix<>(asColumn, data);
    }

    private Matrix<Complex> complexFromStrings(String[][] values, boolean asColumn) {
        try {
            Complex[][] data = new Complex[values.length][values[0].length];
            for (int i = 0; i < values.length; i++) {
                for(int j = 0; j < values[0].length; j++){
                    data[i][j] = new Complex(values[i][j]);
                }
            }
            return new Matrix<>(asColumn, data);
        } catch (ConstructFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    private interface MatrixFactory<T> {
        Matrix<?> create(T[][] data, boolean asColumn) throws UndefinedException;
    }

}
