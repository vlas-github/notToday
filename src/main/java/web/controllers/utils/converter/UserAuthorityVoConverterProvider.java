package web.controllers.utils.converter;

import beans.User;
import beans.UserAuthority;
import dao.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserDao userDao;

    @Override
    public UserAuthorityVo convertSourceToTarget(UserAuthority from, UserAuthorityVo to) {
        if (to == null) {
            to = new UserAuthorityVo();
        }
        to.setId(from.getId());
        to.setValue(from.getValue());
        to.setUser(null);
        if (StringUtils.isNotEmpty(from.getUserId())) {
            to.setUser(null);
        }
        return to;
    }

    @Override
    public UserAuthority convertTargetToSource(UserAuthorityVo from, UserAuthority to) {
        if (to == null) {
            to = new UserAuthority();
        }
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
