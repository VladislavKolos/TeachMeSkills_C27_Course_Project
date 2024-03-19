package com.teachmeskills.course_project.session;

import com.teachmeskills.course_project.coder.Coder;
import com.teachmeskills.course_project.storage.StorageMock;
import com.teachmeskills.course_project.user_exception.WrongLoginAndPasswordException;

/**
 * Class contains private constructor which does not allow creating objects of this class
 * contains static boolean method "checkLoginAndPassword"(input parameter - login and password)
 * using the "if-else" statement, the installed logins and passwords are compared with the logins and passwords from the "StorageMock".
 */
public final class LoginAndPasswordValidator {
    private LoginAndPasswordValidator() {
    }

    public static boolean checkLoginAndPassword(String login, String password) throws WrongLoginAndPasswordException {
        StorageMock storageMock = new StorageMock();
        String loginFromStorage = storageMock.getLogin();
        String passwordFromStorage = storageMock.getPassword();

        String decodedLogin = Coder.decodeLogin(loginFromStorage);
        String decodedPassword = Coder.decodePassword(passwordFromStorage);

        if (!login.equalsIgnoreCase(decodedLogin) || !password.equals(decodedPassword)) {
            throw new WrongLoginAndPasswordException("\nError, wrong login or password", 309);
        } else {
            System.out.println("\nThe user is successfully authorized in the system. New session started");
            return true;
        }
    }
}
