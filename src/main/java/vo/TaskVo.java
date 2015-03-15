package vo;

import java.util.Date;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
public class TaskVo implements java.io.Serializable {

    private String id;
    private String title;
    private String description;
    private Date executionDate;
    private Boolean dateIsSet;
    private Boolean timeIsSet;
    private RepeatVo repeat;
    private Boolean completed;
    private Boolean deleted;
    private UserVo user;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public RepeatVo getRepeat() {
        return repeat;
    }

    public void setRepeat(RepeatVo repeat) {
        this.repeat = repeat;
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

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
