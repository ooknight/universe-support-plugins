package test.io.github.ooknight.universe.support.model;

import io.github.ooknight.universe.support.utils.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Bean {

    private Integer x;
    private Integer y;
    private String name;
    private LocalDateTime time;
    private String group;
}
