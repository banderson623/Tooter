package util;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;

public class LibraryUtils {

    public static boolean loadEmbeddedLibrary() {

        boolean usingEmbedded = false;

        // attempt to locate embedded native library within JAR at following location:
        // /NATIVE/${os.arch}/${os.name}/libzmq.[so|dylib|dll]
        String[] allowedExtensions = new String[] { "so", "dylib", "dll" };
        StringBuilder url = new StringBuilder();
        url.append("/NATIVE/");
        url.append(getCurrentPlatformIdentifier());
        url.append("/libzmq.");
        URL nativeLibraryUrl = null;
        // loop through extensions, stopping after finding first one
        for (String ext : allowedExtensions) {
            nativeLibraryUrl = LibraryUtils.class.getResource(url.toString() + ext);
            if (nativeLibraryUrl != null)
                break;
        }

        if (nativeLibraryUrl != null) {

            // native library found within JAR, extract and load

            try {

                final File libfile = File.createTempFile("libzmq-", ".lib");
                libfile.deleteOnExit(); // just in case

                final InputStream in = nativeLibraryUrl.openStream();
                final OutputStream out = new BufferedOutputStream(new FileOutputStream(libfile));

                int len = 0;
                byte[] buffer = new byte[8192];
                while ((len = in.read(buffer)) > -1)
                    out.write(buffer, 0, len);
                out.close();
                in.close();

                System.load(libfile.getAbsolutePath());

                libfile.delete();

                usingEmbedded = true;

            } catch (IOException x) {
                // mission failed, do nothing
            }

        } // nativeLibraryUrl exists

        return usingEmbedded;

    }

    /**
     * Indicates if the program is running from a JAR file or not.
     */
    public static boolean isRunningFromJar() {
        CodeSource cs = LibraryUtils.class.getProtectionDomain().getCodeSource();
        try {
            return cs.getLocation().toURI().getPath().endsWith(".jar");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getCurrentPlatformIdentifier() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            osName = "Windows";
        }

        return System.getProperty("os.arch") + "/" + osName;
    }

}
