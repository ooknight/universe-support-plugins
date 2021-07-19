package io.github.ooknight.universe.support.sensitive.annotation;

import org.apache.commons.lang3.StringUtils;

public enum SensitiveType implements SensitiveHandler {
    /**
     * 中文姓名 - 只显示第一个汉字, 其他隐藏为2个星号
     * 例: 李**
     */
    CHINESE_NAME() {
        @Override
        public String handle(String value) {
            if (StringUtils.isBlank(value)) {
                return "";
            }
            final String name = StringUtils.left(value, 1);
            return StringUtils.rightPad(name, StringUtils.length(value), "*");
        }
    },
    /**
     * 身份证号 - 显示最后四位, 其他隐藏, 共计18位或者15位
     * 例: 110101********5762
     */
    ID_CARD {
        @Override
        public String handle(String value) {
            if (StringUtils.isBlank(value)) {
                return "";
            }
            return StringUtils.left(value, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(value, 3), StringUtils.length(value), "*"), "***"));
        }
    },
    /**
     * 手机号码 - 前三位, 后四位, 其他隐藏
     * 例: 138******1234
     */
    MOBILE {
        @Override
        public String handle(String value) {
            if (StringUtils.isBlank(value)) {
                return "";
            }
            return StringUtils.left(value, 2).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(value, 2), StringUtils.length(value), "*"), "***"));
        }
    },
    /**
     * 地址 - 只显示到地区, 不显示详细地址
     * 例: 北京市海淀区****
     */
    ADDRESS {
        @Override
        public String handle(String value) {
            if (StringUtils.isBlank(value)) {
                return "";
            }
            final int length = StringUtils.length(value);
            return StringUtils.rightPad(StringUtils.left(value, length - 6), length, "*");
        }
    },
    /**
     * 电子邮箱 - 邮箱前缀仅显示第一个字母, 前缀其他隐藏, 用星号代替, @及后面的地址显示
     * 例: x**@163.com
     */
    MAIL {
        @Override
        public String handle(String value) {
            if (StringUtils.isBlank(value)) {
                return "";
            }
            final int index = StringUtils.indexOf(value, "@");
            if (index <= 1) {
                return value;
            } else {
                return StringUtils.rightPad(StringUtils.left(value, 1), index, "*").concat(StringUtils.mid(value, index, StringUtils.length(value)));
            }
        }
    },
    /**
     * 银行卡号 - 前六位, 后四位, 其他用星号隐藏, 每位1个星号
     * 例: 6222600**********1234
     */
    BANK_CARD {
        @Override
        public String handle(String value) {
            if (StringUtils.isBlank(value)) {
                return "";
            }
            return StringUtils.left(value, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(value, 4), StringUtils.length(value), "*"), "******"));
        }
    }
}
