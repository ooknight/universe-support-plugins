package io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.delegates.UnhandledTriggerAction;

class Constant {

    static <S, T, C> UnhandledTriggerAction<S, T, C> DEFAULT_UNHANDLED_TRIGGER_ACTION() {
        return (state, trigger, context) -> {
            throw new IllegalStateException(
                String.format("No valid leaving transitions are permitted from state '%s' for trigger '%s' (context:%s). Consider ignoring the trigger.", state, trigger, context)
            );
        };
    }
}
