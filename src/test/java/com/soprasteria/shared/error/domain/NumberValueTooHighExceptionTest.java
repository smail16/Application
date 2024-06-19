package com.soprasteria.shared.error.domain;

import com.soprasteria.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@UnitTest
class NumberValueTooHighExceptionTest {

    @Test
    void shouldGetExceptionInformation() {
        NumberValueTooHighException exception = NumberValueTooHighException.builder().field("myField").maxValue("42").value("1337").build();

        assertThat(exception.type()).isEqualTo(AssertionErrorType.NUMBER_VALUE_TOO_HIGH);
        assertThat(exception.field()).isEqualTo("myField");
        assertThat(exception.parameters()).containsOnly(entry("max", "42"), entry("value", "1337"));
        assertThat(exception.getMessage()).isEqualTo("Value of field \"myField\" must be at most 42 but was 1337");
    }
}
