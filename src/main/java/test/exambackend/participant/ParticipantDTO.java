package test.exambackend.participant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantDTO {
    private Long id;
    private String fullName;
    private int age;
    private Gender gender;
    private String adjacentClub;
    private AgeGroup ageGroup;
}
