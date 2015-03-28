package beans;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
@Entity
@Table(name = "Users")
public class User  implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid", strategy=GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String passHash;

    @Column(name = "locality")
    private String locality;

    @Column(name = "registrationDate")
    private Date registrationDate;

    @Column(name = "isAccountNonLocked")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isAccountNonLocked;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<UserAuthority> authorities;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (fio != null ? !fio.equals(user.fio) : user.fio != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (isAccountNonLocked != null ? !isAccountNonLocked.equals(user.isAccountNonLocked) : user.isAccountNonLocked != null) return false;
        if (locality != null ? !locality.equals(user.locality) : user.locality != null) return false;
        if (passHash != null ? !passHash.equals(user.passHash) : user.passHash != null) return false;
        if (registrationDate != null ? !registrationDate.equals(user.registrationDate) : user.registrationDate != null) return false;
        if (tasks != null ? !tasks.equals(user.tasks) : user.tasks != null) return false;
        if (authorities != null ? !authorities.equals(user.authorities) : user.authorities != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passHash != null ? passHash.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (isAccountNonLocked != null ? isAccountNonLocked.hashCode() : 0);
        result = 31 * result + (authorities != null ? authorities.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public Set<UserAuthority> getUserAuthorities() {
        return authorities;
    }

    public void setUserAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *
     * implements UserDetails methods
     *
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passHash;
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

    public User clone() {
        User user = new User();
        user.setId(getId());
        user.setEmail(getEmail());
        user.setFio(getFio());
        user.setIsAccountNonLocked(getIsAccountNonLocked());
        user.setLocality(getLocality());
        user.setPassHash(getPassHash());
        user.setRegistrationDate(getRegistrationDate());
        user.setUserAuthorities(getUserAuthorities() != null ? getUserAuthorities() : null);
        if (user.getTasks() != null) {
            user.setTasks(getTasks().stream().map((t) -> t != null ? t.clone() : null).collect(Collectors.toList()));
        }
        return user;
    }
}
