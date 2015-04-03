package dao.impl;

import beans.Task;
import beans.TaskParticipant;
import beans.User;
import dao.TaskParticipantDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.exception.DataAccessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Repository
public class TaskParticipantDaoImpl extends GenericDao implements TaskParticipantDao {

    @Override
    public List<TaskParticipant> getParticipant(Task task) {
        try {
            return getSession().createCriteria(TaskParticipant.class)
                    .add(Restrictions.eq("_task_guid", task.getGuid())).list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public List<TaskParticipant> getParticipant(User user) {
        try {
            return getSession().createCriteria(TaskParticipant.class)
                    .add(Restrictions.eq("userId", user.getId())).list();
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }

    @Override
    public TaskParticipant save(TaskParticipant taskParticipant) {
        try {
            getSession().save(taskParticipant);
            getSession().flush();
            return taskParticipant;
        } catch (Throwable t) {
            throw new DataAccessException(t);
        }
    }
}
