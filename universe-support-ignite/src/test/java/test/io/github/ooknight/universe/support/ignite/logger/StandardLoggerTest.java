package test.io.github.ooknight.universe.support.ignite.logger;

import io.github.ooknight.universe.support.ignite.logger.StandardLogger;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

public class StandardLoggerTest {

    @Test
    void test1() {
        StandardLogger logger = new StandardLogger();
        System.out.println(logger);
    }

    @Test
    void test2() {
        StandardLogger logger = new StandardLogger(LoggerFactory.getLogger(StandardLoggerTest.class));
        System.out.println(logger);
    }
}
