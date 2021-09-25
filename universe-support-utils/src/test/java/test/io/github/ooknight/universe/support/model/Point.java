package test.io.github.ooknight.universe.support.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Serializable {

    private Integer x;
    private Integer y;
    private String name;
    private LocalDateTime time;
    private String group;
}
