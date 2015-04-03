package beans;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.util.Date;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Entity
public abstract class BaseVersionedEntity extends BaseEntity {

    @Column(name="_guid")
    private String guid;

    @Column(name="_active")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean active;

    @Column(name="_next")
    private String next;

    @Column(name="_previous")
    private String previous;

    @Column(name="_change_date")
    private Date changeDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BaseVersionedEntity that = (BaseVersionedEntity) o;

        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (changeDate != null ? !changeDate.equals(that.changeDate) : that.changeDate != null) return false;
        if (guid != null ? !guid.equals(that.guid) : that.guid != null) return false;
        if (next != null ? !next.equals(that.next) : that.next != null) return false;
        if (previous != null ? !previous.equals(that.previous) : that.previous != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (guid != null ? guid.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        result = 31 * result + (previous != null ? previous.hashCode() : 0);
        result = 31 * result + (changeDate != null ? changeDate.hashCode() : 0);
        return result;
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
}
