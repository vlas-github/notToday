package utils.converter.vo;

import beans.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vo.UserAuthorityVo;
import vo.UserVo;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Component
public class UserAuthorityVoConverter implements Converter<UserAuthority, UserAuthorityVo> {

    @Override
    public UserAuthorityVo convertSourceToTarget(UserAuthority from, UserAuthorityVo to) {
        to.setId(from.getId());
        to.setValue(from.getValue());
        return to;
    }

    @Override
    public UserAuthority convertTargetToSource(UserAuthorityVo from, UserAuthority to) {
        to.setId(from.getId());
        to.setValue(from.getValue());
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
