package hu.icellmobilsoft.onboarding.java.sample.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ByteArrayToFileConverter {

    public static File convert(byte[] byteArray, String fileName) throws Exception {
        String[] prefixAndSuffix = FileNameSplitter.split(fileName, ".");
        String[] prefixes = FileNameSplitter.split(prefixAndSuffix[0], "/", false);
        List<String> filteredPrefixesList = Arrays.stream(prefixes).filter(s -> !s.isEmpty()).toList();
        String[] filteredPrefixes = filteredPrefixesList.toArray(new String[0]);
        String prefix = filteredPrefixes[filteredPrefixes.length - 1];

        Path tempPath = Files.createTempFile(prefix, prefixAndSuffix[1]);
        Files.write(tempPath, byteArray); // Írjuk bele a byte[] tartalmát a fájlba
        File tempFile = tempPath.toFile();
        tempFile.deleteOnExit(); // Az ideiglenes fájl automatikusan törlődik a program befejeztével

        return tempFile;
    }
}