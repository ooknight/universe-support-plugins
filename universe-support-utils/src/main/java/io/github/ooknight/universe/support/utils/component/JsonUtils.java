package io.github.ooknight.universe.support.utils.component;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Map;
import java.util.Optional;

public interface JsonUtils {

    default String string(Object o) {
        return string(o, false);
    }

    default String string(Object o, boolean pretty) {
        if (pretty) {
            return JSON.toJSONString(o, SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat);
        } else {
            return JSON.toJSONString(o, SerializerFeature.WriteMapNullValue);
        }
    }

    default <T> T parse(String str, Class<T> cls) {
        return JSON.parseObject(str, cls);
    }

    default String read(String key, String content) {
        return Optional.ofNullable(JSONUtil.getByPath(JSONUtil.parse(content), key)).orElse(null).toString();
    }

    Map<String, String> map(String content);

    Map<String, String> map(Object bean);
}
