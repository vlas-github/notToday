package services;

import beans.Task;
import beans.TaskParticipant;
import beans.User;
import utils.exception.BusinessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface TaskService {
    Task getById(String id) throws BusinessException;
    Task get(String guid) throws BusinessException;
    List<Task> getSubtasks(Task task) throws BusinessException;
    List<Task> getByUser(User user) throws BusinessException;
    Task add(Task task, User user) throws BusinessException;
    Task update(Task task) throws BusinessException;
}
