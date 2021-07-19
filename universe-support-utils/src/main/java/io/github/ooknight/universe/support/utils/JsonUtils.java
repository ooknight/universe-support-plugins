package io.github.ooknight.universe.support.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Optional;

public final class JsonUtils {

    public String string(Object o) {
        return string(o, false);
    }

    public String string(Object o, boolean pretty) {
        if (pretty) {
            return JSON.toJSONString(o, SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat);
        } else {
            return JSON.toJSONString(o, SerializerFeature.WriteMapNullValue);
        }
    }

    public <T> T parse(String str, Class<T> cls) {
        return JSON.parseObject(str, cls);
    }

    public String read(String key, String content) {
        return Optional.ofNullable(JSONUtil.getByPath(JSONUtil.parse(content), key)).orElse(null).toString();
    }
}
