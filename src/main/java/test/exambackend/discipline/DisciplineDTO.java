package test.exambackend.discipline;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.exambackend.participant.ParticipantDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DisciplineDTO {
    private Long id;
    private String name;
    private String description;
    private ResultsType resultsType;
    @JsonManagedReference
    private List<ParticipantDTO> participants = new ArrayList<>();
}
