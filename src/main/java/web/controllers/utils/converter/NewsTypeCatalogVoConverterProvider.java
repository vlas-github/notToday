package web.controllers.utils.converter;

import beans.NewsTypeCatalog;
import org.springframework.stereotype.Component;
import web.controllers.vo.NewsTypeCatalogVo;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Component
public class NewsTypeCatalogVoConverterProvider implements ConverterProvider<NewsTypeCatalog, NewsTypeCatalogVo> {
    @Override
    public NewsTypeCatalogVo convertSourceToTarget(NewsTypeCatalog from, NewsTypeCatalogVo to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setLocalizationCode(from.getLocalizationCode());
        to.setOrder(from.getOrder());
        return to;
    }

    @Override
    public NewsTypeCatalog convertTargetToSource(NewsTypeCatalogVo from, NewsTypeCatalog to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setLocalizationCode(from.getLocalizationCode());
        to.setOrder(from.getOrder());
        return to;
    }

    @Override
    public Class<NewsTypeCatalog> getSource() {
        return NewsTypeCatalog.class;
    }

    @Override
    public Class<NewsTypeCatalogVo> getTarget() {
        return NewsTypeCatalogVo.class;
    }
}
