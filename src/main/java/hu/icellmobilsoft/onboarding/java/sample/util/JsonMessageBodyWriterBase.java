package hu.icellmobilsoft.onboarding.java.sample.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.BaseResponseType;

public abstract class JsonMessageBodyWriterBase<T> implements MessageBodyWriter<T> {

    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return BaseResponseType.class.isAssignableFrom(type) && mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        entityStream.write(serialize(t).getBytes(StandardCharsets.UTF_8));
    }

    public static String serialize(Object object) throws IOException {
        Gson gson = new GsonBuilder().create();
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            throw new IOException("Failed to serialize object to JSON", e);
        }
    }
}
