package services;

import beans.Task;
import beans.User;
import org.springframework.transaction.annotation.Transactional;
import utils.exception.BusinessException;
import vo.TaskVo;
import vo.UserVo;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
public interface UserService {

    UserVo getUserByEmail(UserVo user) throws BusinessException;
    UserVo getUserById(String id) throws BusinessException;
    UserVo saveOrUpdate(UserVo user) throws BusinessException;
    UserVo save(UserVo user) throws BusinessException;
}
