package io.github.ooknight.universe.support.utils.component;

import java.security.SecureRandom;

public final class RandomUtils {

    private final SecureRandom random = new SecureRandom();

    public String number(int length) {
        if (length < 1 || length > 10) {
            throw new IllegalArgumentException("cannot random " + length + " bit number");
        }
        if (length == 1) {
            return String.valueOf(random.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[length];
        for (int i = 0; i < length; i++) {
            while (true) {
                int k = random.nextInt(10);
                if ((bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char) (k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }
}
