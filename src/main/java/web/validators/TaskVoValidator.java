package web.validators;

import beans.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskVoValidator implements Validator<Task> {

    private Map<String, Object> errors = new HashMap<String, Object>();

    @Override
    public Object validate(Task taskVo) {
        if (StringUtils.isEmpty(taskVo.getText())) {
            errors.put("Text is null", true);
        }
        return errors;
    }

    @Override
    public Object validate(Collection<Task> list) {
        list.stream().forEach(x -> validate(x));
        return errors;
    }

    @Override
    public Map<String, Object> getErrors() {
        return errors;
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
