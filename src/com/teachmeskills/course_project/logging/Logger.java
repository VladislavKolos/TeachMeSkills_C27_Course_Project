package com.teachmeskills.course_project.logging;

import com.teachmeskills.course_project.util.PathToFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class contains private constructor which does not allow creating objects of this class
 * contains two static methods for creating action messages and creating error messages to record them in the corresponding logs.
 */
public class Logger {
    private Logger() {

    }

    public static void logInfo(Date date, String infoMessage) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateToLog = sdf.format(date);
            String message = "[INFO] -> " + dateToLog + " -> " + infoMessage + "\n";
            Files.write(Paths.get(PathToFile.PATH_TO_EVENT_LOG), message.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            // do nothing
        }
    }

    public static void logError(Date date, String errorMessage, Exception exception) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateToLog = sdf.format(date);

            StringBuilder sb = new StringBuilder();
            sb.append("[ERROR] -> ").append(dateToLog).append(" -> ").append(errorMessage);
            sb.append("\n");

            StackTraceElement[] traceElements = exception.getStackTrace();
            for (StackTraceElement element : traceElements) {
                sb.append("\t").append(element.toString());
                sb.append("\n");
            }

            Files.write(Paths.get(PathToFile.PATH_TO_ERROR_LOG), sb.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            // do nothing
        }
    }
}
