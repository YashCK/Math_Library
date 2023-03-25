package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.UndefinedException;

import java.util.HashMap;
import java.util.Map;

public class VectorBuilder<E extends Field<E>> {

    public enum Type {
        Integer, Double, String;

        public <T> Vector<?> create(Class<?> className, T[] values) {
            try {
                switch (this) {
                    case Integer -> {
                        return mapFactories.get(className)[0].create(values);
                    }
                    case Double -> {
                        return mapFactories.get(className)[1].create(values);
                    }
                    case String -> {
                        return mapFactories.get(className)[2].create(values);
                    }
                }
                return null;
            } catch (UndefinedException e){
                throw new RuntimeException(e);
            }
        }

    }

    private static final Map<Class, VectorFactory[]> mapFactories = new HashMap<>();

    private Vector<E> v;

    public <T> VectorBuilder(Type s, Class<E> className, T... values) throws ConstructFormatException {
        initializeFactories();
        if (values.length >= 1) {
            switch (s){
                case Integer -> {
                    Integer[] data;
                    if(values.getClass().equals(int[][].class)){
                        int[] numbers = (int[]) values[0];
                        data = new Integer[numbers.length];
                        for (int i = 0; i < numbers.length; i++) {
                            data[i] = numbers[i];
                        }
                    } else if(values.getClass().equals(int[].class) || values.getClass().equals(Integer[].class)) {
                        data = new Integer[values.length];
                        for (int i = 0; i < values.length; i++) {
                            data[i] = (Integer) values[i];
                        }
                    } else {
                        throw new ConstructFormatException("Either an int[], Integer[], or ints in a var args format must be supplied.");
                    }
                    v = (Vector<E>) s.create(className, data);
                }
                case Double -> {
                    Double[] data;
                    if(values.getClass().equals(double[][].class)){
                        double[] numbers = (double[]) values[0];
                        data = new Double[numbers.length];
                        for (int i = 0; i < numbers.length; i++) {
                            data[i] = numbers[i];
                        }
                    } else if(values.getClass().equals(double[].class) || values.getClass().equals(Double[].class)) {
                        data = new Double[values.length];
                        for (int i = 0; i < values.length; i++) {
                            data[i] = (Double) values[i];
                        }
                    } else {
                        throw new ConstructFormatException("Either an double[], Double[], or doubles in a var args format must be supplied.");
                    }
                    v = (Vector<E>) s.create(className, data);
                }
                case String -> {
                    String[] data;
                    if(values.getClass().equals(String[][].class)){
                        String[] numbers = (String[]) values[0];
                        data = new String[numbers.length];
                        for (int i = 0; i < numbers.length; i++) {
                            data[i] = numbers[i];
                        }
                    } else if(values.getClass().equals(String[].class)) {
                        data = new String[values.length];
                        for (int i = 0; i < values.length; i++) {
                            data[i] = (String) values[i];
                        }
                    } else {
                        throw new ConstructFormatException("Either an String[] or strings in a var args format must be supplied.");
                    }
                    v = (Vector<E>) s.create(className, data);
                }
            }
        }
    }

    public Vector<E> create() {
        return v;
    }

    public void register(Class<?> className, Type t, VectorFactory factory) {
        if (!mapFactories.containsKey(className)) {
            VectorFactory[] factories = new VectorFactory[3];
            mapFactories.put(className, factories);
        }
        switch (t) {
            case Integer -> mapFactories.get(className)[1] = factory;
            case Double -> mapFactories.get(className)[2] = factory;
            case String -> mapFactories.get(className)[3] = factory;
        }
    }

    private void initializeFactories() {
        VectorFactory[] cFactories = new VectorFactory[3];
        VectorFactory<Integer> intMethod = this::complexFromInts;
        cFactories[0] = intMethod;
        VectorFactory<Double> doubleMethod = this::complexFromDoubles;
        cFactories[1] = doubleMethod;
        VectorFactory<String> stringMethod = this::complexFromStrings;
        cFactories[2] = stringMethod;
        mapFactories.put(Complex.class, cFactories);
        VectorFactory[] rFactories = new VectorFactory[3];
        VectorFactory<Integer> intMethod2 = this::realFromInts;
        rFactories[0] = intMethod2;
        VectorFactory<Double> doubleMethod2 = this::realFromDoubles;
        rFactories[1] = doubleMethod2;
        VectorFactory<String> stringMethod2 = this::realFromStrings;
        rFactories[2] = stringMethod2;
        mapFactories.put(Real.class, rFactories);
    }

    private Vector<Real> realFromDoubles(Double[] values) {
        Real[] reals = new Real[values.length];
        for (int i = 0; i < values.length; i++) {
            reals[i] = new Real(values[i]);
        }
        return new Vector<>(reals);
    }

    private Vector<Real> realFromInts(Integer[] values){
        Real[] reals = new Real[values.length];
        for (int i = 0; i < values.length; i++) {
            reals[i] = new Real(values[i]);
        }
        return new Vector<Real>(reals);
    }

    private Vector<Real> realFromStrings(String[] values) throws UndefinedException {
        throw new UndefinedException("VectorBuilder is not defined to create Vector<Real> from strings.");
    }

    private Vector<Complex> complexFromInts(Integer[] values) {
        Complex[] data = new Complex[values.length];
        for (int i = 0; i < values.length; i++) {
            data[i] = new Complex(values[i], 0);
        }
        return new Vector<>(data);
    }

    private Vector<Complex> complexFromDoubles(Double[] values) {
        Complex[] data = new Complex[values.length];
        for (int i = 0; i < values.length; i++) {
            data[i] = new Complex(values[i], 0);
        }
        return new Vector<>(data);
    }

    private Vector<Complex> complexFromStrings(String[] values) {
        try {
            Complex[] data = new Complex[values.length];
            for (int i = 0; i < values.length; i++) {
                data[i] = new Complex(values[i]);
            }
            return new Vector<>(data);
        } catch (ConstructFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    private interface VectorFactory<T> {
        Vector<?> create(T[] data) throws UndefinedException;
    }

}
