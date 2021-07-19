package test.io.github.ooknight.universe.support.utils;

import test.io.github.ooknight.universe.support.model.Line;
import test.io.github.ooknight.universe.support.model.Point;
import io.github.ooknight.universe.support.utils.CryptoUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static oo.UTILS.U.console;
import static oo.UTILS.U.digest;
import static oo.UTILS.x;

public class UnitTest {

    @Test
    void rsa() {
        CryptoUtils.RSA rsa = x.crypto.rsa();
        //
        byte[] r1 = rsa.encryptByPrivateKey("abc".getBytes());
        byte[] r2 = rsa.decryptByPublicKey(r1);
        //Assertions.assertEquals("xxxx", );
        console.echo(r1);
        console.echo(r2);
        //
        byte[] r3 = rsa.encryptByPublicKey("abc".getBytes());
        byte[] r4 = rsa.decryptByPrivateKey(r3);
        console.echo(r3);
        console.echo(r4);
    }

    @Test
    void aes() {
        CryptoUtils.AES aes = x.crypto.aes();
        byte[] r1 = aes.encrypt("abc".getBytes());
        byte[] r2 = aes.decrypt(r1);
        console.echo(r1);
        console.echo(r2);
    }

    @Test
    void datetime() {
        console.echo(x.datetime.timestamp());
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

        console.echo(x.jackson.map("{\"x\":1,\"y\":2,\"name\":\"test\",\"group_id\":\"gg\"}"));
        console.echo(x.jackson.read("x", "{\"x\":1,\"y\":2,\"name\":\"test\",\"group_id\":\"gg\"}"));
        console.echo(x.jackson.map(point));

        //console.echo(z.jackson.read("start", "{\"start\":{\"x\":1,\"y\":2},\"end\":{\"x\":3,\"y\":4}}"));
    }

    @Test
    void json() {
        console.echo(x.json.string(Lists.newArrayList(1, 2, 3)));
        console.echo(x.json.string(Lists.newArrayList(1, 2, 3), true));
        //
        Point point = new Point(1, 2, "test", LocalDateTime.of(2020, 1, 1, 1, 1, 1), "gg");
        console.echo(x.jackson.string(point));
        console.echo(x.jackson.string(point, true));
        //
        console.echo(x.jackson.parse("{\"x\":1,\"y\":2,\"name\":\"test\"}", Point.class));
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
