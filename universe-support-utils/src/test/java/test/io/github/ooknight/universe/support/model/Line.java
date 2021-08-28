package test.io.github.ooknight.universe.support.model;

import io.github.ooknight.universe.support.utils.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Line implements Bean {

    private Point start;
    private Point end;
    private String name;
}
