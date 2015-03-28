package services.impl;

import beans.Task;
import beans.User;
import dao.TaskDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.TaskService;
import utils.exception.BusinessException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    @Transactional
    public List<Task> getByUser(User user) throws BusinessException {
        try {
            if (StringUtils.isEmpty(user.getEmail())) {
                throw new BusinessException("Email - is null!");
            }
            List<Task> tasks = taskDao.getList(user).stream().map((t) -> t.clone()).collect(Collectors.toList());

            return tasks;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public Task add(Task task) throws BusinessException {
        try {
            return taskDao.save(task).clone();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public Task update(Task task) throws BusinessException {
        try {
            return taskDao.saveOrUpdate(task).clone();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public Task getById(String id) throws BusinessException {
        try {
            Task task = new Task();
            task.setId(id);
            task = taskDao.getById(task).clone();
            return task;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
