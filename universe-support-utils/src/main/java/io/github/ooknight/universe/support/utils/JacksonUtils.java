package io.github.ooknight.universe.support.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Map;

import static oo.UTILS.x;

public final class JacksonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.registerModule(new JavaTimeModule());
        //mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        //mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        //mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        //mapper.configure(MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING, true);
    }

    public String string(Object o) {
        return x.throwable.propagate(() -> mapper.writeValueAsString(o));
    }

    public String string(Object o, boolean pretty) {
        if (pretty) {
            return x.throwable.propagate(() -> mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o));
        } else {
            return x.throwable.propagate(() -> mapper.writeValueAsString(o));
        }
    }

    public <T> T parse(String content, Class<T> cls) {
        return x.throwable.propagate(() -> mapper.readValue(content, cls));
    }

    public String read(String key, String content) {
        return x.throwable.propagate(() -> mapper.readTree(content).get(key).asText());
    }

    public Map<String, String> map(String content) {
        MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, String.class);
        return x.throwable.propagate(() -> mapper.readValue(content, type));
    }

    public Map<String, String> map(Object bean) {
        MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, String.class);
        return mapper.convertValue(bean, type);
    }
}
