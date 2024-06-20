package test.exambackend.discipline;

import org.springframework.stereotype.Service;
import test.exambackend.errorhandling.exception.NotFoundException;
import test.exambackend.errorhandling.exception.ValidationException;
import test.exambackend.participant.Participant;
import test.exambackend.participant.ParticipantDTO;
import test.exambackend.participant.ParticipantRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplineService {
    DiciplineRepository diciplineRepository;
    ParticipantRepository participantRepository;

    public DisciplineService(DiciplineRepository diciplineRepository, ParticipantRepository participantRepository) {
        this.diciplineRepository = diciplineRepository;
        this.participantRepository = participantRepository;
    }

    /**
     * Validate DisciplineDTO
     * @param disciplineDTO DisciplineDTO
     */
    public void validateDisciplineDTO(DisciplineDTO disciplineDTO) {
        if (
                disciplineDTO.getName() == null ||
                        disciplineDTO.getName().isEmpty() ||
                        disciplineDTO.getDescription() == null ||
                        disciplineDTO.getDescription().isEmpty() ||
                        disciplineDTO.getResultsType() == null
        ) {
            throw new ValidationException("name, description and resultsType must be provided");
        }
    }

    /**
     * Find all disciplines
     * @return List of DisciplineDTO
     */
    public List<DisciplineDTO> findAll() {
        return diciplineRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Find discipline by id
     * @param id Long
     * @return Optional of DisciplineDTO
     */
    public Optional<DisciplineDTO> findById(Long id) {
        if (id == null || id < 0) {
            throw new ValidationException("Id must be provided");
        }

        Optional<Discipline> participantOptional = diciplineRepository.findById(id);

        if (participantOptional.isEmpty()) {
            throw new NotFoundException("Discipline not found, provided id: " + id);
        }

        return participantOptional.map(this::toDTO);
    }

    /**
     * Create discipline
     * @param disciplineDTO DisciplineDTO
     * @return DisciplineDTO
     */
    public DisciplineDTO createDiscipline(DisciplineDTO disciplineDTO) {
        validateDisciplineDTO(disciplineDTO);

        List<Participant> participants = disciplineDTO.getParticipants().stream()
                .map(participantDTO -> participantRepository.findById(participantDTO.getId())
                        .orElseThrow(() -> new NotFoundException(" Participants not found, provided id: " + participantDTO.getId())))
                .toList();

        Discipline discipline = toEntity(disciplineDTO);
        discipline.setParticipants(participants);
        participants.forEach(participant -> participant.getDisciplines().add(discipline));
        diciplineRepository.save(discipline);

        return toDTO(discipline);
    }

    /**
     * Update discipline
     * @param id Long
     * @return DisciplineDTO
     */
    public DisciplineDTO updateDiscipline(Long id, DisciplineDTO disciplineDTO) {
        Discipline existingDiscipline = diciplineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Discipline not found, provided id: " + id));

        validateDisciplineDTO(disciplineDTO);

        List<Participant> participants = disciplineDTO.getParticipants().stream()
                .map(participantDTO -> participantRepository.findById(participantDTO.getId())
                        .orElseThrow(() -> new NotFoundException("Participants not found, provided id: " + participantDTO.getId())))
                .toList();

        Discipline disciplineToUpdate = toEntity(disciplineDTO);
        disciplineToUpdate.setId(existingDiscipline.getId());
        disciplineToUpdate.setParticipants(participants);
        participants.forEach(participant -> participant.getDisciplines().add(disciplineToUpdate));
        Discipline updatedDiscipline = diciplineRepository.save(disciplineToUpdate);

        return toDTO(updatedDiscipline);
    }

    /**
     * Convert Discipline to DisciplineDTO
     * @param discipline Discipline
     * @return DisciplineDTO
     */
    private DisciplineDTO toDTO(Discipline discipline) {
        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(discipline.getId());
        disciplineDTO.setName(discipline.getName());
        disciplineDTO.setDescription(discipline.getDescription());
        disciplineDTO.setResultsType(discipline.getResultsType());

        List<ParticipantDTO> participants = participantRepository.findByDisciplinesId(discipline.getId()).stream()
                .map(participant -> {
                    ParticipantDTO participantDTO = new ParticipantDTO();
                    participantDTO.setId(participant.getId());
                    participantDTO.setFullName(participant.getFullName());
                    participantDTO.setAge(participant.getAge());
                    participantDTO.setGender(participant.getGender());
                    participantDTO.setAdjacentClub(participant.getAdjacentClub());
                    participantDTO.setAgeGroup(participant.getAgeGroup());
                    participantDTO.setCountry(participant.getCountry());

                    return participantDTO;
                }).collect(Collectors.toList());

        disciplineDTO.setParticipants(participants);

        return disciplineDTO;
    }

    /**
     * Convert DisciplineDTO to Discipline
     * @param disciplineDTO DisciplineDTO
     * @return Discipline
     */
    public Discipline toEntity(DisciplineDTO disciplineDTO) {
        Discipline discipline = new Discipline();
        discipline.setName(disciplineDTO.getName());
        discipline.setDescription(disciplineDTO.getDescription());
        discipline.setResultsType(disciplineDTO.getResultsType());
        return discipline;
    }
}


