package web.validators;

import java.util.Collection;
import java.util.Map;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface Validatir<T> {
    Object validate(T t);
    Object validate(Collection<T> list);
    Map<String, Object> getErrors();
    boolean hasErrors();
}
