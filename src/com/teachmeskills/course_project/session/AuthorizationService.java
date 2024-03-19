package com.teachmeskills.course_project.session;

import com.teachmeskills.course_project.user_exception.WrongLoginAndPasswordException;

/**
 * Class contains private constructor which does not allow creating objects of this class
 * contains static method "doAuth"(input parameter - login and password) - method for logging into the program and starting a new session
 * the return value is our session.
 */
public class AuthorizationService {
    private AuthorizationService() {

    }

    public static Session doAuth(String login, String password) {
        try {
            LoginAndPasswordValidator.checkLoginAndPassword(login, password);
        } catch (WrongLoginAndPasswordException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Some other error");
        }
        return new Session();
    }
}
