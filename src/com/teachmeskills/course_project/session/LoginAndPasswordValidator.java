package com.teachmeskills.course_project.session;

import com.teachmeskills.course_project.coder.Coder;
import com.teachmeskills.course_project.logging.Logger;
import com.teachmeskills.course_project.storage.StorageMock;
import com.teachmeskills.course_project.user_exception.WrongLoginAndPasswordException;

import java.util.Date;

/**
 * Class contains private constructor which does not allow creating objects of this class
 * contains static boolean method "checkLoginAndPassword"(input parameter - login and password)
 * using the "if-else" statement, the installed logins and passwords are compared with the logins and passwords from the "StorageMock"
 * the "logInfo" method of the "Logger" class is called to write information to "eventLog.txt".
 */
public final class LoginAndPasswordValidator {
    private LoginAndPasswordValidator() {
    }

    public static boolean checkLoginAndPassword(String login, String password) throws WrongLoginAndPasswordException {
        StorageMock storageMock = new StorageMock();
        String loginFromStorage = storageMock.getLogin();
        String passwordFromStorage = storageMock.getPassword();
        Logger.logInfo(new Date(), "The login and password were taken from the storage \"StorageMock\"");

        String decodedLogin = Coder.decodeLogin(loginFromStorage);
        String decodedPassword = Coder.decodePassword(passwordFromStorage);
        Logger.logInfo(new Date(), "Login and password decoded");

        if (!login.equalsIgnoreCase(decodedLogin) || !password.equals(decodedPassword)) {
            Logger.logInfo(new Date(), "Login or password verification failed");
            throw new WrongLoginAndPasswordException("\nError, wrong login or password", 309);
        } else {
            Logger.logInfo(new Date(), "Login and password verification completed successfully");
            System.out.println("\nThe user is successfully authorized in the system. New session started");
            Logger.logInfo(new Date(), "A message was displayed on the console that the user was successfully authorized in the system. New session started");
            return true;
        }
    }
}
