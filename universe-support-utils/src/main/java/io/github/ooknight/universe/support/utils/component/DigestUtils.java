package io.github.ooknight.universe.support.utils.component;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * HEX 输出默认为 "小写", 即 lower 默认为 true
 */
public final class DigestUtils {

    public String md5(String content) {
        return HexUtil.encodeHexStr(DigestUtil.md5(content), true);
    }

    public String md5(String content, boolean lower) {
        return HexUtil.encodeHexStr(DigestUtil.md5(content), lower);
    }

    public String md5(byte[] content) {
        return HexUtil.encodeHexStr(DigestUtil.md5(content));
    }

    public String sha1(String content) {
        return HexUtil.encodeHexStr(DigestUtil.sha1(content), true);
    }

    public String sha1(String content, boolean lower) {
        return HexUtil.encodeHexStr(DigestUtil.sha1(content), lower);
    }

    public boolean verify(byte[] signed, byte[] data) {
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withRSA);
        return sign.verify(data, signed);
    }

    public byte[] sign(byte[] data) {
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withRSA);
        return sign.sign(data);
    }
}
