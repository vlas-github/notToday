package services;

import utils.exception.BusinessException;
import web.controllers.vo.UserVo;


/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
public interface UserService {

    UserVo getUserByEmail(UserVo user) throws BusinessException;
    UserVo getUserById(String id) throws BusinessException;
    UserVo update(UserVo user) throws BusinessException;
    UserVo save(UserVo user) throws BusinessException;
}
