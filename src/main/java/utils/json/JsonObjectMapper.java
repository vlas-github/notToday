package utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class JsonObjectMapper extends ObjectMapper {

    CustomFilterProvider filterProvider;

    public JsonObjectMapper() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        setAnnotationIntrospector(new CustomIntrospector());
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        filterProvider = new CustomFilterProvider();
        filterProvider.setFailOnUnknownId(false);
        setFilters(filterProvider);
        Hibernate4Module hibernateModule = new Hibernate4Module();
        hibernateModule.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, true);
        registerModule(hibernateModule);
    }

    public JsonObjectMapper addFilterAllExceptFilter(Class clazz, String... properties) {
        filterProvider.addFilter(clazz.getName(), SimpleBeanPropertyFilter.filterOutAllExcept(properties));
        return this;
    }

    public JsonObjectMapper addSerializeAllExceptFilter(Class clazz, String... properties) {
        filterProvider.addFilter(clazz.getName(), SimpleBeanPropertyFilter.serializeAllExcept(properties));
        return this;
    }

}