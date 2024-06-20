// ResultDTO.java
package test.exambackend.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.exambackend.discipline.ResultsType;
import test.exambackend.participant.AgeGroup;
import test.exambackend.participant.Gender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResResultDTO {
    private long id;
    private String disciplineName;
    private ResultsType resultsType;
    private String resultValue;
    private LocalDate resultDate;
    private String participantName;
    private AgeGroup ageGroup;
    private Gender gender;
    private String adjacentClub;
}