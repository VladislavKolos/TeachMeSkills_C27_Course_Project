package com.teachmeskills.course_project.runner;

import com.teachmeskills.course_project.document_parsing.parsing_service.TotalParsingService;
import com.teachmeskills.course_project.logging.Logger;
import com.teachmeskills.course_project.session.AuthorizationService;
import com.teachmeskills.course_project.session.Session;

import java.util.Date;
import java.util.Scanner;

/**
 * Class with method main to run the program
 * you are asked to enter your login and password
 * login and password are entered
 * an object of the "Session" class is created and a called method of the "AuthorizationService" class "doAuth" is assigned to it with input parameters("login" and "password") for authorization in the program and starting the session
 * method "writeFileProcess" with input parameter "session" of class "TotalParsingService" is called in this class
 * the "logInfo" method of the "Logger" class is called to write information to "eventLog.txt".
 */
public class Runner {
    public static void main(String[] args) {
        Logger.logInfo(new Date(), "The program has started");
        Logger.logInfo(new Date(), "A new session has started");

        System.out.println("\nThe program has started\nEnter login and password:");
        Logger.logInfo(new Date(), "A message was displayed on the console about the start of the program and a request to enter a login and password");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        Logger.logInfo(new Date(), "Login entered for authorization in the program");
        String password = scanner.nextLine();
        Logger.logInfo(new Date(), "Password entered for authorization in the program");

        Session session = AuthorizationService.doAuth(login, password);
        Logger.logInfo(new Date(), "A session object is created and the \"doAuth\" method is called for authorization in the program. The input method accepts the following parameters: entered login and password");
        if (session != null) {
            if (session.isSessionAlive()) {
                TotalParsingService.writeFileProcess(session);
                Logger.logInfo(new Date(), "Called method \"writeFileProcess\" with input parameter \"session\". The method is needed to call the \"doChecksFileProcess\", \"doInvoicesFileProcess\" and \"doOrdersFileProcess\" methods (all have a common input parameter - session). The total turnover for 2023 for all types of files was calculated and recorded in the final statistics");

                System.out.println("\nProgram execution is finished\nSession ended");
                Logger.logInfo(new Date(), "A message was displayed on the console about the end of the program and the end of the session");

                Logger.logInfo(new Date(), "Program execution is finished");
                Logger.logInfo(new Date(), "Session ended");
            } else {
                System.out.println("\nSession was expired");
                Logger.logInfo(new Date(), "A message was displayed on the console stating that the session has expired");
            }
        } else {
            System.out.println("\nSession doesn't exists");
            Logger.logInfo(new Date(), "A message was displayed on the console stating that the session does not exist");
        }
    }
}
