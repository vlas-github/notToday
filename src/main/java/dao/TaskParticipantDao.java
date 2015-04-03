package dao;

import beans.TaskParticipant;
import beans.User;
import beans.Task;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
public interface TaskParticipantDao {

    List<TaskParticipant> getParticipant(Task task);
    List<TaskParticipant> getParticipant(User user);
}
