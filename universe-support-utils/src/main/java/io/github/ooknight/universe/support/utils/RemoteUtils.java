package io.github.ooknight.universe.support.utils;

import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.HttpServletRequest;

public final class RemoteUtils {

    public String ip(HttpServletRequest request) {
        // 0:0:0:0:0:0:0:1 ? "127.0.0.1" : ip
        return ServletUtil.getClientIP(request);
    }
}
