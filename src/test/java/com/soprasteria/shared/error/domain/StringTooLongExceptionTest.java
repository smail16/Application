package com.soprasteria.shared.error.domain;

import com.soprasteria.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@UnitTest
class StringTooLongExceptionTest {

    @Test
    void shouldGetExceptionInformation() {
        StringTooLongException exception = StringTooLongException.builder().field("myField").maxLength(2).value("value").build();

        assertThat(exception.type()).isEqualTo(AssertionErrorType.STRING_TOO_LONG);
        assertThat(exception.field()).isEqualTo("myField");
        assertThat(exception.parameters()).containsOnly(entry("maxLength", "2"), entry("currentLength", "5"));
        assertThat(exception.getMessage()).isEqualTo("The value in field \"myField\" must be at most 2 long but was 5");
    }
}
