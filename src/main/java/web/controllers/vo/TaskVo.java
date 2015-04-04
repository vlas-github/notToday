package web.controllers.vo;

import java.util.Date;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
public class TaskVo extends BaseVo {
    private String id;
    private String guid;
    private Boolean active;
    private String next;
    private String previous;
    private Date changeDate;
    private String text;
    private String description;
    private Boolean completed;
    private Boolean deleted;
    private Date executionDate;
    private Boolean dateIsSet;
    private Boolean timeIsSet;
    private String repeat;
    private TaskVo parentTask;
    private Boolean haveSubtasks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public Boolean getDateIsSet() {
        return dateIsSet;
    }

    public void setDateIsSet(Boolean dateIsSet) {
        this.dateIsSet = dateIsSet;
    }

    public Boolean getTimeIsSet() {
        return timeIsSet;
    }

    public void setTimeIsSet(Boolean timeIsSet) {
        this.timeIsSet = timeIsSet;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public TaskVo getParentTask() {
        return parentTask;
    }

    public void setParentTask(TaskVo parentTask) {
        this.parentTask = parentTask;
    }

    public Boolean getHaveSubtasks() {
        return haveSubtasks;
    }

    public void setHaveSubtasks(Boolean haveSubtasks) {
        this.haveSubtasks = haveSubtasks;
    }
}
