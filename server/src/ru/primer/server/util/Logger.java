package ru.primer.server.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Logger {

    public static void info(String message) {
        System.out.println(timeStamp(LoggerType.INFO) + ": " + message);
    }

    public static void warn(String message) {
        System.err.println(timeStamp(LoggerType.WARN) + ": " + message);
    }

    public static void error(String message) {
        System.err.println(timeStamp(LoggerType.ERROR) + ": " + message);
    }

    private static String timeStamp(LoggerType type) {
        return "[" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                + " " + type.name() + "]";
    }
}
