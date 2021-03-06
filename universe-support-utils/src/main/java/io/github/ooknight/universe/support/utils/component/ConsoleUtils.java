package io.github.ooknight.universe.support.utils.component;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import io.github.ooknight.universe.support.utils.Bean;
import static io.github.ooknight.universe.support.utils.COMBINE.U.encode;
import static io.github.ooknight.universe.support.utils.COMBINE.U.json;

import java.util.Collection;

public final class ConsoleUtils {

    //private static final Logger log = LoggerFactory.getLogger("console");

    public void echo(Collection<?> collection) {
        for (Object o : collection) {
            if (o instanceof Bean) {
                echo((Bean) o);
            } else {
                echo(o);
            }
        }
    }

    public void echo(Object o) {
        System.out.println(o);
    }

    public void echo(Bean o) {
        System.out.println(json.string(o));
    }

    public void echo(byte[] o) {
        System.out.println(encode.base64(o));
    }

    public void print(Object o) {
        System.out.println(o);
    }

    //public void debug(Collection<?> collection) {
    //    for (Object o : collection) {
    //        log.debug("\n{}", o);
    //    }
    //}
    //
    //public void debug(Object o) {
    //    log.debug("\n{}", o);
    //}
    //
    //public void debug(byte[] value) {
    //    log.debug("\n{}", new String(value));
    //}
}
