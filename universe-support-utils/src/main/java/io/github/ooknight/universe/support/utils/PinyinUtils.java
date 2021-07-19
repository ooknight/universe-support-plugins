package io.github.ooknight.universe.support.utils;

import com.rnkrsoft.bopomofo4j.Bopomofo4j;
import com.rnkrsoft.bopomofo4j.ToneType;

public final class PinyinUtils {

    static {
        Bopomofo4j.local();
    }

    public String convert(String text) {
        return Bopomofo4j.pinyin(text, ToneType.WITHOUT_TONE, false, true, "");
    }
}
