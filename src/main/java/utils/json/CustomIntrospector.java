package utils.json;

import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class CustomIntrospector extends JacksonAnnotationIntrospector {

    @Override
    public Object findFilterId(AnnotatedClass a) {
        return a.getRawType();
    }
}