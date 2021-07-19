package test.io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.transitions.Transition;

import org.junit.jupiter.api.Test;

public class StateRepresentationTests {

    Transition<State, Trigger> actualTransition = null;
    boolean executed = false;
    int order = 0, subOrder = 0, superOrder = 0;

    @Test
    public void UponEntering_EnteringActionsExecuted() {
        //StateRepresentation<State, Trigger> stateRepresentation = CreateRepresentation(State.B);
        //Transition<State, Trigger> transition = new Transition<>(State.A, State.B, Trigger.X);
        //actualTransition = null;
        //stateRepresentation.addEntryAction(new Action2<Transition<State, Trigger>, Object[]>() {
        //    @Override
        //    public void execute(Transition<State, Trigger> t, Object[] a) {
        //        actualTransition = t;
        //    }
        //});
        //stateRepresentation.enter(transition);
        //assertEquals(transition, actualTransition);
    }

    @Test
    public void UponLeaving_EnteringActionsNotExecuted() {
        //StateRepresentation<State, Trigger> stateRepresentation = CreateRepresentation(State.B);
        //Transition<State, Trigger> transition = new Transition<>(State.A, State.B, Trigger.X);
        //actualTransition = null;
        //stateRepresentation.addEntryAction(new Action2<Transition<State, Trigger>, Object[]>() {
        //    @Override
        //    public void execute(Transition<State, Trigger> t, Object[] a) {
        //        actualTransition = t;
        //    }
        //});
        //stateRepresentation.exit(transition);
        //assertNull(actualTransition);
    }

    @Test
    public void UponLeaving_LeavingActionsExecuted() {
        //StateRepresentation<State, Trigger> stateRepresentation = CreateRepresentation(State.A);
        //Transition<State, Trigger> transition = new Transition<>(State.A, State.B, Trigger.X);
        //actualTransition = null;
        //stateRepresentation.addExitAction(new Action1<Transition<State, Trigger>>() {
        //    @Override
        //    public void execute(Transition<State, Trigger> t) {
        //        actualTransition = t;
        //    }
        //});
        //stateRepresentation.exit(transition);
        //assertEquals(transition, actualTransition);
    }

    @Test
    public void UponEntering_LeavingActionsNotExecuted() {
        //StateRepresentation<State, Trigger> stateRepresentation = CreateRepresentation(State.A);
        //Transition<State, Trigger> transition = new Transition<>(State.A, State.B, Trigger.X);
        //actualTransition = null;
        //stateRepresentation.addExitAction(new Action1<Transition<State, Trigger>>() {
        //    @Override
        //    public void execute(Transition<State, Trigger> t) {
        //        actualTransition = t;
        //    }
        //});
        //stateRepresentation.enter(transition);
        //assertNull(actualTransition);
    }

    @Test
    public void IncludesUnderlyingState() {
        //StateRepresentation<State, Trigger> stateRepresentation = CreateRepresentation(State.B);
        //assertTrue(stateRepresentation.includes(State.B));
    }

    @Test
    public void DoesNotIncludeUnrelatedState() {
        //StateRepresentation<State, Trigger> stateRepresentation = CreateRepresentation(State.B);
        //assertFalse(stateRepresentation.includes(State.C));
    }

    @Test
    public void IsIncludedInUnderlyingState() {
        //StateRepresentation<State, Trigger> stateRepresentation = CreateRepresentation(State.B);
        //assertTrue(stateRepresentation.includes(State.B));
    }

    @Test
    public void IsNotIncludedInUnrelatedState() {
        //StateRepresentation<State, Trigger> stateRepresentation = CreateRepresentation(State.B);
        //assertFalse(stateRepresentation.includes(State.C));
    }

    @Test
    public void EntryActionsExecuteInOrder() {
        //final ArrayList<Integer> actual = new ArrayList<>();
        //StateRepresentation<State, Trigger> rep = CreateRepresentation(State.B);
        //rep.addEntryAction(new Action2<Transition<State, Trigger>, Object[]>() {
        //    @Override
        //    public void execute(Transition<State, Trigger> arg1, Object[] arg2) {
        //        actual.add(0);
        //    }
        //});
        //rep.addEntryAction(new Action2<Transition<State, Trigger>, Object[]>() {
        //    @Override
        //    public void execute(Transition<State, Trigger> arg1, Object[] arg2) {
        //        actual.add(1);
        //    }
        //});
        //rep.enter(new Transition<>(State.A, State.B, Trigger.X));
        //assertEquals(2, actual.size());
        //assertEquals(0, actual.get(0).intValue());
        //assertEquals(1, actual.get(1).intValue());
    }

    @Test
    public void ExitActionsExecuteInOrder() {
        //final List<Integer> actual = new ArrayList<>();
        //StateRepresentation<State, Trigger> rep = CreateRepresentation(State.B);
        //rep.addExitAction(new Action1<Transition<State, Trigger>>() {
        //    @Override
        //    public void execute(Transition<State, Trigger> arg1) {
        //        actual.add(0);
        //    }
        //});
        //rep.addExitAction(new Action1<Transition<State, Trigger>>() {
        //    @Override
        //    public void execute(Transition<State, Trigger> arg1) {
        //        actual.add(1);
        //    }
        //});
        //rep.exit(new Transition<>(State.B, State.C, Trigger.X));
        //assertEquals(2, actual.size());
        //assertEquals(0, actual.get(0).intValue());
        //assertEquals(1, actual.get(1).intValue());
    }

    @Test
    public void WhenTransitionExists_TriggerCanBeFired() {
        //StateRepresentation<State, Trigger> rep = CreateRepresentation(State.B);
        //assertFalse(rep.canHandle(Trigger.X));
    }

    @Test
    public void WhenTransitionExistsButGuardConditionNotMet_TriggerCanBeFired() {
        //StateRepresentation<State, Trigger> rep = CreateRepresentation(State.B);
        //rep.addTriggerBehaviour(new TriggerBehaviourInternal<State, Trigger>(
        //    Trigger.X, TriggerBehaviourInternalTests.returnFalse, TriggerBehaviourInternalTests.nopAction));
        //assertFalse(rep.canHandle(Trigger.X));
    }

    @Test
    public void WhenTransitionDoesNotExist_TriggerCannotBeFired() {
        //StateRepresentation<State, Trigger> rep = CreateRepresentation(State.B);
        //rep.addTriggerBehaviour(new TriggerBehaviourInternal<State, Trigger>(
        //    Trigger.X, TriggerBehaviourInternalTests.returnTrue, TriggerBehaviourInternalTests.nopAction));
        //assertTrue(rep.canHandle(Trigger.X));
    }
    //StateRepresentation<State, Trigger> CreateRepresentation(State state) {
    //    return new StateRepresentation<>(state);
    //}
}
