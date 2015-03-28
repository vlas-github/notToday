package utils.json;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class CustomFilterProvider extends SimpleFilterProvider {

    @Override
    public BeanPropertyFilter findFilter(Object filterId) {
        Class id = (Class) filterId;
        BeanPropertyFilter f = null;
        while (id != Object.class && f == null) {
            f = _filtersById.get(id.getName());
            id = id.getSuperclass();
        }
        if (f == null) {
            f = _defaultFilter;
            if (f == null && _cfgFailOnUnknownId) {
                throw new IllegalArgumentException("No filter configured with id '"+filterId+"' (type "
                        +filterId.getClass().getName()+")");
            }
        }
        return f;
    }

}