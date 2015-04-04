package dao.impl;

import beans.News;
import dao.NewsDao;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.exception.DataAccessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Repository
public class NewsDaoImpl extends GenericDao implements NewsDao {

    @Override
    public News getById(String id) {
        try {
            return (News) getSession().get(News.class, id);
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public News save(News news) {
        try {
            getSession().save(news);
            getSession().flush();
            return news;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public News update(News news) {
        try {
            getSession().saveOrUpdate(news);
            getSession().flush();
            return news;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<News> getList() {
        try {
            return getSession().createCriteria(News.class).list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<News> getList(News pattern) {
        try {
            throw new Exception("don't work");
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public News getLast(String guid) {
        return (News) getSession().createCriteria(News.class)
                .add(Restrictions.eq("_guid", guid))
                .add(Restrictions.eq("_active", true))
                .addOrder(Order.asc("_creation_date")).uniqueResult();
    }
}
