package services.impl;

import beans.User;
import dao.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.UserService;
import utils.exception.BusinessException;
import web.controllers.utils.converter.Converter;
import web.controllers.vo.UserVo;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Override
    @Transactional
    public UserVo getUserByEmail(UserVo user) throws BusinessException {
        try {
            if (StringUtils.isEmpty(user.getEmail())) {
                throw new BusinessException("Validate email - is null!");
            }
            user = converter.convert(userDao.getByEmail(user.getEmail()));
            return user;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public UserVo getUserById(String id) throws BusinessException {
        try {
            User user = userDao.getById(id);
            if (user == null) {
                throw new BusinessException("User not found");
            }
            return converter.convert(user);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public UserVo update(UserVo user) throws BusinessException {
        try {
            return converter.convert(userDao.update(converter.convert(user)));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public UserVo save(UserVo user) throws BusinessException {
        try {
            return converter.convert(userDao.save(converter.convert(user)));
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
            user = getUserByEmail(user);
            return user;
        } catch (Exception e) {
            throw new UsernameNotFoundException("UserService -> loadUserByUsername", e);
        }
    }
}
