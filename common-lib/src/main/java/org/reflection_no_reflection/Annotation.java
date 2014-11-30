package org.reflection_no_reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class of all the annotation classes inside the RNR framework.
 * It is used as both :
 * <ul>
 *   <li>a data holder when processing annotated members in the annotation processor. Instances contains all information related to bindings.</li>
 *   <li>the super class of all classes that will be generated by the annotation processor. These classes will be true annotation.</li>
 * </ul>
 * @author SNI
 */
public class Annotation implements java.lang.annotation.Annotation {

    /** The class name of this annotation type. */
    private String annotationTypeName;
    /** Maps method names to their return values. The methods are methods of this annotation. */
    private Map<String,Object> mapMethodToValue = new HashMap<>();
    /** Maps method names to their return types. The methods are methods of this annotation. */
    private Map<String,String> mapMethodToReturnType = new HashMap<>();

    /**
     * Creates a new annotation.
     * @param annotationTypeName class name of this annotation type.
     * @param mapMethodToValue maps method names to their return values.
     * @param mapMethodToReturnType maps method names to their return types.
     */
    public Annotation(String annotationTypeName, Map<String, Object> mapMethodToValue,Map<String,String> mapMethodToReturnType) {
        this.annotationTypeName = annotationTypeName;
        this.mapMethodToValue = mapMethodToValue;
        this.mapMethodToReturnType = mapMethodToReturnType;
    }

    public String getAnnotationTypeName() {
        return annotationTypeName;
    }

    public Map<String, Object> getMapMethodToValue() {
        return mapMethodToValue;
    }

    public Map<String, String> getMapMethodToReturnType() {
        return mapMethodToReturnType;
    }

    @Override
    public Class<? extends java.lang.annotation.Annotation> annotationType() {
        try {
            return (Class<? extends java.lang.annotation.Annotation>) Class.forName(annotationTypeName);
        } catch (Exception ex) {
            throw new RuntimeException("Impossible to load the annotation type via the class loader. Type name is " + annotationTypeName, ex);
        }
    }
}
