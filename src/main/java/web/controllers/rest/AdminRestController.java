package web.controllers.rest;

import beans.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.UserService;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;
import web.controllers.utils.converter.Converter;
import web.controllers.vo.UserAuthorityVo;
import web.controllers.vo.UserVo;
import web.validators.UserVoValidator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Controller
@RequestMapping(value = "/api/", produces="application/json; charset=utf-8")
public class AdminRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserVoValidator userVoValidator;

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Autowired
    @Qualifier("passwordEncoder")
    private StandardPasswordEncoder passwordEncoder;

    @RequestMapping(value = "admin/blocker/{id}.json", method = RequestMethod.PUT)
    @ResponseBody
    public Object block(@PathVariable("id") String userId) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (StringUtils.isEmpty(userId)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("User id is empty");
                return response;
            }
            User user = userService.getUserById(userId);
            user.setIsAccountNonLocked(false);
            userService.update(user);
            response.setStatus(AjaxResponseStatus.OK);
            response.setData(user);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "user.json", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody UserVo user) {
        AjaxResponse response = new AjaxResponse();
        try {
            userVoValidator.validate(user);
            if (userVoValidator.hasErrors()) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setData(userVoValidator.getErrors());
                return response;
            }
            Set<UserAuthorityVo> authorities = new HashSet<>();
            UserAuthorityVo authority = new UserAuthorityVo();
            authority.setValue("ROLE_USER");
            authorities.add(authority);
            user.setAuthorities(authorities);
            user.setIsAccountNonLocked(true);
            user.setLocality("en");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setLastActivityDate(new Date());
            user.setRegistrationDate(new Date());
            user = converter.convert(userService.save(converter.convert(user)));
            response.setData(user);
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "user/{id}/password/{password}.json", method = RequestMethod.POST)
    @ResponseBody
    public Object changePassword(@PathVariable("id") String id, @PathVariable("password") String password) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (StringUtils.isEmpty(id)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("User id is empty");
                return response;
            }
            if (StringUtils.isEmpty(password)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Password id is empty");
                return response;
            }
            User user = userService.getUserById(id);
            user.setPassword(password);
            userService.update(user);
            response.setStatus(AjaxResponseStatus.OK);
            response.setData(user);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }
}
