package test.io.github.ooknight.universe.support.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Line implements Serializable {

    private Point start;
    private Point end;
    private String name;
}
