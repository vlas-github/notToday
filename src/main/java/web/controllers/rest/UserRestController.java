package web.controllers.rest;

import beans.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.UserService;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;
import web.validators.UserVoValidator;

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
            if (StringUtils.isNotEmpty(user.getFio())) {
                persisted.setFio(user.getFio());
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
            response.setData(userService.saveOrUpdate(user));
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;

    }
}
