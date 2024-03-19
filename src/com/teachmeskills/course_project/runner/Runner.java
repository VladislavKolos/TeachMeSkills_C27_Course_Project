package com.teachmeskills.course_project.runner;

import com.teachmeskills.course_project.document_parsing.parsing_service.ParsingService;
import com.teachmeskills.course_project.session.AuthorizationService;
import com.teachmeskills.course_project.session.Session;
import com.teachmeskills.course_project.util.PathToFile;

import java.util.Scanner;

/**
 * Class with method main to run the program
 * you are asked to enter your login and password
 * login and password are entered
 * an object of the "Session" class is created and a called method of the "AuthorizationService" class "doAuth" is assigned to it with input parameters("login" and "password") for authorization in the program and starting the session
 */
public class Runner {
    public static void main(String[] args) {
        System.out.println("\nEnter login and password:");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        Session session = AuthorizationService.doAuth(login, password);

        ParsingService.doFileProcess(session, PathToFile.PATH_TO_CHECKS);
        ParsingService.doFileProcess(session, PathToFile.PATH_TO_INVOICES);
        ParsingService.doFileProcess(session, PathToFile.PATH_TO_ORDERS);


        scanner.close();
    }
}
