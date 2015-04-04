package services;

import web.controllers.vo.BaseCatalogVo;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface CatalogService {
    <T extends BaseCatalogVo> List<T> getList(Class<T> clazz);
    <T extends BaseCatalogVo> T get(String id, Class<T> clazz);
}
