package io.github.ooknight.universe.support.utils.component;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;

public final class EncodeUtils {

    public String base64(String content) {
        return Base64.encode(content);
    }

    public String base64(byte[] content) {
        return Base64.encode(content);
    }

    public String hex(byte[] content) {
        return HexUtil.encodeHexStr(content);
    }
}
