package web.controllers.utils.converter;

import beans.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import services.TaskService;
import utils.exception.BusinessException;
import web.controllers.vo.TaskVo;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Component
public class TaskVoConverterProvider implements ConverterProvider<Task, TaskVo> {

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Autowired
    private TaskService taskService;

    @Override
    public TaskVo convertSourceToTarget(Task from, TaskVo to) {
        if (to == null) {
            to = new TaskVo();
        }
        to.setId(from.getId());
        to.setGuid(from.getGuid());
        to.setActive(from.isActive());
        to.setNext(from.getNext());
        to.setPrevious(from.getPrevious());
        to.setChangeDate(from.getChangeDate());
        to.setText(from.getText());
        to.setDescription(from.getDescription());
        to.setCompleted(from.isCompleted());
        to.setDeleted(from.isDeleted());
        to.setExecutionDate(from.getExecutionDate());
        to.setDateIsSet(from.getDateIsSet());
        to.setTimeIsSet(from.getTimeIsSet());
        to.setRepeat(from.getRepeat());
        to.setParentTask(null);
        if (StringUtils.isNotEmpty(from.getParentTaskGuid())) {
            try {
                Task parent = taskService.get(from.getParentTaskGuid());
                parent.setParentTaskGuid(null);
                to.setParentTask(converter.convert(parent, TaskVo.class));
            } catch (BusinessException e) { }
        }
        to.setHaveSubtasks(from.getHaveSubtasks());
        return to;
    }

    @Override
    public Task convertTargetToSource(TaskVo from, Task to) {
        if (to == null) {
            to = new Task();
        }
        to.setId(from.getId());
        to.setGuid(from.getGuid());
        to.setActive(from.isActive());
        to.setNext(from.getNext());
        to.setPrevious(from.getPrevious());
        to.setChangeDate(from.getChangeDate());
        to.setText(from.getText());
        to.setDescription(from.getDescription());
        to.setCompleted(from.isCompleted());
        to.setDeleted(from.isDeleted());
        to.setExecutionDate(from.getExecutionDate());
        to.setDateIsSet(from.getDateIsSet());
        to.setTimeIsSet(from.getTimeIsSet());
        to.setRepeat(from.getRepeat());
        to.setParentTaskGuid(null);
        if (from.getParentTask() != null && StringUtils.isNotEmpty(from.getParentTask().getGuid())) {
            to.setParentTaskGuid(from.getParentTask().getGuid());
        }
        to.setHaveSubtasks(from.getHaveSubtasks());
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
