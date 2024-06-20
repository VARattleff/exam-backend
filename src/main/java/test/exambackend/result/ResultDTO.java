package test.exambackend.result;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.exambackend.discipline.DisciplineDTO;
import test.exambackend.participant.ParticipantDTO;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ResultDTO {
    private long id;
    private LocalDate resultDate;
    private LocalTime timeValue;
    private Double distanceValue;
    private Double pointValue;
    @JsonManagedReference
    private ParticipantDTO participant;
    private DisciplineDTO discipline;
}
