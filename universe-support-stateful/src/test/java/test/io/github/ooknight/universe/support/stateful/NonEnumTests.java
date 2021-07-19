package test.io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.StateMachine;
import io.github.ooknight.universe.support.stateful.StateMachineConfig;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NonEnumTests {

    private static final String StateA = "StateA";
    private static final String StateB = "StateB";
    private static final String StateC = "StateC";
    private static final String TriggerX = "TriggerX";
    private static final String TriggerY = "TriggerY";

    @Test
    void CanUseReferenceTypeMarkers() {
        runSimpleTest(new String[]{StateA, StateB, StateC}, new String[]{TriggerX, TriggerY});
    }

    @Test
    void CanUseValueTypeMarkers() {
        runSimpleTest(State.values(), Trigger.values());
    }

    private <S, T> void runSimpleTest(S[] states, T[] transitions) {
        S a = states[0];
        S b = states[1];
        T x = transitions[0];
        StateMachineConfig<S, T, Void> config = new StateMachineConfig<>();
        config.configure(a)
            .permit(x, b);
        StateMachine<S, T, Void> sm = config.create(a);
        sm.fire(x);
        assertEquals(b, sm.state());
    }
}
