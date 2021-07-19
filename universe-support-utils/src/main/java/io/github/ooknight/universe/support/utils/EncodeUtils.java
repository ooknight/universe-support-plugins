package io.github.ooknight.universe.support.utils;

import cn.hutool.core.codec.Base64;

public final class EncodeUtils {

    public String base64(String content) {
        return Base64.encode(content);
    }
}
