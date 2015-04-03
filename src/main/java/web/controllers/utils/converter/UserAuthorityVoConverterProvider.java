package web.controllers.utils.converter;

import beans.User;
import beans.UserAuthority;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import services.UserService;
import utils.exception.BusinessException;
import web.controllers.vo.UserAuthorityVo;
import web.controllers.vo.UserVo;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Component
public class UserAuthorityVoConverterProvider implements ConverterProvider<UserAuthority, UserAuthorityVo> {

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Autowired
    private UserService userService;

    @Override
    public UserAuthorityVo convertSourceToTarget(UserAuthority from, UserAuthorityVo to) {
        to.setId(from.getId());
        to.setValue(from.getValue());
        to.setUser(null);
        if (StringUtils.isNotEmpty(from.getUserId())) {
            try {
                User user = userService.getUserById(from.getUserId());
                user.setAuthorities(null);
                to.setUser(converter.convert(user, UserVo.class));
            } catch (BusinessException e) { }
        }
        return to;
    }

    @Override
    public UserAuthority convertTargetToSource(UserAuthorityVo from, UserAuthority to) {
        to.setId(from.getId());
        to.setValue(from.getValue());
        to.setUserId(null);
        if (from.getUser() != null && StringUtils.isNotEmpty(from.getUser().getId())) {
            to.setUserId(from.getUser().getId());
        }
        return to;
    }

    @Override
    public Class<UserAuthority> getSource() {
        return UserAuthority.class;
    }

    @Override
    public Class<UserAuthorityVo> getTarget() {
        return UserAuthorityVo.class;
    }
}
