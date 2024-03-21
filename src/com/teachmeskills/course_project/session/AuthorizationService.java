package com.teachmeskills.course_project.session;

import com.teachmeskills.course_project.logging.Logger;
import com.teachmeskills.course_project.user_exception.WrongLoginAndPasswordException;

import java.util.Date;

/**
 * Class contains private constructor which does not allow creating objects of this class
 * contains static method "doAuth"(input parameter - login and password) - method for logging into the program and starting a new session
 * the return value is our session
 * the "logInfo" and "logError" methods of the "Logger" class are called to write information to "eventLog.txt" and "errorLog.txt" respectively.
 */
public class AuthorizationService {
    private AuthorizationService() {

    }

    public static Session doAuth(String login, String password) {
        try {
            LoginAndPasswordValidator.checkLoginAndPassword(login, password);
            Logger.logInfo(new Date(), "The \"checkLoginAndPassword\" method is called with input parameters login and password to check the login and password");
        } catch (WrongLoginAndPasswordException e) {
            Logger.logError(new Date(), "Incorrect login and/or password", e);
            System.out.println(e.getMessage());
            Logger.logInfo(new Date(), "A message was displayed on the console with error code 309 \"Error, wrong login or password\"");
            return null;
        } catch (Exception e) {
            Logger.logError(new Date(), "An unexpected error occurred", e);
            System.out.println("Some other error");
            Logger.logInfo(new Date(), "An unexpected error message was displayed on the console");
            return null;
        }
        return new Session();
    }
}
