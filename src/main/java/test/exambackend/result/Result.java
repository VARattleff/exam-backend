package test.exambackend.result;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.exambackend.discipline.Discipline;
import test.exambackend.participant.Participant;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate resultDate;
    private LocalTime timeValue;
    private Double distanceValue;
    private Double pointValue;

    @ManyToOne(fetch = FetchType.EAGER)
    private Participant participant;

    @ManyToOne(fetch = FetchType.EAGER)
    private Discipline discipline;
}
