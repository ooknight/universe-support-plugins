package test.io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.StateMachine;
import io.github.ooknight.universe.support.stateful.StateMachineConfig;
import io.github.ooknight.universe.support.stateful.delegates.Action;
import io.github.ooknight.universe.support.stateful.delegates.UnhandledTriggerAction;
import io.github.ooknight.universe.support.stateful.transitions.Transition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateMachineTests {

    private static final Enum StateA = State.A, StateB = State.B, StateC = State.C, TriggerX = Trigger.X, TriggerY = Trigger.Y;
    private boolean fired = false;
    private String entryArgS = null;

    @Test
    public void CanUseReferenceTypeMarkers() {
        runSimpleTest(new Enum[]{StateA, StateB, StateC}, new Enum[]{TriggerX, TriggerY});
    }

    @Test
    public void CanUseValueTypeMarkers() {
        runSimpleTest(State.values(), Trigger.values());
    }

    private <S extends Enum, T extends Enum> void runSimpleTest(S[] states, T[] transitions) {
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

    @Test
    public void InitialStateIsCurrent() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        State initial = State.B;
        StateMachine<State, Trigger, Void> sm = config.create(initial);
        assertEquals(initial, sm.state());
    }

    @Test
    public void AcceptedTriggersRespectGuards() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        config.configure(State.B)
            .permit(Trigger.X, State.A, Helper.RETURN_FALSE(Void.class));
        StateMachine<State, Trigger, Void> sm = config.create(State.B);
        assertEquals(0, sm.getPermittedTriggers(null).size());
    }

    @Test
    public void WhenDiscriminatedByGuard_ChoosesPermittedTransition() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        config.configure(State.B)
            .permit(Trigger.X, State.A, Helper.RETURN_FALSE(Void.class))
            .permit(Trigger.X, State.C, Helper.RETURN_TRUE(Void.class));
        StateMachine<State, Trigger, Void> sm = config.create(State.B);
        sm.fire(Trigger.X);
        assertEquals(State.C, sm.state());
    }

    private void setFired() {
        fired = true;
    }

    @Test
    public void WhenTriggerIsIgnored_ActionsNotExecuted() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        config.configure(State.B)
            .onEntry(new Action<State, Trigger, Void>() {
                @Override
                public void execute(Transition<State, Trigger> transition, Void context) {
                    setFired();
                }
            })
            .ignore(Trigger.X);
        fired = false;
        StateMachine<State, Trigger, Void> sm = config.create(State.B);
        sm.fire(Trigger.X);
        assertFalse(fired);
    }

    @Test
    public void WhenUnhandledTriggerisFired_UnhandledTriggerActionIsCalled() {
        StateMachineConfig<State, Trigger, String> config = new StateMachineConfig<>();
        config.configure(State.B);
        fired = false;
        entryArgS = null;
        StateMachine<State, Trigger, String> sm = config.create(State.B, "ppp");
        sm.onUnhandledTrigger(new UnhandledTriggerAction<State, Trigger, String>() {
            @Override
            public void handle(State trigger, Trigger state, String context) {
                fired = true;
                entryArgS = context;
            }
        });
        sm.fire(Trigger.X);
        assertTrue(fired);
        assertEquals(entryArgS, "ppp");
    }

    @Test
    public void IfSelfTransitionPermitted_ActionsFire() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        config.configure(State.B)
            .onEntry(new Action<State, Trigger, Void>() {
                @Override
                public void execute(Transition<State, Trigger> transition, Void context) {
                    setFired();
                }
            })
            .reentry(Trigger.X);
        fired = false;
        StateMachine<State, Trigger, Void> sm = config.create(State.B);
        sm.fire(Trigger.X);
        assertTrue(fired);
    }

    @Test
    public void ImplicitReentryIsDisallowed() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        StateMachine<State, Trigger, Void> sm = config.create(State.B);
        //config.configure(State.B).permit(Trigger.X, State.B);
        assertThrows(IllegalStateException.class, () -> config.configure(State.B).permit(Trigger.X, State.B));
    }

    @Test
    public void TriggerParametersAreImmutableOnceSet() {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        StateMachine<State, Trigger, Void> sm = config.create(State.B);
        //sm.fire(Trigger.X);
        assertThrows(IllegalStateException.class, () -> sm.fire(Trigger.X));
    }
}
