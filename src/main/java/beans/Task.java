package beans;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
@Entity
@Table(name="Task")
@AttributeOverrides({
        @AttributeOverride(name="id",           column=@Column(name="_id")),
        @AttributeOverride(name="guid",         column=@Column(name="_guid")),
        @AttributeOverride(name="next",         column=@Column(name="_next")),
        @AttributeOverride(name="previous",     column=@Column(name="_previous")),
        @AttributeOverride(name="changeDate",   column=@Column(name="_change_date"))})
public final class Task extends BaseVersionedEntity {

    @Column(name="_text")
    private String text;

    @Column(name="_description")
    private String description;

    @Column(name="_completed")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean completed;

    @Column(name="_deleted")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean deleted;

    @Column(name="_execution_date")
    private Date executionDate;

    @Column(name="_date_is_set")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean dateIsSet;

    @Column(name="_time_is_set")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean timeIsSet;

    @Column(name="_repeat")
    private String repeat;

    @Column(name="_parent_task")
    private String parentTaskGuid;

    @Column(name="_have_subtasks")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean haveSubtasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Task task = (Task) o;

        if (completed != null ? !completed.equals(task.completed) : task.completed != null) return false;
        if (dateIsSet != null ? !dateIsSet.equals(task.dateIsSet) : task.dateIsSet != null) return false;
        if (deleted != null ? !deleted.equals(task.deleted) : task.deleted != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (executionDate != null ? !executionDate.equals(task.executionDate) : task.executionDate != null)
            return false;
        if (haveSubtasks != null ? !haveSubtasks.equals(task.haveSubtasks) : task.haveSubtasks != null) return false;
        if (parentTaskGuid != null ? !parentTaskGuid.equals(task.parentTaskGuid) : task.parentTaskGuid != null) return false;
        if (repeat != null ? !repeat.equals(task.repeat) : task.repeat != null) return false;
        if (text != null ? !text.equals(task.text) : task.text != null) return false;
        if (timeIsSet != null ? !timeIsSet.equals(task.timeIsSet) : task.timeIsSet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (executionDate != null ? executionDate.hashCode() : 0);
        result = 31 * result + (dateIsSet != null ? dateIsSet.hashCode() : 0);
        result = 31 * result + (timeIsSet != null ? timeIsSet.hashCode() : 0);
        result = 31 * result + (repeat != null ? repeat.hashCode() : 0);
        result = 31 * result + (parentTaskGuid != null ? parentTaskGuid.hashCode() : 0);
        result = 31 * result + (haveSubtasks != null ? haveSubtasks.hashCode() : 0);
        return result;
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

    public String getParentTaskGuid() {
        return parentTaskGuid;
    }

    public void setParentTaskGuid(String parentTaskGuid) {
        this.parentTaskGuid = parentTaskGuid;
    }

    public Boolean getHaveSubtasks() {
        return haveSubtasks;
    }

    public void setHaveSubtasks(Boolean haveSubtasks) {
        this.haveSubtasks = haveSubtasks;
    }
}
