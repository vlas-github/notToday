package web.controllers.utils.converter;

import beans.UserAuthorityCatalog;
import org.springframework.stereotype.Component;
import web.controllers.vo.UserAuthorityCatalogVo;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Component
public class UserAuthorityCatalogVoConverterProvider implements ConverterProvider<UserAuthorityCatalog, UserAuthorityCatalogVo> {
    @Override
    public UserAuthorityCatalogVo convertSourceToTarget(UserAuthorityCatalog from, UserAuthorityCatalogVo to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setLocalizationCode(from.getLocalizationCode());
        to.setOrder(from.getOrder());
        return to;
    }

    @Override
    public UserAuthorityCatalog convertTargetToSource(UserAuthorityCatalogVo from, UserAuthorityCatalog to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setLocalizationCode(from.getLocalizationCode());
        to.setOrder(from.getOrder());
        return to;
    }

    @Override
    public Class<UserAuthorityCatalog> getSource() {
        return UserAuthorityCatalog.class;
    }

    @Override
    public Class<UserAuthorityCatalogVo> getTarget() {
        return UserAuthorityCatalogVo.class;
    }
}
