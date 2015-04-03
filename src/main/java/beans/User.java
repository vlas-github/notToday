package beans;

import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
@Entity
@Table(name="User")
@AttributeOverrides({
        @AttributeOverride(name="id", column=@Column(name="_id"))})
public final class User extends BaseEntity implements UserDetails {

    @Column(name="_name")
    private String name;

    @Column(name="_email")
    private String email;

    @Column(name="_login")
    private String login;

    @Column(name="_password")
    private String password;

    @Column(name="_locality")
    private String locality;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "_user_id")
    private Set<UserAuthority> authorities;

    @Column(name="_is_account_non_locked")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean isAccountNonLocked;

    @Column(name="_registration_date")
    private Date registrationDate;

    @Column(name="_last_activity_date")
    private Date lastActivityDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (authorities != null ? !authorities.equals(user.authorities) : user.authorities != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (isAccountNonLocked != null ? !isAccountNonLocked.equals(user.isAccountNonLocked) : user.isAccountNonLocked != null)
            return false;
        if (lastActivityDate != null ? !lastActivityDate.equals(user.lastActivityDate) : user.lastActivityDate != null)
            return false;
        if (locality != null ? !locality.equals(user.locality) : user.locality != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (registrationDate != null ? !registrationDate.equals(user.registrationDate) : user.registrationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (authorities != null ? authorities.hashCode() : 0);
        result = 31 * result + (isAccountNonLocked != null ? isAccountNonLocked.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (lastActivityDate != null ? lastActivityDate.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
