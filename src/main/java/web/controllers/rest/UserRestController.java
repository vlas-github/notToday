package web.controllers.rest;

import beans.User;
import beans.UserAuthority;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.UserService;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;
import web.validators.UserVoValidator;

import java.util.*;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Controller
@RequestMapping(value = "/api/", produces="application/json; charset=utf-8")
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserVoValidator userVoValidator;
    @Autowired
    @Qualifier("passwordEncoder")
    private StandardPasswordEncoder passwordEncoder;

    @RequestMapping(value = "user/{id}.json", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") String id) {
        AjaxResponse response = new AjaxResponse();
        try {
            response.setData(userService.getUserById(id));
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "user.json", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody User user) {
        AjaxResponse response = new AjaxResponse();
        try {
            userVoValidator.validate(user);
            if (userVoValidator.hasErrors()) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setData(userVoValidator.getErrors());
                return response;
            }
            Set<UserAuthority> authorities = new HashSet<>();
            UserAuthority authority = new UserAuthority();
            authority.setValue("ROLE_USER");
            authorities.add(authority);
            user.setIsAccountNonLocked(true);
            user.setLocality("en");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRegistrationDate(new Date());

            response.setData(userService.save(user));
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "user/{id}.json", method = RequestMethod.PUT)
    @ResponseBody
    public Object update(@PathVariable("id") String id, @RequestBody User user) {
        AjaxResponse response = new AjaxResponse();
        try {
            User persisted = userService.getUserById(id);
            if (StringUtils.isNotEmpty(user.getName())) {
                persisted.setName(user.getName());
            }
            if (StringUtils.isNotEmpty(user.getLocality())) {
                persisted.setLocality(user.getLocality());
            }
            userVoValidator.validate(persisted);
            if (userVoValidator.hasErrors()) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Validate error");
                return response;
            }
            response.setData(userService.update(user));
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }
}
