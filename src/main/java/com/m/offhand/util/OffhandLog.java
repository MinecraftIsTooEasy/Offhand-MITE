package com.m.offhand.util;

import com.m.offhand.OffhandMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class OffhandLog {
    private static final Logger LOGGER = LogManager.getLogger(OffhandMod.NameSpace);

    private OffhandLog() {
    }

    public static void info(String message, Object... params) {
        LOGGER.info(message, params);
    }

    public static void debug(String message, Object... params) {
        LOGGER.debug(message, params);
    }

    public static void warn(String message, Object... params) {
        LOGGER.warn(message, params);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}
