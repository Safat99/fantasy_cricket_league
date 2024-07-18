package com.sarker.fcl_main.common.utils;

import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtils {
    private DateUtils() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    public static Date convertToDate(@NotNull LocalDateTime dateToConvert) {
        return Timestamp.valueOf(dateToConvert);
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
