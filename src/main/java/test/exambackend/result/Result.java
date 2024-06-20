package test.exambackend.result;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import test.exambackend.discipline.Discipline;
import test.exambackend.participant.Participant;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate resultDate;

    private Double pointValue;

    private Integer hours;
    private Integer minutes;
    private Integer seconds;
    private Integer hundredths;

    private Integer meters;
    private Integer centimeters;

    private Integer points;

    private String resultValue;

    @ManyToOne(fetch = FetchType.EAGER)
    private Participant participant;
    @ManyToOne(fetch = FetchType.EAGER)
    private Discipline discipline;
}