package hu.icellmobilsoft.onboarding.java.sample.coffee;

import java.io.InputStream;

import jakarta.enterprise.inject.Alternative;

import jakarta.enterprise.inject.Model;
import org.w3c.dom.DOMImplementationSource;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;

@Model
public class XsdResourceResolver implements IXsdResourceResolver {
    private String xsdDirPath;

    public XsdResourceResolver() {
    }

    public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
        if (StringUtils.isBlank(systemId)) {
            return null;
        } else {
            DOMImplementationLS domImplLS = this.getDOMImplementationLS();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            LSInput input = domImplLS.createLSInput();
            InputStream resStream = null;
            String temp;
            String path;
            if (resStream == null) {
                temp = StringUtils.substringBeforeLast(this.xsdDirPath, "/");
                path = systemId;
                if (StringUtils.contains(systemId, "..")) {
                    temp = StringUtils.substringBeforeLast(temp, "/");
                    path = StringUtils.substringAfter(systemId, "..");
                } else {
                    temp = temp + "/";
                }

                path = temp + path;
                resStream = classLoader.getResourceAsStream(path);
            }

            if (resStream == null) {
                return null;
            } else {
                input.setByteStream(resStream);
                input.setSystemId(systemId);
                return input;
            }
        }
    }

    private DOMImplementationLS getDOMImplementationLS() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            return (DOMImplementationLS) registry.getDOMImplementation("LS");
        } catch (ClassNotFoundException var6) {
            try {
                Class<?> sysImpl = classLoader.loadClass("com.sun.org.apache.xerces.internal.dom.DOMXSImplementationSourceImpl");
                DOMImplementationSource source = (DOMImplementationSource) sysImpl.newInstance();
                return (DOMImplementationLS) source.getDOMImplementation("LS");
            } catch (Exception var5) {
                throw new RuntimeException(var5);
            }
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public void setXsdDirPath(String xsdDirPath) {
        this.xsdDirPath = xsdDirPath;
    }
}
