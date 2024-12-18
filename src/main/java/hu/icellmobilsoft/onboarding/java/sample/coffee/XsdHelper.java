package hu.icellmobilsoft.onboarding.java.sample.coffee;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import jakarta.enterprise.context.Dependent;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.SAXException;

import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;

@Dependent
public class XsdHelper implements IXsdHelper {
    private static final Map<String, Schema> xsdCache = new ConcurrentHashMap();
    private static final Map<String, JAXBContext> jaxbContextCache = new ConcurrentHashMap();

    public XsdHelper() {
    }

    public JAXBContext getJAXBContext(Class<?> forClass) throws JAXBException, BaseException {
        if (forClass == null) {
            throw new BaseException("forClass is null!");
        } else {
            String className = forClass.getName();
            if (jaxbContextCache.containsKey(className)) {
                return (JAXBContext) jaxbContextCache.get(className);
            } else {
                JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { forClass });
                jaxbContextCache.put(className, jaxbContext);
                return jaxbContext;
            }
        }
    }

    public Schema getSchema(String xsd, LSResourceResolver lsResourceResolver) throws BaseException, SAXException {
        if (!StringUtils.isBlank(xsd) && lsResourceResolver != null) {
            Schema schema = (Schema) xsdCache.get(xsd);
            if (schema == null) {
                InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(xsd);
                if (stream == null) {
                    throw new BaseException("cannot find schema to validate");
                }

                StreamSource src = new StreamSource(stream);
                SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

                try {
                    sf.setResourceResolver(lsResourceResolver);
                } catch (Exception e) {
                    throw new BaseException(e.getMessage(), e);
                }

                schema = sf.newSchema(src);
                xsdCache.put(xsd, schema);
            }

            return schema;
        } else {
            throw new BaseException("xsd is blank or lsResourceResolver is null!");
        }
    }

    public void clearXsdCache() {
        xsdCache.clear();
    }

    public void clearJaxbContextCache() {
        jaxbContextCache.clear();
    }
}
