package test.io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.delegates.Action;
import io.github.ooknight.universe.support.stateful.delegates.Selector;
import io.github.ooknight.universe.support.stateful.transitions.Transition;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class DynamicTransitionActionTests {

    final private Enum StateA = State.A, StateB = State.B, StateC = State.C, TriggerX = Trigger.X, TriggerY = Trigger.Y;
    //
    private DynamicallyGotoState<P> gotoA = new DynamicallyGotoState<>(State.A);
    private DynamicallyGotoState<P> gotoB = new DynamicallyGotoState<>(State.B);

    @Test
    void UnguardedDynamicTransitionActionsArePerformed() {
        //Map<Integer, Integer> context = new HashMap<>();
        //context.put(0, -1);
        //context.put(1, -1);
        //context.put(2, -1);
        //context.put(3, -1);
        ////
        //AccumulatingAction action0 = new AccumulatingAction(context);
        //AccumulatingAction action1 = new AccumulatingAction(context);
        //AccumulatingAction action2 = new AccumulatingAction(context);
        //AccumulatingAction action3 = new AccumulatingAction(context);
        ////
        //StateMachineConfig<State, Trigger> config = new StateMachineConfig<>();
        //config.configure(State.A)
        //    .permit(Trigger.X, gotoB, action0)
        //    .permit(Trigger.Y, gotoB, action2);
        //config.configure(State.B)
        //    .permit(Trigger.X, gotoA, action1)
        //    .permit(Trigger.Y, gotoA, action3);
        ////
        //StateMachine<State, Trigger> sm = config.create(State.A);
        //sm.fire(Trigger.X);
        //sm.fire(TriggerX1, new P(1));
        //sm.fire(TriggerY2, new P(1, 2));
        //sm.fire(TriggerY3, new P(1, 2, 3));
        ////
        //assertEquals(State.A, sm.state());
        //assertEquals(Integer.valueOf(0), context.get(0));
        //assertEquals(Integer.valueOf(1), context.get(1));
        //assertEquals(Integer.valueOf(3), context.get(2));
        //assertEquals(Integer.valueOf(6), context.get(3));
    }

    @Test
    void GuardedDynamicTransitionActionsArePerformed() {
        //Map<Integer, Integer> context = new HashMap<>();
        //context.put(0, -1);
        //context.put(1, -1);
        //context.put(2, -1);
        //context.put(3, -1);
        ////
        //AccumulatingAction action0 = new AccumulatingAction(context);
        //AccumulatingAction action1 = new AccumulatingAction(context);
        //AccumulatingAction action2 = new AccumulatingAction(context);
        //AccumulatingAction action3 = new AccumulatingAction(context);
        ////
        //StateMachineConfig<State, Trigger> config = new StateMachineConfig<>();
        //config.configure(State.A)
        //    .permit(Trigger.X, gotoB, TriggerBehaviourInternalTests.returnTrue, action0)
        //    .permit(TriggerY2, gotoB, TriggerBehaviourInternalTests.returnTrue, action2);
        //config.configure(State.B)
        //    .permit(TriggerX1, gotoA, TriggerBehaviourInternalTests.returnTrue, action1)
        //    .permit(TriggerY3, gotoA, TriggerBehaviourInternalTests.returnTrue, action3);
        //StateMachine<State, Trigger> sm = config.create(State.A);
        //sm.fire(Trigger.X);
        //sm.fire(TriggerX1, new P(1));
        //sm.fire(TriggerY2, new P(1, 2));
        //sm.fire(TriggerY3, new P(1, 2, 3));
        //assertEquals(State.A, sm.state());
        //assertEquals(Integer.valueOf(0), context.get(0));
        //assertEquals(Integer.valueOf(1), context.get(1));
        //assertEquals(Integer.valueOf(3), context.get(2));
        //assertEquals(Integer.valueOf(6), context.get(3));
    }

    private class DynamicallyGotoState<T> implements Selector<Void, State> {

        private State targetState;

        public DynamicallyGotoState(State whereToGo) {
            this.targetState = whereToGo;
        }

        @Override
        public State apply(Void context) {
            return this.targetState;
        }
    }

    private class AccumulatingAction implements Action<State, Trigger, P> {

        private Map<Integer, Integer> cache;

        public AccumulatingAction(Map<Integer, Integer> cache) {
            this.cache = cache;
        }

        @Override
        public void execute(Transition<State, Trigger> transition, P context) {
            cache.put(context.size, context.p1 + context.p2 + context.p3);
        }
    }

    private class P {

        private int p1;
        private int p2;
        private int p3;
        private int size;

        public P(int p1) {
            this.p1 = p1;
            this.size = 1;
        }

        public P(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.size = 2;
        }

        public P(int p1, int p2, int p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
            this.size = 3;
        }
    }
}
