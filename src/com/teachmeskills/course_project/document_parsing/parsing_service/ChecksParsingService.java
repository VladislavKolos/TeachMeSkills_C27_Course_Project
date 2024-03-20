package com.teachmeskills.course_project.document_parsing.parsing_service;

import com.teachmeskills.course_project.session.Session;
import com.teachmeskills.course_project.util.PathToFile;
import com.teachmeskills.course_project.util.Rate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class contains private constructor which does not allow creating objects of this class
 * contains static method "doCheckFileProcess"(input parameter - our session) - method for processing files of the "Check" type
 * information is read and added to the collection
 * only files for 2023 in ".txt" format are processed
 * invalid files are moved to a separate folder
 * a collection with valid information is processed, statistics are compiled and recorded in a separate file.
 */
public final class ChecksParsingService {
    private ChecksParsingService() {
    }

    public static double doChecksFileProcess(Session session) {
        double total = 0.0;
        double checksTotal = 0.0;
        if (session != null) {
            if (session.isSessionAlive()) {
                System.out.print("\nEnter the path to the checksFolder with files: ");
                Scanner scanner = new Scanner(System.in);
                String folderPath = scanner.nextLine();

                List<String> textFilesContent = new ArrayList<>();

                String movedFilesFolder = PathToFile.PATH_TO_INVALID_REPORT_FOLDER + File.separator + "invalidChecksFiles";
                File movedFilesDir = new File(movedFilesFolder);
                if (!movedFilesDir.exists()) {
                    movedFilesDir.mkdirs();
                }

                File folder = new File(folderPath);
                File[] files = folder.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            if (file.getName().contains("2023") && file.getName().endsWith(".txt")) {
                                try {
                                    String content = Files.readString(file.toPath());
                                    textFilesContent.add(content);
                                } catch (IOException e) {
                                    System.out.println("\nAn error occurred while accessing files and directories");
                                } catch (Exception e) {
                                    System.out.println("\nSome other error");
                                }
                            } else {
                                Path sourcePath = file.toPath();
                                Path destinationPath = Paths.get(movedFilesFolder + File.separator + file.getName());
                                try {
                                    Files.move(sourcePath, destinationPath);
                                } catch (IOException e) {
                                    System.out.println("\nAn error occurred while accessing files and directories");
                                } catch (Exception e) {
                                    System.out.println("\nSome other error");
                                }
                            }
                        }
                    }
                }

                Pattern pattern = Pattern.compile("\\bEURO\\s+(\\d+,\\d+)\\b");

                for (String str : textFilesContent) {
                    Matcher matcher = pattern.matcher(str);
                    while (matcher.find()) {
                        String euroAmountStr = matcher.group(1).replace(",", ".");
                        double euroAmount = Double.parseDouble(euroAmountStr);
                        total += euroAmount * Rate.EUR;
                    }
                }
                checksTotal = (double) Math.round(total * 100) / 100;

                String outputFilePath = PathToFile.PATH_TO_FINAL_STATISTICS;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
                    writer.write("Total amount of checks in dollars was: " + checksTotal + " $");
                    System.out.println("Result written to " + outputFilePath);
                } catch (IOException e) {
                    System.out.println("\nAn error occurred while accessing files and directories");
                } catch (Exception e) {
                    System.out.println("Some other error");
                }
            } else {
                System.out.println("\nSession was expired");
            }
        } else {
            System.out.println("\nSession doesn't exists");
        }
        return checksTotal;
    }
}