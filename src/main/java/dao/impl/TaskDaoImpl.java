package dao.impl;

import beans.Task;
import dao.TaskDao;
import org.hibernate.criterion.Order;
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
    public Task getById(String id) {
        try {
            return (Task) getSession().get(Task.class, id);
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
    public Task update(Task task) {
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
            return getSession().createCriteria(Task.class)
                    .add(Restrictions.eq("active", true))
                    .addOrder(Order.asc("creationDate")).list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<Task> getList(Task pattern) {
        try {
            throw new Exception("don't work");
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }


    @Override
    public Task getLast(String guid) {
        return (Task) getSession().createCriteria(Task.class)
                .add(Restrictions.eq("guid", guid))
                .add(Restrictions.eq("active", true))
                .addOrder(Order.asc("text")).uniqueResult();
    }

    @Override
    public List<Task> getSubtasks(Task task) {
        if (task.getHaveSubtasks()) {
            return getSession().createCriteria(Task.class)
                    .add(Restrictions.eq("active", true))
                    .add(Restrictions.eq("parentTask", task.getGuid()))
                    .addOrder(Order.asc("text")).list();
        }
        return null;
    }
}
