package util;

import org.zeromq.EmbeddedLibraryTools;

import java.io.File;
import java.lang.reflect.Field;

public class LibraryUtils {

    /**
     * Dynamically sets the java.library.path property to point to the correct native library directory based on the
     * execution environment.
     */
    public static void setLibraryPath() {
        String libPath = System.getProperty("user.dir") + File.separator + "lib" + File.separator +
                "native" + File.separator + EmbeddedLibraryTools.getCurrentPlatformIdentifier();
        System.setProperty("java.library.path", libPath);

        // The JVM caches properties on startup, so this workaround forces java.library.path to be re-evaluated
        try {
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(System.getProperty("os.name").contains("Windows")) {
            System.loadLibrary("libzmq");
        }
    }

}
