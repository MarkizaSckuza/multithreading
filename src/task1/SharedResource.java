package task1;

/**
 * Created by Margo on 19.09.2015.
 */
public class SharedResource {

    private static Object lock = new Object();
    private static String resource;
    private static Boolean isWritten = false;
    private static Boolean isSorted = false;

    public static String getResource() {
        return resource;
    }

    public static synchronized void setResource(String resource) {
        SharedResource.resource = resource;
    }

    public static Boolean getIsWritten() {
        return isWritten;
    }

    public static synchronized void setIsWritten(Boolean isWritten) {
        SharedResource.isWritten = isWritten;
    }

    public static Boolean getIsSorted() {
        return isSorted;
    }

    public static synchronized void setIsSorted(Boolean isSorted) {
        SharedResource.isSorted = isSorted;
    }

    public static Object getLock() {
        return lock;
    }
}
