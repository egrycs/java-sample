package hu.icellmobilsoft.onboarding.java.sample.coffee;

import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.SAXException;

import javax.xml.validation.Schema;

public interface IXsdHelper {
    JAXBContext getJAXBContext(Class<?> var1) throws JAXBException, BaseException;

    Schema getSchema(String var1, LSResourceResolver var2) throws BaseException, SAXException;
}
