package utils.converter.vo;

import beans.Repeat;
import beans.Task;
import beans.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vo.RepeatVo;
import vo.TaskVo;
import vo.UserVo;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Component
public class TaskVoConverter implements Converter<Task, TaskVo> {

    @Autowired
    private UserVoConverter userVoConverter;
    @Autowired RepeatVoConverter repeatVoConverter;


    @Override
    public TaskVo convertSourceToTarget(Task from, TaskVo to) {
        to.setId(from.getId());
        to.setUser(userVoConverter.convertSourceToTarget(from.getUser(), new UserVo()));
        to.setCompleted(from.isCompleted());
        to.setDateIsSet(from.getDateIsSet());
        to.setDeleted(from.isDeleted());
        to.setDescription(from.getDescription());
        to.setExecutionDate(from.getExecutionDate());
        if (from.getRepeat() != null) {
            to.setRepeat(repeatVoConverter.convertSourceToTarget(from.getRepeat(), new RepeatVo()));
        }
        to.setTitle(from.getTitle());
        to.setId(from.getId());
        to.setTimeIsSet(from.getTimeIsSet());
        return to;
    }

    @Override
    public Task convertTargetToSource(TaskVo from, Task to) {
        if (from.getUser() != null) {
            to.setUser(userVoConverter.convertTargetToSource(from.getUser(), new User()));
        }
        to.setCompleted(from.isCompleted());
        to.setDateIsSet(from.getDateIsSet());
        to.setDeleted(from.isDeleted());
        to.setDescription(from.getDescription());
        to.setExecutionDate(from.getExecutionDate());
        if (from.getRepeat() != null) {
            to.setRepeat(repeatVoConverter.convertTargetToSource(from.getRepeat(), new Repeat()));
        }
        to.setTitle(from.getTitle());
        to.setId(from.getId());
        to.setTimeIsSet(from.getTimeIsSet());
        return to;
    }

    @Override
    public Class<Task> getSource() {
        return Task.class;
    }

    @Override
    public Class<TaskVo> getTarget() {
        return TaskVo.class;
    }
}
