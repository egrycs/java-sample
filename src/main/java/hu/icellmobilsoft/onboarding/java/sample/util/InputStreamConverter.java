package hu.icellmobilsoft.onboarding.java.sample.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class InputStreamConverter {

    public static byte[] convert(InputStream inputStream) throws Exception {
        // Lementjük az eredeti InputStream tartalmát egy ByteArrayOutputStream-be
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        // A byte tömb lekérése az OutputStream-ből
        return byteArrayOutputStream.toByteArray();
    }
}
