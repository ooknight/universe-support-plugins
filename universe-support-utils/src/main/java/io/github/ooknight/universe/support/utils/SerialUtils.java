package io.github.ooknight.universe.support.utils;

import cn.hutool.core.util.IdUtil;

public final class SerialUtils {

    /*
     * 4aa75521d4be4d39980dc38b203240a8
     */
    public String uuid() {
        return IdUtil.fastSimpleUUID();
    }

    /*
     * 1177489476419719168
     */
    public String code() {
        return IdUtil.getSnowflake(1L, 1L).nextIdStr();
    }

    public String code(String prefix) {
        return prefix + IdUtil.getSnowflake(1L, 1L).nextIdStr();
    }

    //
    //public String next() {
    //    return IdUtil.getSnowflake(1L, 1L).nextIdStr();
    //}
    //
    //public String next(String prefix) {
    //    return prefix + IdUtil.getSnowflake(1L, 1L).nextIdStr();
    //}
}
