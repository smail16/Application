package com.soprasteria.shared.error.domain;

public class MissingMandatoryValueException extends AssertionException {

    private MissingMandatoryValueException(String field, String message) {
        super(field, message);
    }

    public static MissingMandatoryValueException forBlankValue(String field) {
        return new MissingMandatoryValueException(field, defaultMessage(field, "blank"));
    }

    public static MissingMandatoryValueException forNullValue(String field) {
        return new MissingMandatoryValueException(field, defaultMessage(field, "null"));
    }

    public static MissingMandatoryValueException forEmptyValue(String field) {
        return new MissingMandatoryValueException(field, defaultMessage(field, "empty"));
    }

    private static String defaultMessage(String field, String reason) {
        return "The field \"" +
            field +
            "\" is mandatory and wasn't set" +
            " (" +
            reason +
            ")";
    }

    @Override
    public AssertionErrorType type() {
        return AssertionErrorType.MISSING_MANDATORY_VALUE;
    }
}
