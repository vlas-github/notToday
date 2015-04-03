package services;

import beans.User;
import utils.exception.BusinessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
public interface UserService {

    User getUserByEmail(User user) throws BusinessException;
    User getUserById(String id) throws BusinessException;
    User update(User user) throws BusinessException;
    User save(User user) throws BusinessException;
}
