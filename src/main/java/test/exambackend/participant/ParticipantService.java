package test.exambackend.participant;

import org.springframework.stereotype.Service;
import test.exambackend.discipline.DiciplineRepository;
import test.exambackend.discipline.Discipline;
import test.exambackend.discipline.DisciplineDTO;
import test.exambackend.errorhandling.exception.NotFoundException;
import test.exambackend.errorhandling.exception.ValidationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantService {
    ParticipantRepository participantRepository;
    DiciplineRepository diciplineRepository;

    public ParticipantService(ParticipantRepository participantRepository, DiciplineRepository diciplineRepository) {
        this.participantRepository = participantRepository;
        this.diciplineRepository = diciplineRepository;
    }

    public List<ParticipantDTO> findAll() {
        return participantRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ParticipantDTO> findById(Long id) {
        if (id == null || id < 0) {
            throw new ValidationException("Id must be provided");
        }

        Optional<Participant> participantOptional = participantRepository.findById(id);

        if (participantOptional.isEmpty()) {
            throw new NotFoundException("Participant not found, provided id: " + id);
        }

        return participantOptional.map(this::toDTO);
    }



    public ParticipantDTO createParticipant(ParticipantDTO participantDTO) {
        if (
                participantDTO.getFullName() == null ||
                participantDTO.getFullName().isEmpty() ||
                participantDTO.getAge() < 0 ||
                participantDTO.getGender() == null ||
                participantDTO.getAdjacentClub() == null ||
                participantDTO.getCountry() == null
            )

        {
            throw new ValidationException("fullName, age, gender, adjacentClub and country must be provided");
        }

        List<Discipline> disciplines = participantDTO.getDisciplines().stream()
                .map(disciplineDTO -> diciplineRepository.findById(disciplineDTO.getId())
                        .orElseThrow(() -> new NotFoundException("Discipline not found, provided id: " + disciplineDTO.getId())))
                .toList();

        Participant participant = toEntity(participantDTO);
        participant.calculateAndSetAgeGroup();
        participant.setDisciplines(disciplines);
        participantRepository.save(participant);

        return toDTO(participant);
    }

    public ParticipantDTO toDTO(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participant.getId());
        participantDTO.setFullName(participant.getFullName());
        participantDTO.setAge(participant.getAge());
        participantDTO.setGender(participant.getGender());
        participantDTO.setAdjacentClub(participant.getAdjacentClub());
        participantDTO.setAgeGroup(participant.getAgeGroup());
        participantDTO.setCountry(participant.getCountry());


        participantDTO.setDisciplines(participant.getDisciplines().stream().map(discipline -> {
            DisciplineDTO disciplineDTO = new DisciplineDTO();
            disciplineDTO.setId(discipline.getId());
            disciplineDTO.setName(discipline.getName());
            disciplineDTO.setDescription(discipline.getDescription());
            disciplineDTO.setResultsType(discipline.getResultsType());
            return disciplineDTO;
        }).collect(Collectors.toList()));

        return participantDTO;
    }

    public Participant toEntity(ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setId(participantDTO.getId());
        participant.setFullName(participantDTO.getFullName());
        participant.setAge(participantDTO.getAge());
        participant.setGender(participantDTO.getGender());
        participant.setAdjacentClub(participantDTO.getAdjacentClub());
        participant.setCountry(participantDTO.getCountry());
        return participant;
    }

    public ParticipantDTO deleteParticipant(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant not found, provided id: " + id));
        ParticipantDTO participantDTO = toDTO(participant);
        participantRepository.deleteById(id);
        return participantDTO;
    }
}