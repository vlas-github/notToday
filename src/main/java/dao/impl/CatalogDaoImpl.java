package dao.impl;

import beans.GenericCatalog;
import dao.CatalogDao;
import org.springframework.stereotype.Repository;
import utils.exception.DataAccessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Repository
public class CatalogDaoImpl extends GenericDao implements CatalogDao {
    @Override
    public <T extends GenericCatalog> List<T> getList(Class<T> clazz) {
        try {
            return (List<T>) getSession().createCriteria(clazz).list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }
    @Override
    public <T extends GenericCatalog> T get(String id, Class<T> clazz) {
        try {
            return (T) getSession().get(clazz, id);
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }
}
