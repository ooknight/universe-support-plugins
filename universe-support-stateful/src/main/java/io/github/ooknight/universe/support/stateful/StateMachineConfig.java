package io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.triggers.TriggerBehaviour;
import io.github.ooknight.universe.support.stateful.triggers.TriggerBehaviourTransitioning;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The state machine configuration. Reusable.
 */
public class StateMachineConfig<S, T, C> {

    private final Map<S, StateRepresentation<S, T, C>> map = new HashMap<>();
    /**
     * Added in 2.5.2.
     * Default MUST be false for backward compatibility reasons. Prior to 2.5.2,
     * entering the initial state never fires its entry action.
     */
    private boolean entryActionOfInitialStateEnabled = false;

    /**
     * Enables the state machine to execute the entry action of the initial state
     * when the state machine starts.
     * This configuration is disabled by default.
     */
    public void enableEntryActionOfInitialState() {
        this.entryActionOfInitialStateEnabled = true;
    }

    /**
     * Disables the state machine to execute the entry action of the initial state
     * when the state machine starts.
     * This is the default.
     */
    public void disableEntryActionOfInitialState() {
        this.entryActionOfInitialStateEnabled = false;
    }

    /**
     * Begin configuration of the entry/exit actions and allowed transitions
     * when the state machine is in a particular state
     *
     * @param state The state to configure
     * @return A configuration object through which the state can be configured
     */
    public StateConfiguration<S, T, C> configure(S state) {
        return new StateConfiguration<>(getOrCreateRepresentation(state));
    }

    public StateMachine<S, T, C> create(S state, C context) {
        return new StateMachine<>(state, context, this);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public StateMachine<S, T, Void> create(S state) {
        return new StateMachine(state, null, this);
    }

    public void export(OutputStream dotFile) throws IOException {
        export(dotFile, false);
    }

    public void export(OutputStream dotFile, boolean printLabels) throws IOException {
        try (OutputStreamWriter w = new OutputStreamWriter(dotFile, StandardCharsets.UTF_8)) {
            PrintWriter writer = new PrintWriter(w);
            writer.write("digraph G {\n");
            for (Map.Entry<S, StateRepresentation<S, T, C>> entry : this.map.entrySet()) {
                Map<T, List<TriggerBehaviour<S, T, C>>> behaviours = entry.getValue().getTriggerBehaviours();
                for (Map.Entry<T, List<TriggerBehaviour<S, T, C>>> behaviour : behaviours.entrySet()) {
                    for (TriggerBehaviour<S, T, C> triggerBehaviour : behaviour.getValue()) {
                        if (triggerBehaviour instanceof TriggerBehaviourTransitioning) {
                            S destination = triggerBehaviour.transitionsTo(null, null);
                            if (printLabels) {
                                writer.write(String.format("\t%s -> %s [label = \"%s\" ];\n", entry.getKey(), destination, triggerBehaviour.getTrigger()));
                            } else {
                                writer.write(String.format("\t%s -> %s;\n", entry.getKey(), destination));
                            }
                        }
                    }
                }
            }
            writer.write("}");
        }
    }

    /**
     * Return StateRepresentation for the specified state. May return null.
     *
     * @param state The state
     * @return StateRepresentation for the specified state, or null.
     */
    StateRepresentation<S, T, C> getRepresentation(S state) {
        return map.get(state);
    }

    /**
     * Gets whether the entry action of the initial state of the state machine
     * must be executed when the state machine starts.
     * Default is false for backward compatibility sake.
     * <p>
     * Added in 2.5.2
     *
     * @return true if the entry action of the initial state of the state machine
     * must be executed when the state machine starts.
     */
    boolean isEntryActionOfInitialStateEnabled() {
        return entryActionOfInitialStateEnabled;
    }

    /**
     * Return StateRepresentation for the specified state. Creates representation if it does not exist.
     *
     * @param state The state
     * @return StateRepresentation for the specified state.
     */
    private StateRepresentation<S, T, C> getOrCreateRepresentation(S state) {
        StateRepresentation<S, T, C> result = map.get(state);
        if (result == null) {
            result = new StateRepresentation<>(state);
            map.put(state, result);
        }
        return result;
    }
}
