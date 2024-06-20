package test.exambackend.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultDTO {
    private Long id;
    private Long disciplineId;
    private String result;
    private String date;
    private String location;
    private String competition;
    private String comment;
}
