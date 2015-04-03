package dao.impl;

import beans.User;
import dao.UserDao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.exception.DataAccessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
@Repository
public class UserDaoImpl extends GenericDao implements UserDao {

    @Override
    public User getById(String id) {
        try {
            return (User) getSession().get(User.class, id);
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public User save(User user) {
        try {
            getSession().save(user);
            getSession().flush();
            return user;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public User update(User user) {
        try {
            getSession().saveOrUpdate(user);
            getSession().flush();
            return user;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<User> getList() {
        try {
            return getSession().createCriteria(User.class).list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<User> getList(User pattern) {
        try {
            throw new Exception("don't work");
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }
}
