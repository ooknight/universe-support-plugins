package io.github.ooknight.universe.support.ebean;

import io.ebean.config.SlowQueryEvent;
import io.ebean.config.SlowQueryListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingSlowQueryListener implements SlowQueryListener {

    private static final Logger logger = LoggerFactory.getLogger(SlowQueryListener.class);

    @Override
    public void process(SlowQueryEvent event) {
        logger.warn("SLOW : [{}] : {}", event.getTimeMillis(), event.getSql());
    }
}
