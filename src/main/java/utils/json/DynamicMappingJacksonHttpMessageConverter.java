package utils.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

public abstract class DynamicMappingJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private boolean prefixJson = false;
    private Boolean prettyPrint = false;

    // Spring добавит сюда реализацию получения маппера для текущего запроса
    @Override
    public abstract ObjectMapper getObjectMapper();

    @Override
    public void setObjectMapper(ObjectMapper objectMapper) {
    }

    public boolean isPrettyPrint() {
        return prettyPrint;
    }

    @Override
    public void setPrettyPrint(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    @Override
    public void setPrefixJson(boolean prefixJson) {
        this.prefixJson = prefixJson;
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        JavaType javaType = getJavaType(type, contextClass);
        return (getObjectMapper().canDeserialize(javaType) && canRead(mediaType));
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return (getObjectMapper().canSerialize(clazz) && canWrite(mediaType));
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        JavaType javaType = getJavaType(clazz, null);
        return readJavaType(javaType, inputMessage);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        JavaType javaType = getJavaType(type, contextClass);
        return readJavaType(javaType, inputMessage);
    }

    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
        try {
            return getObjectMapper().readValue(inputMessage.getBody(), javaType);
        } catch (IOException ex) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
        }
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
        JsonGenerator jsonGenerator = getObjectMapper().getFactory().createGenerator(outputMessage.getBody(), encoding);

        if (isPrettyPrint()) {
            jsonGenerator.useDefaultPrettyPrinter();
        }

        try {
            if (this.prefixJson) {
                jsonGenerator.writeRaw("{} && ");
            }
            getObjectMapper().writeValue(jsonGenerator, object);
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    @Override
    protected JavaType getJavaType(Type type, Class<?> contextClass) {
        return (contextClass != null) ?
                getObjectMapper().getTypeFactory().constructType(type, contextClass) :
                getObjectMapper().constructType(type);
    }

}
