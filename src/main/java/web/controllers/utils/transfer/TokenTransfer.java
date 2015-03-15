package web.controllers.utils.transfer;

/**
 * Created by vlasov-id-131216 on 14.03.15.
 */
public class TokenTransfer
{
    private final String token;

    public TokenTransfer(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return this.token;
    }

}