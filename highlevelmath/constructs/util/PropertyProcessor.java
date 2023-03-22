package highlevelmath.constructs.util;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SupportedAnnotationTypes({"Commutative", "Associative", "Distributive"})
public class PropertyProcessor extends AbstractProcessor {

    private final Map<String, Set<String>> commutativeOps = new HashMap<>();
    private final Map<String, Set<String>> associativeOps = new HashMap<>();
    private final Map<String, Set<String>> distributiveOps = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //Loop through the annotations
        for (TypeElement annotation : annotations) {
            //Decide what check to do depending on which annotation is being checked
            switch(annotation.getSimpleName().toString()){
                case "Commutative" -> {
                    generalCheck(annotation, roundEnv, this::commutativity);
                }
                case "Associative" -> {
                    generalCheck(annotation, roundEnv, this::associativity);
                }
                case "Distributive" -> {
                    generalCheck(annotation, roundEnv, this::distributivity);
                }
            }
        }
        //Processing complete
        return true;
    }

    private void commutativity(){

    }

    private void associativity(){

    }

    private void distributivity(){

    }

    private void generalCheck(TypeElement annotation, RoundEnvironment roundEnv, PropertyCheck specificCheck){
        //Receive all elements annotated with the current annotation.
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
        for(Element e : annotatedElements){
            //Make sure that this annotation is only applied to methods
            if (e.getKind() != ElementKind.METHOD) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "Annotation @Associative can only be used on methods", e);
                continue;
            }
            //Perform check
            ExecutableElement method = (ExecutableElement) e;
            //Methods enclosing class, name, and operator
            String className = ((TypeElement) method.getEnclosingElement()).getQualifiedName().toString();
            String methodName = method.getSimpleName().toString();
            Elements elementUtils = processingEnv.getElementUtils();
            // Gets the operator kind associated with the method, if it exists
            OperatorKind operatorKind = elementUtils.getOperatorKind(method);
            String op = processingEnv.getElementUtils().
            String op = processingEnv.getElementUtils().getBinaryOperator(method).toString();

            if (!commutativeOps.containsKey(className)) {
                commutativeOps.put(className, new HashSet<>());
            }
            commutativeOps.get(className).add(op);
            specificCheck.check();
        }
    }

    @FunctionalInterface
    private interface PropertyCheck {
        void check();
    }

}
