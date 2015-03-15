package utils.json;

/**
 * Created by vlasov-id-131216 on 23.02.15.
 */
public enum AjaxResponseStatus {
    OK(0), ERROR(1);

    private Integer code;

    private AjaxResponseStatus(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
