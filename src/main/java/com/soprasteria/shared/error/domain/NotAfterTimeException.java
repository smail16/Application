package com.soprasteria.shared.error.domain;

import java.time.Instant;

public class NotAfterTimeException extends AssertionException {

    private NotAfterTimeException(String field, String message) {
        super(field, message);
    }

    public static NotAfterTimeExceptionBuilder field(String fieldName, Instant value) {
        return new NotAfterTimeExceptionBuilder(fieldName, value);
    }

    @Override
    public AssertionErrorType type() {
        return AssertionErrorType.NOT_AFTER_TIME;
    }

    record NotAfterTimeExceptionBuilder(String fieldName, Instant value) {
        private static String message(String fieldName, Instant actual, String hint, Instant other) {
            return "Time in \"" +
                fieldName +
                "\" " +
                "having value : " +
                actual +
                ' ' +
                hint +
                " " +
                other +
                " but wasn't";
        }

        public NotAfterTimeException strictlyNotAfter(Instant other) {
            return build("must be strictly after", other);
        }

        public NotAfterTimeException notAfter(Instant other) {
            return build("must be after", other);
        }

        private NotAfterTimeException build(String hint, Instant other) {
            return new NotAfterTimeException(fieldName, message(fieldName, value, hint, other));
        }
    }
}
