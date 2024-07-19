package org.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public static void logInfo(String message) {
        logger.info(message);
        System.out.println(message);;
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
