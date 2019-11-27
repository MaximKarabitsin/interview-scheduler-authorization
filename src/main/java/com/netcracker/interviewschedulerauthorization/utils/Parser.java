package com.netcracker.interviewschedulerauthorization.utils;

import java.util.Optional;

public class Parser {
    public static Optional<Long> toLong(String string) {
        try {
            return Optional.of(Long.parseLong(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> toInteger(String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Boolean> toBoolean(String string) {
        if ("true".equals(string)) return Optional.of(true);
        if ("false".equals(string)) return Optional.of(false);
        return Optional.empty();
    }
}
