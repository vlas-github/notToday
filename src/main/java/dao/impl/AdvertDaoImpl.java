package dao.impl;

import beans.Advert;
import dao.AdvertDao;
import org.springframework.stereotype.Repository;
import utils.exception.DataAccessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Repository
public class AdvertDaoImpl extends GenericDao implements AdvertDao {
    @Override
    public Advert getById(Advert advert) {
        try {
            return (Advert) getSession().get(Advert.class, advert.getId());
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public Advert save(Advert advert) {
        try {
            getSession().save(advert);
            getSession().flush();
            return advert;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public Advert saveOrUpdate(Advert advert) {
        try {
            getSession().saveOrUpdate(advert);
            getSession().flush();
            return advert;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<Advert> getList() {
        try {
            return getSession().createCriteria(Advert.class).list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<Advert> getList(Advert pattern) {
        try {
            throw new Exception("don't work");
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }
}
