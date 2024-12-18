package hu.icellmobilsoft.onboarding.java.sample.coffee;

import java.io.InputStream;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import hu.icellmobilsoft.onboarding.java.sample.exception.XsdProcessingException;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.xml.bind.*;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.SAXException;

import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.validation.ValidateXML;

@Model
public class JaxbTool {

    public JaxbTool() {
    }

    public <T> T unmarshalXML(Class<T> type, InputStream inputStream, String schemaPath) throws BaseException {
        if (type != null && inputStream != null) {
            IXsdValidationErrorCollector errorCollector = (IXsdValidationErrorCollector)createCDIInstance(IXsdValidationErrorCollector.class);

            try {
                IXsdHelper xsdHelper = (IXsdHelper)createCDIInstance(IXsdHelper.class);
                JAXBContext jaxbContext = xsdHelper.getJAXBContext(type);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                unmarshaller.setEventHandler(errorCollector);
                if (!StringUtils.isBlank(schemaPath)) {
                    unmarshaller.setSchema(xsdHelper.getSchema(schemaPath, this.createLSResourceResolverInstance(schemaPath)));
                }

                T result = (T) unmarshaller.unmarshal(inputStream);
                if (!errorCollector.getErrors().isEmpty()) {
                    throw new XsdProcessingException(errorCollector.getErrors(), (Throwable)null);
                } else {
                    return result;
                }
            } catch (UnmarshalException e) {
                throw new XsdProcessingException(errorCollector.getErrors(), e);
            } catch (SAXException | JAXBException e) {
                throw new XsdProcessingException(e.getLocalizedMessage(), e);
            }
        } else {
            throw new BaseException("type or inputStream is null!");
        }
    }

    public String marshalXML(Object obj, String schemaPath) throws BaseException {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jaxb.formatted.output", true);
        properties.put("jaxb.fragment", Boolean.TRUE);
        return this.marshalXML(obj, schemaPath, properties);
    }

    public String marshalXML(Object obj, String schemaPath, Map<String, Object> marshallerProperties) throws BaseException {
        if (obj == null) {
            throw new BaseException("obj is null!");
        } else {
            try {
                IXsdHelper xsdHelper = (IXsdHelper)createCDIInstance(IXsdHelper.class);
                JAXBContext jaxbContext = xsdHelper.getJAXBContext(obj.getClass());
                Marshaller marshaller = jaxbContext.createMarshaller();
                if (marshallerProperties != null) {
                    Iterator iterator = marshallerProperties.entrySet().iterator();

                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> entry = (Map.Entry) iterator.next();
                        marshaller.setProperty(entry.getKey(), entry.getValue());
                    }
                }

                IXsdValidationErrorCollector errorCollector = createCDIInstance(IXsdValidationErrorCollector.class);
                marshaller.setEventHandler(errorCollector);
                if (!StringUtils.isBlank(schemaPath)) {
                    marshaller.setSchema(xsdHelper.getSchema(schemaPath, this.createLSResourceResolverInstance(schemaPath)));
                }

                StringWriter stringWriter = new StringWriter();
                marshaller.marshal(obj, stringWriter);
                if (!errorCollector.getErrors().isEmpty()) {
                    throw new XsdProcessingException(errorCollector.getErrors(), (Throwable)null);
                } else {
                    return stringWriter.getBuffer().toString();
                }
            } catch (SAXException | JAXBException e) {
                throw new XsdProcessingException(e.getMessage(), e);
            }
        }
    }

    protected LSResourceResolver createLSResourceResolverInstance(String schemaPath) {
        IXsdResourceResolver resourceResolver = (IXsdResourceResolver) createCDIInstance(IXsdResourceResolver.class);
        resourceResolver.setXsdDirPath(schemaPath);
        return resourceResolver;
    }

    public String getXsdPath(ValidateXML validateXML) throws BaseException {
        if (validateXML == null) {
            throw new BaseException("validateXML is null!");
        } else {
            return validateXML.xsdPath();
        }
    }

    protected static <I> I createCDIInstance(Class<I> type) {
        CDI<Object> cdi = CDI.current();
        Instance<I> instance = cdi.select(type, new Annotation[0]);
        I result = instance.get();
        cdi.destroy(instance);

        return result;
    }
}
