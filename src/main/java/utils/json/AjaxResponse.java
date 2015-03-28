package utils.json;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.LinkedList;
import java.util.List;

public class AjaxResponse {

    @JsonSerialize(using = AjaxResponseStatusSerializer.class)
    private AjaxResponseStatus status;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private Object data;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String message;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
    private List<String> messages = new LinkedList<String>();

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private Boolean externalServicesErrors;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String m) {
        messages.add(m);
    }

    public Boolean getExternalServicesErrors() {
        return externalServicesErrors;
    }

    public void setExternalServicesErrors(Boolean externalServicesErrors) {
        this.externalServicesErrors = externalServicesErrors;
    }

    public AjaxResponseStatus getStatus() {
        return status;
    }

    public void setStatus(AjaxResponseStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
