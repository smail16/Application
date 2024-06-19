package com.soprasteria.wire.mongodb.infrastructure.secondary;

import com.soprasteria.wire.mongodb.infrastructure.secondary.JSR310DateConverters.DateToZonedDateTimeConverter;
import com.soprasteria.wire.mongodb.infrastructure.secondary.JSR310DateConverters.ZonedDateTimeToDateConverter;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories({"com.soprasteria"})
@Import(value = MongoAutoConfiguration.class)
class MongodbDatabaseConfiguration {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(DateToZonedDateTimeConverter.INSTANCE);
        converters.add(ZonedDateTimeToDateConverter.INSTANCE);
        return new MongoCustomConversions(converters);
    }
}
