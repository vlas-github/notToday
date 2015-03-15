package utils.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * Created by vlasov-id-131216 on 23.02.15.
 */
public class AjaxResponseStatusSerializer extends
        JsonSerializer<AjaxResponseStatus> {

    @Override
    public void serialize(AjaxResponseStatus value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {

        jgen.writeNumber(value.code());

    }
}