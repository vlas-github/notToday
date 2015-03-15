package services.impl;

import beans.Task;
import beans.User;
import dao.TaskDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.TaskService;
import utils.converter.vo.TaskVoConverter;
import utils.converter.vo.UserVoConverter;
import utils.exception.BusinessException;
import vo.TaskVo;
import vo.UserVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private UserVoConverter userVoConverter;
    @Autowired
    private TaskVoConverter taskVoConverter;
    @Autowired
    private TaskDao taskDao;

    @Override
    @Transactional
    public List<TaskVo> getByUser(UserVo user) throws BusinessException {
        try {
            if (StringUtils.isEmpty(user.getEmail())) {
                throw new BusinessException("Email - is null!");
            }
            return taskDao.getList(userVoConverter.convertTargetToSource(user, new User()))
                    .stream()
                    .map(x -> taskVoConverter.convertSourceToTarget(x, new TaskVo()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public TaskVo add(TaskVo task) throws BusinessException {
        try {
            return taskVoConverter.convertSourceToTarget(
                    taskDao.save(taskVoConverter.convertTargetToSource(task, new Task())),
                    new TaskVo());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public TaskVo update(TaskVo task) throws BusinessException {
        try {
            return taskVoConverter.convertSourceToTarget(
                    taskDao.saveOrUpdate(taskVoConverter.convertTargetToSource(task, new Task())),
                    new TaskVo());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public TaskVo getById(String id) throws BusinessException {
        try {
            TaskVo task = new TaskVo();
            task.setId(id);
            return taskVoConverter.convertSourceToTarget(
                    taskDao.getById(taskVoConverter.convertTargetToSource(task, new Task())),
                    new TaskVo());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
