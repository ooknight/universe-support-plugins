package io.github.ooknight.universe.support.sensitive.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;

public class SensitiveSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveType type;

    @SuppressWarnings("unused")
    public SensitiveSerialize() {
    }

    private SensitiveSerialize(final SensitiveType type) {
        this.type = type;
    }

    @Override
    public void serialize(String value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeString(type.handle(value));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        Sensitive sensitiveInfo = property.getAnnotation(Sensitive.class);
        if (sensitiveInfo == null) {
            sensitiveInfo = property.getContextAnnotation(Sensitive.class);
        }
        // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
        if (sensitiveInfo != null) {
            return new SensitiveSerialize(sensitiveInfo.value());
        } else {
            return serializers.findValueSerializer(property.getType(), property);
        }
    }
}
