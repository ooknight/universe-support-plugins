package io.github.ooknight.universe.support.utils;

import io.github.ooknight.universe.support.utils.component.CertificateUtils;
import io.github.ooknight.universe.support.utils.component.CollectionUtils;
import io.github.ooknight.universe.support.utils.component.ConsoleUtils;
import io.github.ooknight.universe.support.utils.component.CryptoUtils;
import io.github.ooknight.universe.support.utils.component.DateTimeUtils;
import io.github.ooknight.universe.support.utils.component.DateUtils;
import io.github.ooknight.universe.support.utils.component.DigestUtils;
import io.github.ooknight.universe.support.utils.component.EncodeUtils;
import io.github.ooknight.universe.support.utils.component.JacksonUtils;
import io.github.ooknight.universe.support.utils.component.JsonUtils;
import io.github.ooknight.universe.support.utils.component.MobileUtils;
import io.github.ooknight.universe.support.utils.component.NumberUtils;
import io.github.ooknight.universe.support.utils.component.PageUtils;
import io.github.ooknight.universe.support.utils.component.ParamUtils;
import io.github.ooknight.universe.support.utils.component.PinyinUtils;
import io.github.ooknight.universe.support.utils.component.RandomUtils;
import io.github.ooknight.universe.support.utils.component.RemoteUtils;
import io.github.ooknight.universe.support.utils.component.SerialUtils;
import io.github.ooknight.universe.support.utils.component.StringUtils;
import io.github.ooknight.universe.support.utils.component.ThrowableUtils;
import io.github.ooknight.universe.support.utils.component.TimestampUtils;
import io.github.ooknight.universe.support.utils.component.UrlUtils;
import io.github.ooknight.universe.support.utils.component.ZipUtils;

import cn.hutool.core.util.ClassLoaderUtil;

public final class COMBINE {

    public static final COMBINE x = new COMBINE();

    public final CertificateUtils certificate = U.certificate;
    public final ConsoleUtils console = U.console;
    public final CollectionUtils collection = U.collection;
    public final CryptoUtils crypto = U.crypto;
    public final DateUtils date = U.date;
    public final DateTimeUtils datetime = U.datetime;
    public final DigestUtils digest = U.digest;
    public final EncodeUtils encode = U.encode;
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

    private COMBINE() {}

    public static class U {

        public static final CertificateUtils certificate = new CertificateUtils();
        public static final ConsoleUtils console = new ConsoleUtils();
        public static final CollectionUtils collection = new CollectionUtils();
        public static final CryptoUtils crypto = new CryptoUtils();
        public static final DateUtils date = new DateUtils();
        public static final DateTimeUtils datetime = new DateTimeUtils();
        public static final DigestUtils digest = new DigestUtils();
        public static final EncodeUtils encode = new EncodeUtils();
        public static final JsonUtils json = useJackson() ? new JacksonUtils() : null;
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

        private static boolean useJackson() {
            return true;
        }
    }
}
