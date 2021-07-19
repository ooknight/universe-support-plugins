package io.github.ooknight.universe.support.utils;

import cn.hutool.core.util.IdcardUtil;

public final class CertificateUtils {

    public int age(String idcard) {
        return IdcardUtil.getAgeByIdCard(idcard);
    }
}
