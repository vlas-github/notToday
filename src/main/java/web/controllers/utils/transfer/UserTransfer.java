package web.controllers.utils.transfer;

import java.util.Map;

/**
 * Created by vlasov-id-131216 on 14.03.15.
 */
public class UserTransfer
{

    private final String name;

    private final Map<String, Boolean> roles;


    public UserTransfer(String userName, Map<String, Boolean> roles)
    {
        this.name = userName;
        this.roles = roles;
    }


    public String getName()
    {
        return this.name;
    }


    public Map<String, Boolean> getRoles()
    {
        return this.roles;
    }

}