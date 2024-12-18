package hu.icellmobilsoft.onboarding.java.sample.service;

import java.io.InputStream;

public interface ILoadData {
    void loadFromXml(InputStream xmlStream, String schemaFileName);

    void loadFromJson(InputStream jsonStream, String schemaFileName);
}
