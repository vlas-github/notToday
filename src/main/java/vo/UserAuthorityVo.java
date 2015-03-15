package vo;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
public class UserAuthorityVo implements GrantedAuthority {

    private String id;
    private String value;

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

    @Override
    public String getAuthority() {
        return value;
    }
}
