package web.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import web.controllers.vo.UserVo;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserVoValidator implements Validator<UserVo> {

    private Map<String, Object> errors = new HashMap<String, Object>();

    @Override
    public Object validate(UserVo user) {
        if (StringUtils.isEmpty(user.getEmail())) {
            errors.put("email is null", true);
        }
        try {
            InternetAddress email = new InternetAddress(user.getEmail());
            email.validate();
        } catch (AddressException e) {
            errors.put("email is not valid", true);
        }
        if (StringUtils.isEmpty(user.getName())) {
            errors.put("name is null", true);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            errors.put("password is null", true);
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
