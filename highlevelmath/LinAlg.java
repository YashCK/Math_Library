//package highlevelmath;
//
//import highlevelmath.constructs.structures.CVector;
//import highlevelmath.constructs.structures.Complex;
//import highlevelmath.constructs.structures.Vec;
//import highlevelmath.constructs.structures.Vector;
//import highlevelmath.constructs.util.VecFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class LinAlg {
//
//    private enum ElementType {
//        NUMBER, REAL, COMPLEX;
//        public <T, S> Vec<?, ?> createVector(T[] values, S scalar) {
//            switch(this) {
//                case REAL    -> {
//                    RealVectorFactory realV = Vector::new;
//                    double[] vals = new double[values.length];
//                    for(int i = 0; i < values.length; i++){
//                        vals[i] = (Double) values[i];
//                    }
//                    return realV.create(vals);
//                }
//                case COMPLEX -> {
//                    VecFactory<Complex, CVector> complexV = CVector::new;
//                    return complexV.create(values);
//                }
//                case NUMBER  -> {
//                    VecFactory<T, S> customV = null;
//                    return customV.create(values);
//                }
//                default -> {
//                    return null;
//                }
//            }
//        }
//
//    }
//
//    private enum ScalarType {
//        NUMBER, REAL, COMPLEX;
//    }
//
//    private static Map<String, ElementFactory> mapFactories = new HashMap<String, ElementFactory>();
//
//    static void register(ElementFactory f){
//        mapFactories.put(f.type, f);
//    }
//
//    private static Vec<T, S> createVector(shape, VecType){
//        ElementFactory fact = mapFactories.get(vectType);
//        return fact.create();
//    }
//
//    public static Vec<T, S> createVector(shape, customFactory){
//
//    }
//
//    private static <T, S> Vec<T, S> test(Vec<T, S> v)
//
//    @FunctionalInterface
//    private interface RealVectorFactory {
//        Vec<Double, Double> create(double[] values);
//    }
//
//}
//
//public class ElementFactory {
//
//    public ElementFactory(){
//        LinAlg.register(this);
//    }
//
//    public Vec<number, number> create(){
//        return the thing
//    };
//
//}