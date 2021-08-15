package io.github.ooknight.universe.support.utils.component;

import cn.hutool.core.net.url.UrlBuilder;

import java.nio.charset.StandardCharsets;

public final class UrlUtils {

    public String create(String prefix, String path) {
        return UrlBuilder.of(prefix, StandardCharsets.UTF_8).addPath(path).build();
    }

    public UrlBuilder of(String prefix) {
        return UrlBuilder.of(prefix, StandardCharsets.UTF_8);
    }
}
