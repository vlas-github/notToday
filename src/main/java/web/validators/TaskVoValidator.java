package web.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.TaskService;
import services.UserService;
import vo.TaskVo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Component
public class TaskVoValidator implements Validatir<TaskVo> {

    @Autowired
    private TaskService taskService;
    private Map<String, Object> errors = new HashMap<String, Object>();

    @Override
    public Object validate(TaskVo taskVo) {
        if (StringUtils.isEmpty(taskVo.getTitle())) {
            errors.put("Title is null", true);
        }
        return errors;
    }

    @Override
    public Object validate(Collection<TaskVo> list) {
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
