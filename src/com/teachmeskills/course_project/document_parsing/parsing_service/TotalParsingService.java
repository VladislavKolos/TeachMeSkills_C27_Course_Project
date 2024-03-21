package com.teachmeskills.course_project.document_parsing.parsing_service;

import com.teachmeskills.course_project.logging.Logger;
import com.teachmeskills.course_project.session.Session;
import com.teachmeskills.course_project.util.PathToFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Class contains private constructor which does not allow creating objects of this class
 * contains static method "writeFileProcess"(input parameter - our session) method for records of general summary statistics for all types of documents for 2023
 * the "logInfo" and "logError" methods of the "Logger" class are called to write information to "eventLog.txt" and "errorLog.txt" respectively.
 */
public final class TotalParsingService {
    private TotalParsingService() {

    }

    public static void writeFileProcess(Session session) {
        double total = ChecksParsingService.doChecksFileProcess(session) + InvoicesParsingService.doInvoicesFileProcess(session) + OrdersParsingService.doOrdersFileProcess(session);
        double grandTotal = (double) Math.round(total * 100) / 100;
        String outputFilePath = PathToFile.PATH_TO_FINAL_STATISTICS;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            writer.write("\nThe total turnover for 2023 on checks, invoices and orders was: " + grandTotal + " $");
            writer.write("\n");
            Logger.logInfo(new Date(), "The total total turnover for 2023 for all valid types of financial documents is recorded in the final statistics");
        } catch (IOException e) {
            Logger.logError(new Date(), "Error accessing files and/or directories. Error reading and/or writing files", e);
            System.out.println("\nAn error occurred while accessing files and directories");
            Logger.logInfo(new Date(), "A message was displayed on the console: \"An error occurred while accessing files and directories\"");
        } catch (Exception e) {
            Logger.logError(new Date(), "An unexpected error occurred", e);
            System.out.println("Some other error");
            Logger.logInfo(new Date(), "An unexpected error message was displayed on the console");
        }
    }
}
