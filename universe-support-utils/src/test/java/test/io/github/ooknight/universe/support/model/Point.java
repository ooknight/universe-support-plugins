package test.io.github.ooknight.universe.support.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    private Integer x;
    private Integer y;
    private String name;
    private LocalDateTime time;
    private String groupId;
}
