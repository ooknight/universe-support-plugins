package io.github.ooknight.universe.support.utils.component;

import cn.hutool.json.JSONUtil;

import java.util.Map;
import java.util.Optional;

public interface JsonUtils {

    default String string(Object o) {
        return JSONUtil.toJsonStr(o);
    }

    default String string(Object o, boolean pretty) {
        if (pretty) {
            return JSONUtil.toJsonPrettyStr(o);
        } else {
            return JSONUtil.toJsonStr(o);
        }
    }

    default <T> T parse(String str, Class<T> cls) {
        return JSONUtil.toBean(str, cls);
    }

    default String read(String key, String content) {
        return Optional.ofNullable(JSONUtil.getByPath(JSONUtil.parse(content), key).toString()).orElse(null);
    }

    Map<String, String> map(String content);

    Map<String, String> map(Object bean);
}
