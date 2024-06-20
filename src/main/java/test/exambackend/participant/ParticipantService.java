package test.exambackend.participant;

import org.springframework.stereotype.Service;
import test.exambackend.discipline.DisciplineDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {
    ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<ParticipantDTO> findAll() {
        return participantRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
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

}
