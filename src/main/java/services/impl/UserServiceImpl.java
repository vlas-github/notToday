package services.impl;

import beans.User;
import dao.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.UserService;
import utils.exception.BusinessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public User getUserByEmail(User user) throws BusinessException {
        try {
            if (StringUtils.isEmpty(user.getEmail())) {
                throw new BusinessException("Validate email - is null!");
            }
            User pattern = new User();
            pattern.setEmail(user.getEmail());
            List<User> users = userDao.getList(user);
            if (users.size() > 1) {
                throw new BusinessException("Two users in db with one email!");
            }
            if (users.size() == 0) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public User getUserById(String id) throws BusinessException {
        try {
            User user = userDao.getById(id);
            if (user == null) {
                throw new BusinessException("User not found");
            }
            return user;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public User update(User user) throws BusinessException {
        try {
            return userDao.update(user);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public User save(User user) throws BusinessException {
        try {
            return userDao.save(user);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = new User();
            user.setEmail(s);
            user = getUserByEmail(user);
            return user;
        } catch (Exception e) {
            throw new UsernameNotFoundException("UserService -> loadUserByUsername", e);
        }
    }
}
