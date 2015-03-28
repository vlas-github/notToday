package dao.impl;

import beans.Task;
import beans.User;
import dao.TaskDao;
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
public class TaskDaoImpl extends GenericDao implements TaskDao {

    @Override
    public Task getById(Task task) {
        try {
            return (Task) getSession().get(Task.class, task.getId());
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public Task save(Task task) {
        try {
            getSession().save(task);
            getSession().flush();
            return task;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public Task saveOrUpdate(Task task) {
        try {
            getSession().saveOrUpdate(task);
            getSession().flush();
            return task;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<Task> getList() {
        try {
            return getSession().createCriteria(Task.class).list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<Task> getList(Task pattern) {
        try {
            Criteria cr = getSession().createCriteria(Task.class);

            if (StringUtils.isNotEmpty(pattern.getId())) {
                cr.add(Restrictions.eq("id", pattern.getId()));
            }

            if (StringUtils.isNotEmpty(pattern.getTitle())) {
                cr.add(Restrictions.like("title", "%"+pattern.getTitle()+"%"));
            }

            if (StringUtils.isNotEmpty(pattern.getDescription())) {
                cr.add(Restrictions.like("description", "%"+pattern.getDescription()+"%"));
            }

            if (pattern.isCompleted() != null) {
                cr.add(Restrictions.like("isComplete", pattern.isCompleted()));
            }

            if (pattern.isDeleted() != null) {
                cr.add(Restrictions.like("isDelete", pattern.isDeleted()));
            }

            return cr.list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<Task> getList(User user) {
        return getSession().createCriteria(Task.class)
                .add(Restrictions.eq("user", user)).list();
    }
}
