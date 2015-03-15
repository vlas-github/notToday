package utils.converter.vo;

import beans.Task;
import beans.User;
import beans.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vo.TaskVo;
import vo.UserAuthorityVo;
import vo.UserVo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Component
public class UserVoConverter implements Converter<User, UserVo> {

    @Autowired
    Converter<Task, TaskVo> taskVoConverter;
    @Autowired
    Converter<UserAuthority, UserAuthorityVo> userAuthorityVoConverter;

    @Override
    public UserVo convertSourceToTarget(User from, UserVo to) {
        to.setEmail(from.getEmail());
        to.setFio(from.getFio());
        to.setId(from.getId());
        to.setIsAccountNonLocked(from.getIsAccountNonLocked());
        to.setLocality(from.getLocality());
        to.setPassHash(from.getPassHash());
        to.setRegistrationDate(from.getRegistrationDate());
        if (from.getUserAuthorities() != null) {
            to.setUserAuthorities(from.getUserAuthorities()
                    .stream()
                    .map(authority -> userAuthorityVoConverter.convertSourceToTarget(authority, new UserAuthorityVo()))
                    .collect(Collectors.toSet()));
        }
        return to;
    }

    @Override
    public User convertTargetToSource(UserVo from, User to) {
        to.setEmail(from.getEmail());
        to.setFio(from.getFio());
        to.setId(from.getId());
        to.setIsAccountNonLocked(from.getIsAccountNonLocked());
        to.setLocality(from.getLocality());
        to.setPassHash(from.getPassHash());
        to.setRegistrationDate(from.getRegistrationDate());
        if (from.getUserAuthorities() != null) {
            to.setUserAuthorities(from.getUserAuthorities()
                    .stream()
                    .map(authority -> userAuthorityVoConverter.convertTargetToSource(authority, new UserAuthority()))
                    .collect(Collectors.toSet()));
        }
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
