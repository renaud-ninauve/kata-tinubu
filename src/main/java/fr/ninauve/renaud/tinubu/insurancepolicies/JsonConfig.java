package fr.ninauve.renaud.tinubu.insurancepolicies;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class JsonConfig {

//    @Bean
//    //@JsonComponent
//    public Module javaTimeModule() {
//        JavaTimeModule module = new JavaTimeModule();
//        module.addSerializer(Instant.class, new MyCustomSerializer());
//        module.addDeserializer(Instant.class, new MyCustomDeserializer());
//        return module;
//    }

//    @JsonComponent
//    public static class MyCustomSerializer extends JsonSerializer<Instant> {
//
//        //private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
//        private DateTimeFormatter fmt = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("UTC"));
//
//        @Override
//        public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
//            String str = fmt.withZone(ZoneId.of("UTC")).format(value);
//
//            gen.writeString(str);
//        }
//    }
//
//    @JsonComponent
//    public static class MyCustomDeserializer extends JsonDeserializer<Instant> {
//
//        //private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
//        private DateTimeFormatter fmt = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("UTC"));
//
//        @Override
//        public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//           // return null;
//            return Instant.from(fmt.parse(p.getText()));
//        }
//    }
}
