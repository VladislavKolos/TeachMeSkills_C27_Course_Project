package com.teachmeskills.course_project.user_exception;

/**
 * An exception class
 * contains one field with modifiers private and final
 * contains one constructor - which accepts the exception message and passes it to the class Exception constructor.
 */
public class WrongLoginAndPasswordException extends Exception {
    private final int validationExceptionCode;

    public WrongLoginAndPasswordException(String message, int validationExceptionCode) {
        super(message);
        this.validationExceptionCode = validationExceptionCode;
    }

    public int getValidationExceptionCode() {
        return validationExceptionCode;
    }
}
