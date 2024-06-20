package test.exambackend.participant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.exambackend.discipline.Discipline;
import test.exambackend.result.Result;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private String adjacentClub;
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    @ManyToMany
    private List<Discipline> disciplines = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<Result> results;
}
