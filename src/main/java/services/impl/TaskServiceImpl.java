package services.impl;

import beans.Task;
import beans.TaskParticipant;
import beans.User;
import dao.TaskDao;
import dao.TaskParticipantDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.TaskService;
import services.UserService;
import utils.exception.BusinessException;
import web.controllers.utils.converter.Converter;
import web.controllers.vo.TaskVo;
import web.controllers.vo.UserVo;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskParticipantDao taskParticipantDao;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Override
    @Transactional
    public List<TaskVo> getByUser(UserVo user) throws BusinessException {
        try {
            if (StringUtils.isEmpty(user.getId())) {
                return null;
            }
            List<TaskParticipant> taskParticipants = taskParticipantDao.getParticipant(converter.convert(user, User.class));
            List<Task> tasks = taskParticipants.stream()
                    .map((participant) -> taskDao.getLast(participant.getTaskGuid()))
                    .collect(Collectors.toList());
            return converter.convert(tasks);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public TaskVo add(TaskVo task, UserVo user) throws BusinessException {
        try {
            task.setId("");
            task.setGuid(UUID.randomUUID().toString());
            task.setActive(true);
            task.setChangeDate(new Date());
            task = converter.convert(taskDao.save(converter.convert(task)));
            if (task.getParentTask().getGuid() != null && StringUtils.isNotEmpty(task.getParentTask().getGuid())) {
                TaskVo parent = converter.convert(taskDao.getLast(task.getParentTask().getGuid()));
                parent.setHaveSubtasks(true);
                update(parent);
            }
            if (user != null && StringUtils.isNotEmpty(user.getId())) {
                user = converter.convert(userService.getUserById(user.getId()));
                if (user != null) {
                    TaskParticipant taskParticipant = new TaskParticipant();
                    taskParticipant.setUserId(user.getId());
                    taskParticipant.setTaskGuid(task.getGuid());
                    taskParticipant.setNotify(true);            // todo брать из настроек пользователя, когда это будет возможно
                    taskParticipantDao.save(taskParticipant);
                }
            }
            return converter.convert(task);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public TaskVo update(TaskVo task) throws BusinessException {
        try {
            if (StringUtils.isEmpty(task.getGuid())) {
                throw new Exception("guid is null");
            }
            Task last = taskDao.getLast(task.getGuid());
            task.setPrevious(last.getId());
            task.setChangeDate(new Date());
            task.setId(null);
            task = converter.convert(taskDao.save(converter.convert(task)));
            last.setNext(task.getId());
            last.setActive(false);
            taskDao.update(last);
            return task;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public TaskVo getById(String id) throws BusinessException {
        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("id is null");
            }
            return converter.convert(taskDao.getById(id));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public TaskVo get(String guid) throws BusinessException {
        try {
            if (StringUtils.isEmpty(guid)) {
                throw new Exception("guid is null");
            }
            return converter.convert(taskDao.getLast(guid));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public List<TaskVo> getSubtasks(TaskVo task) throws BusinessException {
        try {
            if (StringUtils.isEmpty(task.getGuid())) {
                throw new Exception("guid is null");
            }
            return converter.convert(taskDao.getSubtasks(converter.convert(task)));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
