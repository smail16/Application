package com.soprasteria.wire.mongodb.infrastructure.secondary;

import com.soprasteria.UnitTest;
import com.soprasteria.wire.mongodb.infrastructure.secondary.JSR310DateConverters.DateToZonedDateTimeConverter;
import com.soprasteria.wire.mongodb.infrastructure.secondary.JSR310DateConverters.ZonedDateTimeToDateConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class JSR310DateConvertersTest {

    @Test
    @DisplayName("Should convert ZonedDateTime to Date")
    void shouldConvertZonedDateTimeToDate() {
        ZonedDateTime source = ZonedDateTime.parse("2022-02-15T12:00:00+01:00[Europe/Paris]");
        Date expected = Date.from(source.toInstant());
        Date result = ZonedDateTimeToDateConverter.INSTANCE.convert(source);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should convert Date to ZonedDateTime")
    void shouldConvertDateToZonedDateTime() {
        ZonedDateTime expected = ZonedDateTime.parse("2022-02-15T12:00:00+01:00[Europe/Paris]");
        Date source = Date.from(expected.toInstant());
        ZonedDateTime result = DateToZonedDateTimeConverter.INSTANCE.convert(source);
        assertThat(result).isEqualTo(expected);
    }
}
