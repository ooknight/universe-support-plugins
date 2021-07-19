package test.io.github.ooknight.universe.support.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {

    private Point start;
    private Point end;
    private String name;
}
