package utils;

public class ExceptionUtils {

    private static String nullMessage = "The object is not instance, is a null pointer";
    private ExceptionUtils(){
        throw new UnsupportedOperationException("This is a utils class and cannot be instance");
    }

    public static String getNullMessage(){
        return nullMessage;
    }

    public static void setNullMessage(String newNullMessage){
        nullMessage = newNullMessage;
    }


    public static <T> String formatMessage(String message,Class<T> classException){
        return classException.getSimpleName() + "throw a Exception: " + message;
    }

    public static <T> String getNullMessage(Class<T> classException){
        return classException.getSimpleName() + ": " + nullMessage;
    }
}