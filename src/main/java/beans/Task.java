package beans;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid", strategy=GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "executionDate")
    private Date executionDate;

    @Column(name = "dateIsSet")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean dateIsSet;

    @Column(name = "timeIsSet")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean timeIsSet;

    @ManyToOne(targetEntity = Repeat.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "repeat_id", referencedColumnName="id")
    private Repeat repeat;

    @Column(name = "completed")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean completed;

    @Column(name = "deleted")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean deleted;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (completed != null ? !completed.equals(task.completed) : task.completed != null) return false;
        if (dateIsSet != null ? !dateIsSet.equals(task.dateIsSet) : task.dateIsSet != null) return false;
        if (deleted != null ? !deleted.equals(task.deleted) : task.deleted != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (executionDate != null ? !executionDate.equals(task.executionDate) : task.executionDate != null) return false;
        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (repeat != null ? !repeat.equals(task.repeat) : task.repeat != null) return false;
        if (timeIsSet != null ? !timeIsSet.equals(task.timeIsSet) : task.timeIsSet != null) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (user != null ? !user.equals(task.user) : task.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (executionDate != null ? executionDate.hashCode() : 0);
        result = 31 * result + (dateIsSet != null ? dateIsSet.hashCode() : 0);
        result = 31 * result + (timeIsSet != null ? timeIsSet.hashCode() : 0);
        result = 31 * result + (repeat != null ? repeat.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

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

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task clone() {
        Task task = new Task();
        task.setId(getId());
        task.setDeleted(isDeleted());
        task.setCompleted(isCompleted());
        task.setDateIsSet(getDateIsSet());
        task.setDescription(getDescription());
        task.setTimeIsSet(getTimeIsSet());
        task.setRepeat(getRepeat() != null ? getRepeat().clone() : null);
        task.setExecutionDate(getExecutionDate());
        task.setTitle(getTitle());
        task.setUser(null);
        return task;
    }
}
