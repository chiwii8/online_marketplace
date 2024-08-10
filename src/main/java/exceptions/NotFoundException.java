package exceptions;

import utils.ExceptionUtils;

public class NotFoundException extends RuntimeException{
    private static final String MESSAGE = "The element is not found in the database";
    public <T> NotFoundException(Class<T> classException){
        super(ExceptionUtils.formatMessage(MESSAGE,classException));
    }

    public NotFoundException(String message){
        super(message);
    }

    public <T> NotFoundException(String message,Class<T> classException){
        super(ExceptionUtils.formatMessage(message,classException));

    }
}