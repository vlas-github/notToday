package services;

import beans.Task;
import beans.User;
import utils.exception.BusinessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface TaskService {
    List<Task> getByUser(User user) throws BusinessException;
    Task getById(String id) throws BusinessException;
    Task add(Task task) throws BusinessException;
    Task update(Task task) throws BusinessException;
}
