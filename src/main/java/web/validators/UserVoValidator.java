package web.validators;

import beans.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.UserService;
import utils.converter.vo.UserVoConverter;
import utils.exception.BusinessException;
import vo.UserVo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Component
public class UserVoValidator implements Validatir<UserVo> {

    @Autowired
    private UserService userService;
    private Map<String, Object> errors = new HashMap<String, Object>();

    @Override
    public Object validate(UserVo userVo) {
        if (StringUtils.isEmpty(userVo.getEmail())) {
            errors.put("email is null", true);
        }
        if (false /*todo*/) {
            errors.put("email is not valid", true);
        }
        if (StringUtils.isEmpty(userVo.getFio())) {
            errors.put("name is null", true);
        }
        if (StringUtils.isEmpty(userVo.getPassHash())) {
            errors.put("pass is null", true);
        }
        try {
            if (userService.getUserByEmail(userVo) != null) {
                errors.put("email not unique", true);
            }
        } catch (BusinessException e) {
            errors.put("email validation error", true);
        }
        return errors;
    }

    @Override
    public Object validate(Collection<UserVo> list) {
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
