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
                    Integer[] data = new Integer[values.length];
//                    for (int i = 0; i < values.length; i++) {
//                        data[i] = (Integer) values[i];
//                    }
                    return mapFactories.get(vectorName)[0].create(values);
                }
                case Double -> {
                    Double[] data = new Double[values.length];
                    for (int i = 0; i < values.length; i++) {
                        data[i] = (Double) values[i];
                    }
                    return mapFactories.get(vectorName)[1].create(data);
                }
                case String -> {
                    String[] data = new String[values.length];
                    for (int i = 0; i < values.length; i++) {
                        data[i] = (String) values[i];
                    }
                    return mapFactories.get(vectorName)[2].create(data);
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

    public <T> VectorBuilder(Type s, String vectorName, T... values) {
        if (values.length >= 1) {
            switch (s){
                case Integer -> {
                    if(values instanceof Integer[] iVals){
                        System.out.println("hey");
//                        Integer[] data = new Integer[values.length];
//                        for (int i = 0; i < values.length; i++) {
//                            data[i] = (Integer) values[i];
//                        }
                        v = s.create(vectorName, iVals);
                    } else {
//                        System.out.println("hi");
//
//                        Integer[] integerArray = new Integer[values.length];
//                        System.out.println("stuff: " + Arrays.toString(values));
//                        for (int i = 0; i < values.length; i++) {
//                            System.out.println("thing: " + values[i].toString());
//                            integerArray[i] = Integer.valueOf((int) values[i]);
//                        }
//

                        int[] data = new int[values.length];
                        for (int i = 0; i < values.length; i++) {
                            data[i] = (int) (Object) values[i];
                            System.out.println("data[i]" + i + " " + data[i]);
//                            data[i] = ((Number) values[i]).intValue();
                            //((Number) values[i]).intValue()
                        }
                        Integer[] newData = Arrays.stream( data ).boxed().toArray(Integer[]::new);
                        v = s.create(vectorName, newData);
                    }

//                    Integer[] data = new Integer[values.length];
//                    for (int i = 0; i < values.length; i++) {
//                        data[i] = Integer.valueOf(values[i].toString());
//                    }
//                    v = s.create(vectorName, data);

                }
                case Double -> {
                    Double[] data = new Double[values.length];
                    for (int i = 0; i < values.length; i++) {
                        data[i] = (Double) values[i];
                    }
                    v = s.create(vectorName, data);
                }
                case String -> {
                    String[] data = new String[values.length];
                    for (int i = 0; i < values.length; i++) {
                        data[i] = (String) values[i];
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
