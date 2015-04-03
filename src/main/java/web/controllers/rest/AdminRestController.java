package web.controllers.rest;

import beans.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.UserService;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Controller
@RequestMapping(value = "/api/", produces="application/json; charset=utf-8")
public class AdminRestController {

    @Autowired
    private UserService userService;

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
