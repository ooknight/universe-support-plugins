package oo;

import io.github.ooknight.universe.support.utils.CertificateUtils;
import io.github.ooknight.universe.support.utils.CollectionUtils;
import io.github.ooknight.universe.support.utils.ConsoleUtils;
import io.github.ooknight.universe.support.utils.CryptoUtils;
import io.github.ooknight.universe.support.utils.DateTimeUtils;
import io.github.ooknight.universe.support.utils.DateUtils;
import io.github.ooknight.universe.support.utils.DigestUtils;
import io.github.ooknight.universe.support.utils.EncodeUtils;
import io.github.ooknight.universe.support.utils.JacksonUtils;
import io.github.ooknight.universe.support.utils.JsonUtils;
import io.github.ooknight.universe.support.utils.MobileUtils;
import io.github.ooknight.universe.support.utils.NumberUtils;
import io.github.ooknight.universe.support.utils.PageUtils;
import io.github.ooknight.universe.support.utils.ParamUtils;
import io.github.ooknight.universe.support.utils.PinyinUtils;
import io.github.ooknight.universe.support.utils.RandomUtils;
import io.github.ooknight.universe.support.utils.RemoteUtils;
import io.github.ooknight.universe.support.utils.SerialUtils;
import io.github.ooknight.universe.support.utils.StringUtils;
import io.github.ooknight.universe.support.utils.ThrowableUtils;
import io.github.ooknight.universe.support.utils.TimestampUtils;
import io.github.ooknight.universe.support.utils.UrlUtils;
import io.github.ooknight.universe.support.utils.ZipUtils;

import cn.hutool.core.util.ClassLoaderUtil;

public final class UTILS {

    public static final UTILS x = new UTILS();

    public final CertificateUtils certificate = U.certificate;
    public final ConsoleUtils console = U.console;
    public final CollectionUtils collection = U.collection;
    public final CryptoUtils crypto = U.crypto;
    public final DateUtils date = U.date;
    public final DateTimeUtils datetime = U.datetime;
    public final DigestUtils digest = U.digest;
    public final EncodeUtils encode = U.encode;
    public final JacksonUtils jackson = U.jackson;
    public final JsonUtils json = U.json;
    public final MobileUtils mobile = U.mobile;
    public final NumberUtils number = U.number;
    public final PageUtils page = U.page;
    public final ParamUtils param = U.param;
    public final PinyinUtils pinyin = U.pinyin;
    public final RandomUtils random = U.random;
    public final RemoteUtils remote = U.remote;
    public final SerialUtils serial = U.serial;
    public final StringUtils string = U.string;
    public final ThrowableUtils throwable = U.throwable;
    public final TimestampUtils timestamp = U.timestamp;
    public final UrlUtils url = U.url;
    public final ZipUtils zip = U.zip;

    private UTILS() {
    }

    public static class U {

        public static final CertificateUtils certificate = new CertificateUtils();
        public static final ConsoleUtils console = new ConsoleUtils();
        public static final CollectionUtils collection = new CollectionUtils();
        public static final CryptoUtils crypto = new CryptoUtils();
        public static final DateUtils date = new DateUtils();
        public static final DateTimeUtils datetime = new DateTimeUtils();
        public static final DigestUtils digest = new DigestUtils();
        public static final EncodeUtils encode = new EncodeUtils();
        public static final JacksonUtils jackson = new JacksonUtils();
        public static final JsonUtils json = new JsonUtils();
        public static final MobileUtils mobile = new MobileUtils();
        public static final NumberUtils number = new NumberUtils();
        public static final PageUtils page = hasSpringDataAndEbean() ? new PageUtils() : null;
        public static final ParamUtils param = new ParamUtils();
        public static final PinyinUtils pinyin = new PinyinUtils();
        public static final RandomUtils random = new RandomUtils();
        public static final RemoteUtils remote = new RemoteUtils();
        public static final SerialUtils serial = new SerialUtils();
        public static final StringUtils string = new StringUtils();
        public static final ThrowableUtils throwable = new ThrowableUtils();
        public static final TimestampUtils timestamp = new TimestampUtils();
        public static final UrlUtils url = new UrlUtils();
        public static final ZipUtils zip = new ZipUtils();

        private static boolean hasSpringDataAndEbean() {
            return ClassLoaderUtil.isPresent("org.springframework.data.domain.Page")
                && ClassLoaderUtil.isPresent("org.springframework.data.domain.Pageable")
                && ClassLoaderUtil.isPresent("io.ebean.PagedList");
        }
    }
}
