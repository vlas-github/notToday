package services;

import beans.BaseCatalog;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface CatalogService {
    <T extends BaseCatalog> List<T> getList(Class<T> clazz);
    <T extends BaseCatalog> T get(String id, Class<T> clazz);
}
