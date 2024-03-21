package com.teachmeskills.course_project.document_parsing.parsing_service;

import com.teachmeskills.course_project.logging.Logger;
import com.teachmeskills.course_project.session.Session;
import com.teachmeskills.course_project.util.PathToFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class contains private constructor which does not allow creating objects of this class
 * contains static method "doOrdersFileProcess"(input parameter - our session) - method for processing files of the "Order" type
 * information is read and added to the collection
 * only files for 2023 in ".txt" format are processed
 * invalid files are moved to a separate folder
 * a collection with valid information is processed, statistics are compiled and recorded in a separate file
 * the "logInfo" and "logError" methods of the "Logger" class are called to write information to "eventLog.txt" and "errorLog.txt" respectively.
 */
public final class OrdersParsingService {
    private OrdersParsingService() {

    }

    public static double doOrdersFileProcess(Session session) {
        double ordersTotal = 0.0;
        Logger.logInfo(new Date(), "The variable \"ordersTotal\" is declared and initialized");
        double total = 0.0;
        Logger.logInfo(new Date(), "The variable \"total\" is declared and initialized");
        if (session != null) {
            if (session.isSessionAlive()) {
                System.out.print("\nEnter the path to the ordersFolder with files: ");
                Logger.logInfo(new Date(), "Checks have been passed to see if the session exists and is alive. A message was displayed on the console asking you to enter the path to the folder with orders");
                Scanner scanner = new Scanner(System.in);
                String folderPath = scanner.nextLine();
                Logger.logInfo(new Date(), "Entered the path to the folder with orders");

                List<String> textFilesContent = new ArrayList<>();
                Logger.logInfo(new Date(), "A collection of strings of the \"ArrayList<>\" type has been created to store processed information from valid files in the folder with orders");

                String movedFilesFolder = PathToFile.PATH_TO_INVALID_REPORT_FOLDER + File.separator + "invalidOrdersFiles";
                File movedFilesDir = new File(movedFilesFolder);
                if (!movedFilesDir.exists()) {
                    movedFilesDir.mkdirs();
                }
                Logger.logInfo(new Date(), "A folder \"invalidOrdersFiles\" has been created in the \"invalid_file\" folder to store invalid order files");

                File folder = new File(folderPath);
                File[] files = folder.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            Logger.logInfo(new Date(), "Checks have been made to see if the files exist in the \"orders\" folder and if they are actually files");
                            if (file.getName().contains("2023") && file.getName().endsWith(".txt")) {
                                Logger.logInfo(new Date(), "All files have been checked for validity");
                                try {
                                    String content = Files.readString(file.toPath());
                                    textFilesContent.add(content);
                                    Logger.logInfo(new Date(), "Information has been read from valid files and added to the \"textFilesContent\" collection");
                                } catch (IOException e) {
                                    Logger.logError(new Date(), "Error accessing files and/or directories. Error reading and/or writing files", e);
                                    System.out.println("\nAn error occurred while accessing files and directories");
                                    Logger.logInfo(new Date(), "A message was displayed on the console: \"An error occurred while accessing files and directories\"");
                                } catch (Exception e) {
                                    Logger.logError(new Date(), "An unexpected error occurred", e);
                                    System.out.println("\nSome other error");
                                    Logger.logInfo(new Date(), "An unexpected error message was displayed on the console");
                                }
                            } else {
                                Path sourcePath = file.toPath();
                                Path destinationPath = Paths.get(movedFilesFolder + File.separator + file.getName());
                                try {
                                    Files.move(sourcePath, destinationPath);
                                    Logger.logInfo(new Date(), "Invalid files have been moved to the \"invalidOrdersFiles\" folder");
                                } catch (IOException e) {
                                    Logger.logError(new Date(), "Error accessing files and/or directories. Error reading and/or writing files", e);
                                    System.out.println("\nAn error occurred while accessing files and directories");
                                    Logger.logInfo(new Date(), "A message was displayed on the console: \"An error occurred while accessing files and directories\"");
                                } catch (Exception e) {
                                    Logger.logError(new Date(), "An unexpected error occurred", e);
                                    System.out.println("\nSome other error");
                                    Logger.logInfo(new Date(), "An unexpected error message was displayed on the console");
                                }
                            }
                        }
                    }
                }

                Pattern pattern = Pattern.compile("\\bTotal\\s+([\\d.,]+)\\b");
                Logger.logInfo(new Date(), "An object of the \"Pattern\" class has been created to compose a regular expression");

                for (String str : textFilesContent) {
                    Matcher matcher = pattern.matcher(str);
                    while (matcher.find()) {
                        String numberStr = matcher.group(1).replaceAll(",", "");
                        total += Double.parseDouble(numberStr);
                    }
                }
                ordersTotal = (double) Math.round(total * 100) / 100;
                Logger.logInfo(new Date(), "The total amount for each valid file from the folder with invoices was taken and added together and recorded in the \"combinedInvoicesTotal\" variable");

                String outputFilePath = PathToFile.PATH_TO_FINAL_STATISTICS;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
                    writer.write("\nTotal amount of orders in dollars was: " + ordersTotal + " $");
                    Logger.logInfo(new Date(), "The total turnover of all orders is recorded in the final statistics");
                    System.out.println("Result written to " + outputFilePath);
                    Logger.logInfo(new Date(), "A message was displayed on the console about which path the result was written to");
                } catch (IOException e) {
                    Logger.logError(new Date(), "Error accessing files and/or directories. Error reading and/or writing files", e);
                    System.out.println("\nAn error occurred while accessing files and directories");
                    Logger.logInfo(new Date(), "A message was displayed on the console: \"An error occurred while accessing files and directories\"");
                } catch (Exception e) {
                    Logger.logError(new Date(), "An unexpected error occurred", e);
                    System.out.println("Some other error");
                    Logger.logInfo(new Date(), "An unexpected error message was displayed on the console");
                }
            } else {
                System.out.println("\nSession was expired");
                Logger.logInfo(new Date(), "A message was displayed on the console stating that the session has expired");
            }
        } else {
            System.out.println("\nSession doesn't exists");
            Logger.logInfo(new Date(), "A message was displayed on the console stating that the session does not exist");
        }
        return ordersTotal;
    }
}
