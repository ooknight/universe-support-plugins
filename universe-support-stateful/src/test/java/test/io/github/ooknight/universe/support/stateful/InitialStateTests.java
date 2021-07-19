package test.io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.StateMachine;
import io.github.ooknight.universe.support.stateful.StateMachineConfig;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InitialStateTests {

    private boolean executed = false;

    @Test
    void testInitialStateEntryActionNotExecuted() {
        final State initial = State.B;
        StateMachineConfig<State, Trigger, Void> config = config(initial);
        StateMachine<State, Trigger, Void> sm = config.create(initial);
        assertEquals(initial, sm.state());
        assertFalse(executed);
    }

    @Test
    void testInitialStateEntryActionNotExecutedIfDisabled() {
        final State initial = State.B;
        StateMachineConfig<State, Trigger, Void> config = config(initial);
        config.disableEntryActionOfInitialState();
        StateMachine<State, Trigger, Void> sm = config.create(initial);
        assertEquals(initial, sm.state());
        assertFalse(executed);
    }

    @Test
    void testInitialStateEntryActionExecuted() {
        final State initial = State.B;
        StateMachineConfig<State, Trigger, Void> config = config(initial);
        config.enableEntryActionOfInitialState();
        StateMachine<State, Trigger, Void> sm = config.create(initial);
        assertEquals(initial, sm.state());
        assertTrue(executed);
    }

    private StateMachineConfig<State, Trigger, Void> config(State initial) {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        config.configure(initial)
            .onEntry((transition, context) -> executed = true);
        return config;
    }

    @Test
    void testInitialStateEntryActionWithParameterNotExecuted() {
        //final State initial = State.B;
        //StateMachineConfig<State, Trigger> config = new StateMachineConfig<>();
        //TriggerWithParameters1<Object, Trigger> trigger =
        //    config.setTriggerParameters(Trigger.X, Object.class);
        //config.configure(initial)
        //    .onEntryFrom(
        //        Trigger.X,
        //        new Action1<Object>() {
        //            @Override
        //            void execute(Object arg1) {
        //                executed = true;
        //            }
        //        },
        //        Object.class
        //    );
        //config.enableEntryActionOfInitialState();
        //StateMachine<State, Trigger> sm = new StateMachine<>(initial, config);
        //assertEquals(initial, sm.state());
        //assertFalse(executed);
    }
}
