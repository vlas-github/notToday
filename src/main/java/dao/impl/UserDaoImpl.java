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
    public User getById(User user) {
        try {
            return (User) getSession().get(User.class, user.getId());
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
    public User saveOrUpdate(User user) {
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
            Criteria cr = getSession().createCriteria(User.class);

            if (StringUtils.isNotEmpty(pattern.getId())) {
                cr.add(Restrictions.eq("id", pattern.getId()));
            }

            if (StringUtils.isNotEmpty(pattern.getFio())) {
                String [] names = pattern.getFio().split(" ");
                StringBuilder fullName = new StringBuilder();
                for (String name : names) {
                    fullName.append("%"+name+"%");
                }
                cr.add(Restrictions.like("fio", fullName.toString()));
            }

            if (StringUtils.isNotEmpty(pattern.getEmail())) {
                cr.add(Restrictions.like("email", "%"+pattern.getEmail()+"%"));
            }

            if (StringUtils.isNotEmpty(pattern.getLocality())) {
                cr.add(Restrictions.like("locality", "%"+pattern.getLocality()+"%"));
            }

            return cr.list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }
}
