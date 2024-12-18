package hu.icellmobilsoft.onboarding.java.sample.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseProcessingExceptionWrapper;
import hu.icellmobilsoft.onboarding.java.sample.validation.ValidateXML;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import hu.icellmobilsoft.onboarding.java.sample.coffee.JaxbTool;

public abstract class JsonMessageBodyReaderBase<T> implements MessageBodyReader<T> {

    @Inject
    private JaxbTool jaxbTool;

    public JsonMessageBodyReaderBase() {
    }

    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return getValidateIfPresent(annotations) != null;
    }

    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) {
        try {
            ValidateXML validateXML = getValidateIfPresent(annotations);
            T obj = this.deserializeJson(type, entityStream);
            String schemaPath = this.jaxbTool.getXsdPath(validateXML);

            this.jaxbTool.marshalXML(obj, schemaPath);
            return obj;
        } catch (BaseException e) {
            throw new BaseProcessingExceptionWrapper(e);
        }
    }

    public static ValidateXML getValidateIfPresent(Annotation[] annotations) {
        ValidateXML validateXML = null;
        if (annotations != null) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof ValidateXML) {
                    validateXML = (ValidateXML) annotation;
                }
            }
        }

        return validateXML;
    }

    public T deserializeJson(Class<T> type, InputStream entityStream) throws BaseException {
        try {
            return toObjectGson(new InputStreamReader(entityStream, StandardCharsets.UTF_8), type);
        } catch (Exception e) {
            throw new BaseException(e.getMessage(), e);
        }
    }

    public static <T> T toObjectGson(Reader reader, Class<T> classType) {
        Gson gson = new GsonBuilder().create();

        try {
            return gson.fromJson(reader, classType);
        } catch (JsonSyntaxException e) {
            JsonReader jsonreader = new JsonReader(reader);
            jsonreader.setLenient(true);
            return gson.fromJson(jsonreader, classType);
        }
    }
}
