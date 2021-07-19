package test.io.github.ooknight.universe.support.stateful.graphviz;

import test.io.github.ooknight.universe.support.stateful.State;
import test.io.github.ooknight.universe.support.stateful.Trigger;
import io.github.ooknight.universe.support.stateful.StateMachineConfig;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphGenerationTests {

    @Test
    public void GeneratesDotFileForGraph() throws IOException {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        config.configure(State.A)
            .permit(Trigger.X, State.B)
            .permit(Trigger.Y, State.C);
        config.configure(State.B)
            .permit(Trigger.Y, State.C);
        config.configure(State.C)
            .permit(Trigger.X, State.A);
        ByteArrayOutputStream dotFile = new ByteArrayOutputStream();
        config.export(dotFile);
        String actual = new String(dotFile.toByteArray(), StandardCharsets.UTF_8);
        assertTrue(actual.contains("digraph G"));
        assertTrue(actual.contains("\tC -> A;\n"));
        assertTrue(actual.contains("\tB -> C;\n"));
        assertTrue(actual.contains("\tA -> C;\n"));
        assertTrue(actual.contains("\tA -> B;\n"));
        Files.write(Paths.get("e:\\test.png"), dotFile.toByteArray());
    }

    @Test
    public void GeneratesDotFileForGraphWithLabels() throws IOException {
        StateMachineConfig<State, Trigger, Void> config = new StateMachineConfig<>();
        config.configure(State.A)
            .permit(Trigger.X, State.B)
            .permit(Trigger.Y, State.C);
        config.configure(State.B)
            .permit(Trigger.Y, State.C);
        config.configure(State.C)
            .permit(Trigger.X, State.A);
        ByteArrayOutputStream dotFile = new ByteArrayOutputStream();
        config.export(dotFile, true);
        String actual = new String(dotFile.toByteArray(), StandardCharsets.UTF_8);
        assertTrue(actual.contains("digraph G"));
        assertTrue(actual.contains("\tA -> C [label = \"Y\" ];\n"));
        assertTrue(actual.contains("\tA -> B [label = \"X\" ];\n"));
        assertTrue(actual.contains("\tC -> A [label = \"X\" ];\n"));
        assertTrue(actual.contains("\tB -> C [label = \"Y\" ];\n"));
    }
}
