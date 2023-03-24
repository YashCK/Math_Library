package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.util.ConstructFormatException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class VectorBuilder {

    public enum Type {
        Integer, Double, String;

        public <T> Vector<?> create(String vectorName, T[] values) {
            switch (this) {
                case Integer -> {
                    return mapFactories.get(vectorName)[0].create(values);
                }
                case Double -> {
                    return mapFactories.get(vectorName)[1].create(values);
                }
                case String -> {
                    return mapFactories.get(vectorName)[2].create(values);
                }
            }
            return null;
        }

    }

    private static Map<String, VectorFactory[]> mapFactories = new HashMap<>();

    private Type s;
    private Vector<?> v;

    public VectorBuilder(double... values) {
        initializeFactories();
        Real[] reals = new Real[values.length];
        for (int i = 0; i < values.length; i++) {
            reals[i] = new Real(values[i]);
        }
        v = new Vector<>(reals);
    }

    public VectorBuilder(int... values) {
        initializeFactories();
        Real[] reals = new Real[values.length];
        for (int i = 0; i < values.length; i++) {
            reals[i] = new Real(values[i]);
        }
        v = new Vector<>(reals);
    }

    public <T> VectorBuilder(Type s, String vectorName, T... values) throws ConstructFormatException {
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
                    v = s.create(vectorName, data);
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
                    v = s.create(vectorName, data);
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
                    v = s.create(vectorName, data);
                }
            }
        }
    }

    public Vector<?> create() {
        return v;
    }

    public void register(String vectorName, VectorFactory factory) {
        if (!mapFactories.containsKey(vectorName)) {
            VectorFactory[] factories = new VectorFactory[3];
            mapFactories.put(vectorName, factories);
        }
        switch (s) {
            case Integer -> mapFactories.get(vectorName)[1] = factory;
            case Double -> mapFactories.get(vectorName)[2] = factory;
            case String -> mapFactories.get(vectorName)[3] = factory;
        }
    }

    private void initializeFactories() {
        VectorFactory[] factories = new VectorFactory[3];
        VectorFactory<Integer> intMethod = this::complexFromInts;
        factories[0] = intMethod;
        VectorFactory<Double> doubleMethod = this::complexFromDoubles;
        factories[1] = doubleMethod;
        VectorFactory<String> stringMethod = this::complexFromStrings;
        factories[2] = stringMethod;
        mapFactories.put("Complex Vector", factories);
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
        Vector<?> create(T[] data);
    }

}
