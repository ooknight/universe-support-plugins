package test.io.github.ooknight.universe.support.sensitive;

import io.github.ooknight.universe.support.sensitive.annotation.Sensitive;
import io.github.ooknight.universe.support.sensitive.annotation.SensitiveType;
import static io.github.ooknight.universe.support.utils.COMBINE.x;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class Demo {

    @Test
    void test() throws Exception {
        UnitEntity entity = new UnitEntity();
        entity.setId(1L);
        entity.setName("测试一下");
        entity.setMobile("12345678901");
        entity.setAddress("北京市朝阳区朝外大街赚赚科技");
        entity.setIdcard("123456123456781234");
        entity.setBankcard("1234567890123456");
        entity.setMail("test@test.com");
        entity.setMemo("不需要脱敏");
        ObjectMapper om = new ObjectMapper();
        String result = om.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
        x.console.echo(result);
    }

    @Data
    private static class UnitEntity {
        //
        private Long id;
        // 姓名
        @Sensitive(SensitiveType.CHINESE_NAME)
        private String name;
        // 手机号
        @Sensitive(SensitiveType.MOBILE)
        private String mobile;
        // 地址
        @Sensitive(SensitiveType.ADDRESS)
        private String address;
        // 身份证号
        @Sensitive(SensitiveType.ID_CARD)
        private String idcard;
        // 银行卡号
        @Sensitive(SensitiveType.BANK_CARD)
        private String bankcard;
        // 邮箱
        @Sensitive(SensitiveType.MAIL)
        private String mail;
        //
        private String memo;
    }
}
