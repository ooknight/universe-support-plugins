package io.github.ooknight.universe.support.utils.component;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Collection;
import java.util.Map;

public final class StringUtils {

    public static final String EMPTY = "";
    public static final String MASK = "*";

    /*
    public static String sensitive(String value, int before, int center, int after) {
        if (value == null) {
            return null;
        }
        if (blank(value)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(value.length());
        if (before > 0) {
            sb.append("")
        }
    }
    */

    public boolean startWithAny(CharSequence str, Collection<? extends CharSequence> prefixes) {
        if (StrUtil.isEmpty(str) || ArrayUtil.isEmpty(prefixes)) {
            return false;
        }
        for (CharSequence t : prefixes) {
            if (StrUtil.startWith(str, t)) {
                return true;
            }
        }
        return false;
    }

    public boolean blank(String value) {
        return false;
    }

    public String brief(String value, int length) {
        return StrUtil.brief(value, length);
    }

    public String format(String template, Map<String, ?> args) {
        return StrUtil.format(template, args);
    }

    public String format(String template, Object... args) {
        return StrUtil.format(template, args);
        //template = String.valueOf(template); // null -> "null"
        //StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        //int templateStart = 0;
        //int i = 0;
        //while (i < args.length) {
        //    int placeholderStart = template.indexOf("{}", templateStart);
        //    if (placeholderStart == -1) {
        //        break;
        //    }
        //    builder.append(template, templateStart, placeholderStart);
        //    builder.append(args[i++]);
        //    templateStart = placeholderStart + 2;
        //}
        //builder.append(template, templateStart, template.length());
        //// if we run out of placeholders, append the extra args in square braces
        //if (i < args.length) {
        //    builder.append(" [");
        //    builder.append(args[i++]);
        //    while (i < args.length) {
        //        builder.append(", ");
        //        builder.append(args[i++]);
        //    }
        //    builder.append(']');
        //}
        //return builder.toString();
    }
}
