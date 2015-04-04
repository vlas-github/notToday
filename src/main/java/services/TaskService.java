package services;

import utils.exception.BusinessException;
import web.controllers.vo.TaskVo;
import web.controllers.vo.UserVo;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface TaskService {
    TaskVo getById(String id) throws BusinessException;
    TaskVo get(String guid) throws BusinessException;
    List<TaskVo> getSubtasks(TaskVo task) throws BusinessException;
    List<TaskVo> getByUser(UserVo user) throws BusinessException;
    TaskVo add(TaskVo task, UserVo user) throws BusinessException;
    TaskVo update(TaskVo task) throws BusinessException;
}
