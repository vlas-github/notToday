package web.controllers.utils.converter;

import beans.RepeatCatalog;
import org.springframework.stereotype.Component;
import web.controllers.vo.RepeatCatalogVo;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Component
public class RepeatCatalogVoConverterProvider implements ConverterProvider<RepeatCatalog, RepeatCatalogVo> {
    @Override
    public RepeatCatalogVo convertSourceToTarget(RepeatCatalog from, RepeatCatalogVo to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setLocalizationCode(from.getLocalizationCode());
        to.setOrder(from.getOrder());
        return to;
    }

    @Override
    public RepeatCatalog convertTargetToSource(RepeatCatalogVo from, RepeatCatalog to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setLocalizationCode(from.getLocalizationCode());
        to.setOrder(from.getOrder());
        return to;
    }

    @Override
    public Class<RepeatCatalog> getSource() {
        return RepeatCatalog.class;
    }

    @Override
    public Class<RepeatCatalogVo> getTarget() {
        return RepeatCatalogVo.class;
    }
}
