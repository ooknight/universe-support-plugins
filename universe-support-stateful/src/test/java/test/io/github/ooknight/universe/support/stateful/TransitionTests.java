package test.io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.transitions.Transition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransitionTests {

    @Test
    void IdentityTransitionIsNotChange() {
        Transition<Integer, Integer> t = new Transition<>(1, 1, 0);
        assertTrue(t.isReentry());
    }

    @Test
    void TransitioningTransitionIsChange() {
        Transition<Integer, Integer> t = new Transition<>(1, 2, 0);
        assertFalse(t.isReentry());
    }
}
