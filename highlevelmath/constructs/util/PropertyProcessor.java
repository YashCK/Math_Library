package highlevelmath.constructs.util;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.*;

@SupportedAnnotationTypes({"Commutative", "Associative", "Distributive"})
public class PropertyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //Loop through the annotations
        for (TypeElement annotation : annotations) {
            //Decide what check to do depending on which annotation is being checked
            switch (annotation.getSimpleName().toString()) {
                case "Commutative" -> {
                    return generalCheck(annotation, roundEnv, "Commutative", this::commutativity);
                }
                case "Associative" -> {
                    return generalCheck(annotation, roundEnv, "Associative", this::associativity);
                }
                case "Distributive" -> {
                    return generalCheck(annotation, roundEnv, "Distributive", this::distributivity);
                }
            }
        }
        //Processing complete
        return true;
    }

    private boolean commutativity(Element e) {
        ExecutableElement method = (ExecutableElement) e;
        TypeMirror returnType = method.getReturnType();
        List<? extends TypeMirror> typeArgs = getTypeArguments(returnType);
        if (typeArgs.isEmpty()) {
            return true;
        }
        TypeMirror firstTypeArg = typeArgs.get(0);
        for (TypeMirror typeArg : typeArgs) {
            if (!processingEnv.getTypeUtils().isSameType(firstTypeArg, typeArg)) {
                return false;
            }
        }
        return true;
    }

    private boolean associativity(Element e) {
        return false;
    }

    private boolean distributivity(Element e) {
        return false;
    }

    private boolean generalCheck(TypeElement annotation, RoundEnvironment roundEnv, String name, PropertyCheck specificCheck) {
        //Receive all elements annotated with the current annotation.
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
        for (Element e : annotatedElements) {
            //Make sure that this annotation is only applied to methods
            if (e.getKind() != ElementKind.METHOD) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "Annotation @" + name + " can only be used on methods", e);
                continue;
            }
            //Perform check
            if (!specificCheck.check(e)) {
                //Commutativity -> Commutative
                String trueName = name.substring(0, name.length() - 3) + "e";
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        trueName + " property was not satisfied", e);
                return false;
            }
        }
        return true;
    }

    private List<? extends TypeMirror> getTypeArguments(TypeMirror type) {
        if (type.getKind() == TypeKind.DECLARED) {
            return ((DeclaredType) type).getTypeArguments();
        } else {
            return Collections.emptyList();
        }
    }

    @FunctionalInterface
    private interface PropertyCheck {
        boolean check(Element e);
    }

}
