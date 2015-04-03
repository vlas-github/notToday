package beans;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
@Entity
@Table(name="UserAuthority")
@AttributeOverrides({
        @AttributeOverride(name="id", column=@Column(name="_id"))})
public final class UserAuthority extends BaseEntity implements GrantedAuthority {

    @Column(name="_value")
    private String value;

    @Column(name="_user_id")
    private String userId;

    public UserAuthority() {
    }

    public UserAuthority(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserAuthority authority = (UserAuthority) o;

        if (userId != null ? !userId.equals(authority.userId) : authority.userId != null) return false;
        if (value != null ? !value.equals(authority.value) : authority.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getAuthority() {
        return value;
    }
}
