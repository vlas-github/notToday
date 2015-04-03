package web.controllers.rest;

import beans.Task;
import beans.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.TaskService;
import services.UserService;
import utils.exception.BusinessException;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;
import web.controllers.utils.converter.Converter;
import web.controllers.vo.TaskVo;
import web.validators.TaskVoValidator;

import java.security.Principal;
import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Controller
@RequestMapping(value = "/api/", produces="application/json; charset=utf-8")
public class TaskRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskVoValidator taskVoValidator;

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @RequestMapping(value = "user/{user}/task/{id}.json", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("user") String userId,
                      @PathVariable("id") String id) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (!verificationOfUserRights(userId)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("403");
                return response;
            }
            Task task = taskService.getById(id);
            if (task == null) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Task not found");
                return response;
            }
            response.setStatus(AjaxResponseStatus.OK);
            response.setData(task);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "user/{user}/task/list.json", method = RequestMethod.GET)
    @ResponseBody
    public Object getAll(@PathVariable("user") String userId) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (!verificationOfUserRights(userId)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("403");
                return response;
            }
            User user = userService.getUserById(userId);
            List<Task> tasks = taskService.getByUser(user);
            response.setStatus(AjaxResponseStatus.OK);
            response.setData(tasks);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "user/{user}/task.json", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@PathVariable("user") String userId,
                      @RequestBody TaskVo task) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (!verificationOfUserRights(userId)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("403");
                return response;
            }
            taskVoValidator.validate(task);
            if (taskVoValidator.hasErrors()) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Task validation error");
                return response;
            }
            User user = userService.getUserById(userId);
            task = converter.convert(taskService.add(converter.convert(task), user));
            response.setStatus(AjaxResponseStatus.OK);
            response.setData(task);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "user/{user}/task/{id}.json", method = RequestMethod.PUT)
    @ResponseBody
    public Object update(@PathVariable("user") String userId, @RequestBody Task task) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (!verificationOfUserRights(userId)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("403");
                return response;
            }
            if (StringUtils.isEmpty(task.getId())) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Task id is empty");
                return response;
            }
            Task persisted = taskService.getById(task.getId());
            if (StringUtils.isNotEmpty(task.getText())) {
                persisted.setText(task.getText());
            }
            if (StringUtils.isNotEmpty(task.getDescription())) {
                persisted.setDescription(task.getDescription());
            }
            if (task.getExecutionDate() != null) {
                persisted.setExecutionDate(task.getExecutionDate());
            }
            if (task.getDateIsSet() != null) {
                persisted.setDateIsSet(task.getDateIsSet());
            }
            if (task.getTimeIsSet() != null) {
                persisted.setTimeIsSet(task.getTimeIsSet());
            }
            if (task.getRepeat() != null) {
                persisted.setRepeat(task.getRepeat());
            }
            if (task.isCompleted() != null) {
                persisted.setCompleted(task.isCompleted());
            }
            if (task.isDeleted() != null) {
                persisted.setDeleted(task.isDeleted());
            }
            taskVoValidator.validate(converter.convert(persisted, TaskVo.class));
            if (taskVoValidator.hasErrors()) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Validation error");
                return response;
            }
            taskService.update(persisted);
            response.setStatus(AjaxResponseStatus.OK);
            response.setData(persisted);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "user/{user}/task/{id}.json", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("user") String userId,
                         @PathVariable("id") String id) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (!verificationOfUserRights(userId)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("403");
                return response;
            }
            Task task = taskService.getById(id);
            if (task == null) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Task not found");
                return response;
            }
            task.setDeleted(true);
            taskService.update(task);
            response.setStatus(AjaxResponseStatus.OK);
            response.setData(task);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    private boolean verificationOfUserRights(String userId) throws BusinessException {
        if (StringUtils.isEmpty(userId)) {
            return false;
        }
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserById(userId);
        if (!user.getEmail().equals(principal.getName())) {
            return false;
        }
        return true;
    }
}
