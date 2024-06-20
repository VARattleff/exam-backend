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
public class ReqResultDTO {
    private Long participantId;
    private Long disciplineId;
    private LocalDate resultDate;
    private Integer hours;
    private Integer minutes;
    private Integer seconds;
    private Integer hundredths;
    private Integer meters;
    private Integer centimeters;
    private Integer points;
}
