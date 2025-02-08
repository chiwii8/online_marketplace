package app.exceptions;

import app.utils.ExceptionUtils;

public class IllegalBlankException extends RuntimeException{
    private static final String MESSAGE = "The String element must not be blank";
    public <T> IllegalBlankException(Class<T> classException){
        super(ExceptionUtils.formatMessage(MESSAGE,classException));
    }
}