package web.validators;

import beans.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.UserService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Component
public class UserVoValidator implements Validator<User> {

    @Autowired
    private UserService userService;
    private Map<String, Object> errors = new HashMap<String, Object>();

    @Override
    public Object validate(User user) {
        if (StringUtils.isEmpty(user.getEmail())) {
            errors.put("email is null", true);
        }
        if (false /*todo*/) {
            errors.put("email is not valid", true);
        }
        if (StringUtils.isEmpty(user.getFio())) {
            errors.put("name is null", true);
        }
        if (StringUtils.isEmpty(user.getPassHash())) {
            errors.put("pass is null", true);
        }
        return errors;
    }

    @Override
    public Object validate(Collection<User> list) {
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
