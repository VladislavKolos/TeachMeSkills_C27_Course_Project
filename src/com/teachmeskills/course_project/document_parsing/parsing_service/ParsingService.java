package com.teachmeskills.course_project.document_parsing.parsing_service;

import com.teachmeskills.course_project.session.Session;

public final class ParsingService {
    private ParsingService() {
    }

    public static void doFileProcess(Session session, String pathToFolder) {
        if (session != null) {
            if (session.isSessionAlive()) {
                //TODO Do file processing
            } else {
                System.out.println("\nSession was expired");
            }
        } else {
            System.out.println("\nSession doesn't exists");
        }
    }
}
