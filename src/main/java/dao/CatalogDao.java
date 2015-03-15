package dao;

import beans.GenericCatalog;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface CatalogDao {
    <T extends GenericCatalog> List<T> getList(Class<T> clazz);
    <T extends GenericCatalog> T get(String id, Class<T> clazz);
}
