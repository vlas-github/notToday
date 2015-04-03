package dao;

import beans.BaseCatalog;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface CatalogDao {
    <T extends BaseCatalog> List<T> getList(Class<T> clazz);
    <T extends BaseCatalog> T get(String id, Class<T> clazz);
}
