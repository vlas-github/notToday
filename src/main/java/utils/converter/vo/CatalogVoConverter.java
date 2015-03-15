package utils.converter.vo;

import beans.GenericCatalog;
import vo.GenericCatalogVo;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public class CatalogVoConverter<F extends GenericCatalog, T extends GenericCatalogVo> implements Converter<F,T> {
    @Override
    public T convertSourceToTarget(F from, T to) {
        to.setId(from.getId());
        to.setLocalizationCode(from.getLocalizationCode());
        to.setName(from.getName());
        to.setOrder(from.getOrder());
        return to;
    }

    @Override
    public F convertTargetToSource(T from, F to) {
        to.setId(from.getId());
        to.setLocalizationCode(from.getLocalizationCode());
        to.setName(from.getName());
        to.setOrder(from.getOrder());
        return to;
    }

    @Override
    public Class<F> getSource() {
        return null;
    }

    @Override
    public Class<T> getTarget() {
        return null;
    }
}
