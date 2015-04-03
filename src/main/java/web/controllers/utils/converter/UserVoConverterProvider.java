package web.controllers.utils.converter;

import beans.User;
import beans.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import web.controllers.vo.UserAuthorityVo;
import web.controllers.vo.UserVo;

import java.util.Date;
import java.util.Set;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Component
public class UserVoConverterProvider implements ConverterProvider<User, UserVo> {

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Override
    public UserVo convertSourceToTarget(User from, UserVo to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setEmail(from.getEmail());
        to.setLogin(from.getLogin());
        to.setPassword(from.getPassword());
        to.setLocality(from.getLocality());
        to.setAuthorities(converter.convert(from.getAuthorities(), UserAuthorityVo.class));
        to.setIsAccountNonLocked(from.getIsAccountNonLocked());
        to.setRegistrationDate(from.getRegistrationDate());
        to.setLastActivityDate(from.getLastActivityDate());
        return to;
    }

    @Override
    public User convertTargetToSource(UserVo from, User to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setEmail(from.getEmail());
        to.setLogin(from.getLogin());
        to.setPassword(from.getPassword());
        to.setLocality(from.getLocality());
        to.setAuthorities(converter.convert(from.getAuthorities(), UserAuthority.class));
        to.setIsAccountNonLocked(from.getIsAccountNonLocked());
        to.setRegistrationDate(from.getRegistrationDate());
        to.setLastActivityDate(from.getLastActivityDate());
        return to;
    }

    @Override
    public Class<User> getSource() {
        return User.class;
    }

    @Override
    public Class<UserVo> getTarget() {
        return UserVo.class;
    }
}
