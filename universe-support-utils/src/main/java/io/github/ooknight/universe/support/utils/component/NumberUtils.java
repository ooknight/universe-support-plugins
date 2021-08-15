package io.github.ooknight.universe.support.utils.component;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public final class NumberUtils {

    public String format(BigDecimal value) {
        if (value == null) {
            return StringUtils.EMPTY;
        }
        DecimalFormat formatter = new DecimalFormat("0.00");
        return formatter.format(value);
    }
}
