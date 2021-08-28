package test.io.github.ooknight.universe.support.utils;

import test.io.github.ooknight.universe.support.model.Line;
import test.io.github.ooknight.universe.support.model.Point;

import io.github.ooknight.universe.support.utils.component.CryptoUtils;
import static io.github.ooknight.universe.support.utils.COMBINE.U.console;
import static io.github.ooknight.universe.support.utils.COMBINE.U.digest;
import static io.github.ooknight.universe.support.utils.COMBINE.x;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Testing {

    @Test
    void console() {
        x.console.echo(new Point());
        x.console.echo("test");
        x.console.echo(Lists.newArrayList(new Point(), new Point()));
        x.console.echo(Lists.newArrayList("test1", "test2"));
    }

    @Test
    void rsa1() {
        CryptoUtils.RSA rsa = x.crypto.rsa();
        //
        String publicKey = rsa.publicKey();
        String privateKey = rsa.privateKey();
        byte[] r1 = rsa.encrypt("abc".getBytes());
        byte[] r2 = rsa.decrypt(r1);
        assertNotNull(publicKey);
        assertNotNull(privateKey);
        assertNotNull(r1);
        assertEquals("abc", new String(r2));
    }

    @Test
    void rsa2() {
        String publicKey0 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqI/sgsLSsyOmuCFnJkKvo0byA3QXYB27kU5Yhbio7pNwDXoubTfz08o/A0clGKFZDsVoNfYQ4lp+D8iS9MPuVBivj2CMnMN2/wby" +
            "21qbWdc6Egn8JsjeLVh26p3GZfm9FWDOcC+bTBf0NJcsHi+fwDFhxO2uBbzjkeXGGazyOIQIDAQAB";
        String privateKey0 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKoj+yCwtKzI6a4IWcmQq+jRvIDdBdgHbuRTliFuKjuk3ANei5tN/PTyj8DRyUYoVkOxWg19hDiWn4PyJL0w+5UGK+" +
            "PYIycw3b/BvLbWptZ1zoSCfwmyN4tWHbqncZl+b0VYM5wL5tMF/Q0lyweL5/AMWHE7a4FvOOR5cYZrPI4hAgMBAAECgYEAoXDm14wUxGcYJQTTfcmC9bYeMgCHjQbwmEMgD7Si6llyUtghjEoiHqMS+JmXb" +
            "LTVtgHROz/AeJmj3uWjKsoRzTWhvL7vaFiebj5SdKqYoCeIPdSWGXII3UqCMx8A2hsz9eEwBqdWnZ+Y7+maTuhDu4PAF31Cm7LwPq3lzYV2s0kCQQDpx4W5wtkACzll0MsrzmY4X0YMCD40umpmZ2MBjVcv" +
            "iKqGDjIl8/pR+J8uVItBwDHDisdb9rkI2YhQIezBmhX7AkEAuk/0oUk68wDI2T84wITCeWNmGEIXzgBneWSHab0hJvf9OsSU65EYlcHjt89OELEkgPTfvPuROMSj482KSSWdkwJAUX6qUU1pVCiVg7a7fLP" +
            "g0nnz8zHUbZEmkjgsPVhGMl3UBBNiy/Q3KOggpYf3W55iOqZgRODHNByQ9k49OGg8jQJAQ166JMAoMsqr1fLIgMUA1Ygd2NO9a941jG3PkWq0XDT8Oo3vE6dp7m0n/MVXgy2q936UkO+euZpRcQRX5s9VRQ" +
            "JAAIZlnSV5HDHhv0QEwgVy3L+0fBEc9fqYSJdc6Y6KGVJv16I1pVZ60MNHsROrAp9sdy43hPiTQT3CWEBQRuPFOw==";
        CryptoUtils.RSA rsa = x.crypto.rsa(publicKey0, privateKey0);
        //
        String publicKey = rsa.publicKey();
        String privateKey = rsa.privateKey();
        byte[] r1 = rsa.encrypt("abc".getBytes());
        byte[] r2 = rsa.decrypt(r1);
        assertEquals(publicKey0, publicKey);
        assertEquals(privateKey0, privateKey);
        assertEquals("abc", new String(r2));
    }

    @Test
    void aes1() {
        CryptoUtils.AES aes = x.crypto.aes();
        String key = aes.key();
        byte[] r1 = aes.encrypt("abc".getBytes());
        byte[] r2 = aes.decrypt(r1);
        assertNotNull(key);
        assertNotNull(Base64.encode(r1));
        assertEquals("abc", new String(r2));
    }

    @Test
    void aes2() {
        CryptoUtils.AES aes = x.crypto.aes("uJpNOqTcF87LztgXWOTFFQ==");
        String key = aes.key();
        byte[] r1 = aes.encrypt("abc".getBytes());
        byte[] r2 = aes.decrypt(r1);
        assertEquals("uJpNOqTcF87LztgXWOTFFQ==", key);
        assertEquals("zOEra0+YHmtcSp4EgETRcQ==", Base64.encode(r1));
        assertEquals("abc", new String(r2));
    }

    @Test
    void sign() {
        String content = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        byte[] data = content.getBytes();

        // 此处密钥如果有非ASCII字符，考虑编码
        byte[] key = "12312312312jsldfoiwuerklwjelr".getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, key);

        // b977f4b13f93f549e06140971bded384
        String macHex1 = mac.digestHex(content);

        System.out.println(macHex1);


    }

    @Test
    void datetime() {
        console.echo(x.date.now());
        console.echo(x.datetime.now());
        console.echo(x.timestamp.now());
        console.echo(x.timestamp.standard());
    }

    @Test
    void digest() {
        // e10adc3949ba59abbe56e057f20f883e
        console.echo(digest.md5("123456"));
        console.echo(digest.md5("123456", true));
        console.echo(digest.md5("123456", false));
    }

    @Test
    void jackson() {
        //console.echo(z.jackson.string(Lists.newArrayList(1, 2, 3)));
        //console.echo(z.jackson.string(Lists.newArrayList(1, 2, 3), true));
        ////
        Point point = new Point(1, 2, "test", LocalDateTime.of(2020, 1, 1, 1, 1, 1), "gg");
        //console.echo(z.jackson.string(point));
        //console.echo(z.jackson.string(point, true));
        ////
        //console.echo(z.jackson.parse("{\"x\":1,\"y\":2,\"name\":\"test\"}", Point.class));
        //console.echo(z.jackson.parse("{\"x\":1,\"y\":2,\"name\":\"test\",\"group_id\":\"gg\"}", Point.class));
        //console.echo(z.jackson.parse("{\"x\":1,\"y\":2,\"name\":\"test\",\"groupId\":\"gg\"}", Point.class));
        //console.echo(z.jackson.parse("{\"x\":1,\"y\":2,\"name\":\"test\",\"notexsit\":\"gg\"}", Point.class));

        console.echo(x.json.map("{\"x\":1,\"y\":2,\"name\":\"test\",\"group_id\":\"gg\"}"));
        console.echo(x.json.read("x", "{\"x\":1,\"y\":2,\"name\":\"test\",\"group_id\":\"gg\"}"));
        console.echo(x.json.map(point));

        //console.echo(z.jackson.read("start", "{\"start\":{\"x\":1,\"y\":2},\"end\":{\"x\":3,\"y\":4}}"));
    }

    @Test
    void json() {
        console.echo(x.json.string(Lists.newArrayList(1, 2, 3)));
        console.echo(x.json.string(Lists.newArrayList(1, 2, 3), true));
        //
        Point point = new Point(1, 2, "test", LocalDateTime.of(2020, 1, 1, 1, 1, 1), "gg");
        console.echo(x.json.string(point));
        console.echo(x.json.string(point, true));
        //
        console.echo(x.json.parse("{\"x\":1,\"y\":2,\"name\":\"test\"}", Point.class));
    }

    @Test
    void mobile() {
        console.echo(x.mobile.region("13810386453"));
        console.echo(x.mobile.region("12345678901"));
    }

    @Test
    void number() {
        console.echo(x.number.format(new BigDecimal("123.123")));
        console.echo(x.number.format(new BigDecimal("0.1")));
        console.echo(x.number.format(new BigDecimal("0")));
    }

    //@Test
    //public void page() {
    //    console.echo(z.page.of(PagedList.emptyList(), Pageable.unpaged()));
    //}

    @Test
    void param() {
        console.echo(x.param.require((Line) null, 0));

        console.echo(x.param.require(() -> new Line().getStart().getX(), 0));
        console.echo(x.param.exist(() -> new Line().getStart().getX()));

        Point start = new Point();
        start.setX(1);
        Line line = new Line();
        line.setStart(start);
        //console.echo(z.param.require(line, 0));
        console.echo(x.param.require(() -> line.getStart().getX(), 0));
        console.echo(x.param.exist(() -> line.getStart().getX()));
    }

    @Test
    void pinyin() {
        console.echo(x.pinyin.convert("测试"));
    }

    @Test
    void random() {
        console.echo(x.random.number(6));
    }

    @Test
    void remote() {
        HttpServletRequest request = new MockHttpServletRequest();
        console.echo(x.remote.ip(request));
    }

    @Test
    void serial() {
        console.echo(x.serial.code());
        console.echo(x.serial.uuid());
    }

    @Test
    void string() {
        console.echo(x.string.blank(""));
        console.echo(x.string.blank("abc"));
    }

    @Test
    void replace() {
        Map<String, Object> p1 = Maps.newHashMap();
        p1.put("x", 1);
        p1.put("y", 2);
        console.echo(x.string.format("x{x},y{y}", p1));
        console.echo(x.string.format("x{},y{}", 1, 2));
    }

    @Test
    void url() {
        console.echo(x.url.of("http://www.zz.com/p1/p2/p3").addPath("/p4/p5/p6/ttt.api").addQuery("x", "1").addQuery("y", "测试").build());
        console.echo(x.url.of("http://www.zz.com/p1/p2/p3").addPath("ttt.api").addQuery("x", "1").addQuery("y", "测试").build());
        console.echo(x.url.of("http://www.zz.com/p1/p2/p3/").addPath("ttt.api").addQuery("x", "1").addQuery("y", "测试").build());
    }
}
