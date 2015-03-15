package services;

import utils.exception.BusinessException;
import vo.TaskVo;
import vo.UserVo;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface TaskService {
    List<TaskVo> getByUser(UserVo user) throws BusinessException;
    TaskVo getById(String id) throws BusinessException;
    TaskVo add(TaskVo task) throws BusinessException;
    TaskVo update(TaskVo task) throws BusinessException;
}
