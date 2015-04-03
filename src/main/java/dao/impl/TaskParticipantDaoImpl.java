package dao.impl;

import beans.Task;
import beans.TaskParticipant;
import beans.User;
import dao.TaskParticipantDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Repository
public class TaskParticipantDaoImpl extends GenericDao implements TaskParticipantDao {

    @Override
    public List<TaskParticipant> getParticipant(Task task) {
        return getSession().createCriteria(TaskParticipant.class)
                .add(Restrictions.eq("_task_guid", task.getGuid())).list();
    }

    @Override
    public List<TaskParticipant> getParticipant(User user) {
        return getSession().createCriteria(TaskParticipant.class)
                .add(Restrictions.eq("_user_id", user.getId())).list();
    }
}
