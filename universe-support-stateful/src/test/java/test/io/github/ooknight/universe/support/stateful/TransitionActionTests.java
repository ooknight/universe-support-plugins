package test.io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.StateMachine;
import io.github.ooknight.universe.support.stateful.StateMachineConfig;
import io.github.ooknight.universe.support.stateful.delegates.Action;
import io.github.ooknight.universe.support.stateful.transitions.Transition;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static test.io.github.ooknight.universe.support.stateful.Helper.RETURN_FALSE;
import static test.io.github.ooknight.universe.support.stateful.Helper.RETURN_TRUE;

public class TransitionActionTests {

    @Test
    void UnguardedActionIsPerformed() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        TripwireAction action = new TripwireAction();
        config.configure(State.A)
            .permit(Trigger.Z, State.B, action);
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        sm.fire(Trigger.Z);
        assertEquals(State.B, sm.state());
        assertTrue(action.wasPerformed());
    }

    @Test
    void TransitionActionIsPerformedBetweenExitAndEntry() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        List<Integer> list = new ArrayList<>();
        Action<State, Trigger, Void> exitAction = new CountingAction(list, 1);
        Action<State, Trigger, Void> transitionAction = new CountingAction(list, 2);
        Action<State, Trigger, Void> entryAction = new CountingAction(list, 3);
        config.configure(State.A)
            .onExit(exitAction)
            .permit(Trigger.Z, State.B, transitionAction);
        config.configure(State.B)
            .onEntry(entryAction);
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        sm.fire(Trigger.Z);
        assertEquals(State.B, sm.state());
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test
    void ActionWithPositiveGuardIsPerformed() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        TripwireAction action = new TripwireAction();
        config.configure(State.A)
            .permit(Trigger.X, State.C, RETURN_TRUE(Void.class), action);
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        sm.fire(Trigger.X);
        assertEquals(State.C, sm.state());
        assertTrue(action.wasPerformed());
    }

    @Test
    void ActionWithNegativeGuardIsNotPerformed() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        TripwireAction action = new TripwireAction();
        config.configure(State.A)
            .permit(Trigger.X, State.C, RETURN_FALSE(Void.class), action);
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        //sm.fire(Trigger.X);
        assertThrows(IllegalStateException.class, () -> sm.fire(Trigger.X));
    }

    @Test
    void ActionWithCorrectGuardIsPerformed() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        TripwireAction correctAction = new TripwireAction();
        TripwireAction wrongAction = new TripwireAction();
        config.configure(State.A)
            .permit(Trigger.X, State.B, RETURN_FALSE(Void.class), wrongAction)
            .permit(Trigger.X, State.C, RETURN_TRUE(Void.class), correctAction);
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        sm.fire(Trigger.X);
        assertEquals(State.C, sm.state());
        assertTrue(correctAction.wasPerformed());
        assertFalse(wrongAction.wasPerformed());
    }

    @Test
    void UnguardedActionIsPerformedOnReentry() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        TripwireAction action = new TripwireAction();
        config.configure(State.A)
            .reentry(Trigger.Z, action);
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        sm.fire(Trigger.Z);
        assertEquals(State.A, sm.state());
        assertTrue(action.wasPerformed());
    }

    @Test
    void ReentryActionIsPerformedBetweenExitAndEntry() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        List<Integer> list = new ArrayList<>();
        Action<State, Trigger, Void> entryAction = new CountingAction(list, 3);
        Action<State, Trigger, Void> transitionAction = new CountingAction(list, 2);
        Action<State, Trigger, Void> exitAction = new CountingAction(list, 1);
        config.configure(State.A)
            .onEntry(entryAction)
            .onExit(exitAction)
            .reentry(Trigger.Z, transitionAction);
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        sm.fire(Trigger.Z);
        assertEquals(State.A, sm.state());
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test
    void ActionWithPositiveGuardIsPerformedOnReentry() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        TripwireAction action = new TripwireAction();
        config.configure(State.A)
            .reentry(Trigger.X, RETURN_TRUE(Void.class), action);
        StateMachine<State, Trigger, Void> sm = config.create(State.A);
        sm.fire(Trigger.X);
        assertEquals(State.A, sm.state());
        assertTrue(action.wasPerformed());
    }

    private static class TripwireAction implements Action<State, Trigger, Void> {

        private boolean beenThere;

        public TripwireAction() {
            beenThere = false;
        }

        public boolean wasPerformed() {
            return beenThere;
        }

        @Override
        public void execute(Transition<State, Trigger> transition, Void context) {
            beenThere = true;
        }
    }

    @SuppressWarnings("FieldMayBeFinal")
    private static class CountingAction implements Action<State, Trigger, Void> {

        private List<Integer> numbers;
        private Integer number;

        public CountingAction(List<Integer> numbers, Integer number) {
            this.numbers = numbers;
            this.number = number;
        }

        @Override
        public void execute(Transition<State, Trigger> transition, Void context) {
            numbers.add(this.number);
        }
    }
}
