package web.controllers.vo;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
public class UserAuthorityVo {
    private String id;
    private String value;
    private UserVo user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
