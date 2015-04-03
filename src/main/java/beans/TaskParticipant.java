package beans;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Entity
@Table(name="TaskParticipant")
@AttributeOverrides({
        @AttributeOverride(name="id", column=@Column(name="_id"))})
public final class TaskParticipant extends BaseEntity {

    @Column(name="_task_guid")
    private String taskGuid;

    @Column(name="_user_id")
    private String userId;

    @Column(name="_notify")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean notify;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskParticipant that = (TaskParticipant) o;

        if (notify != null ? !notify.equals(that.notify) : that.notify != null) return false;
        if (taskGuid != null ? !taskGuid.equals(that.taskGuid) : that.taskGuid != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskGuid != null ? taskGuid.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (notify != null ? notify.hashCode() : 0);
        return result;
    }

    public String getTaskGuid() {
        return taskGuid;
    }

    public void setTaskGuid(String taskGuid) {
        this.taskGuid = taskGuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }
}
