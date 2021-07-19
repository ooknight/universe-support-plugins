package test.io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.StateMachine;
import io.github.ooknight.universe.support.stateful.StateMachineConfig;
import io.github.ooknight.universe.support.stateful.delegates.Selector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicTriggerTests {

    @Test
    void DestinationStateIsDynamic() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        config.configure(State.A).permit(Trigger.X, new Selector<Void, State>() {
            @Override
            public State apply(Void context) {
                return State.B;
            }
        });
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        sm.fire(Trigger.X);
        assertEquals(State.B, sm.state());
    }

    @Test
    void DestinationStateIsCalculatedBasedOnTriggerParameters() {
        StateMachineConfig<State, Trigger, Integer> config = new StateMachineConfig<>();
        config.configure(State.A).permit(Trigger.X, new Selector<Integer, State>() {
            @Override
            public State apply(Integer i) {
                return i == 1 ? State.B : State.C;
            }
        });
        StateMachine<State, Trigger, Integer> sm = config.create(State.A, 1);
        sm.fire(Trigger.X);
        //sm.fire(Trigger.X);
        assertEquals(State.B, sm.state());
    }

    @Test
    void DestinationStateIsCalculatedBasedOnTriggerParameters2() {
        StateMachineConfig<State, Trigger, Integer> config = new StateMachineConfig<>();
        config.configure(State.A).permit(Trigger.X, new Selector<Integer, State>() {
            @Override
            public State apply(Integer context) {
                return State.C;
            }
        });
        StateMachine<State, Trigger, Integer> sm = config.create(State.A, 1);
        //sm.fire(Trigger.X, 1);
        sm.fire(Trigger.X);
        assertEquals(State.C, sm.state());
    }
}
