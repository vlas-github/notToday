package services.impl;

import beans.Task;
import beans.User;
import dao.TaskDao;
import dao.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.UserService;
import utils.converter.vo.TaskVoConverter;
import utils.converter.vo.UserVoConverter;
import utils.exception.BusinessException;
import vo.TaskVo;
import vo.UserVo;
import web.validators.TaskVoValidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    private UserVoConverter userVoConverter;

    @Override
    @Transactional
    public UserVo getUserByEmail(UserVo user) throws BusinessException {
        try {
            if (StringUtils.isEmpty(user.getEmail())) {
                throw new BusinessException("Validate email - is null!");
            }
            User pattern = new User();
            pattern.setEmail(user.getEmail());
            List<User> users = userDao.getList(userVoConverter.convertTargetToSource(user, new User()));
            if (users.size() > 1) {
                throw new BusinessException("Two users in db with one email!");
            }
            if (users.size() == 0) {
                return null;
            }
            return userVoConverter.convertSourceToTarget(users.get(0), new UserVo());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public UserVo getUserById(String id) throws BusinessException {
        try {
            User user = new User();
            user.setId(id);
            user = userDao.getById(user);
            if (user == null) {
                throw new BusinessException("User not found");
            }
            return userVoConverter.convertSourceToTarget(user,new UserVo());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public UserVo saveOrUpdate(UserVo user) throws BusinessException {
        try {
            return userVoConverter.convertSourceToTarget(
                    userDao.saveOrUpdate(userVoConverter.convertTargetToSource(user, new User())),
                    new UserVo());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public UserVo save(UserVo user) throws BusinessException {
        try {
            return userVoConverter.convertSourceToTarget(
                    userDao.save(userVoConverter.convertTargetToSource(user, new User())),
                    new UserVo());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserVo user = new UserVo();
            user.setEmail(s);
            return getUserByEmail(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("UserService -> loadUserByUsername", e);
        }
    }
}
