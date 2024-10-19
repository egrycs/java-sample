package hu.icellmobilsoft.onboarding.java.sample.util;

public class FileNameSplitter {

    public static String[] split(String fullFileName, String separator) {
        return split(fullFileName, separator, true);
    }

    public static String[] split(String fullFileName, String separator, boolean isSeparatorNeeded) {
        // Keressük meg az utolsó pont helyét a fájlnéven belül
        int dotIndex = fullFileName.lastIndexOf(separator);

        // Ha van pont a fájlnévben, akkor szétbontjuk prefix-re és suffix-re
        if (dotIndex > 0 && dotIndex < fullFileName.length() - 1) {
            String prefix = fullFileName.substring(0, dotIndex);
            String suffix = fullFileName.substring(dotIndex + (isSeparatorNeeded ? 0 : 1));
            return new String[] { prefix, suffix };
        } else {
            // Ha nincs pont vagy helytelen formátumú, visszaadjuk az egész fájlnevet suffix nélkül
            return new String[] { fullFileName, "" };
        }
    }
}
