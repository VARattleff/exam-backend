package test.exambackend.result;

import org.springframework.stereotype.Service;
import test.exambackend.discipline.Discipline;
import test.exambackend.discipline.DisciplineRepository;
import test.exambackend.errorhandling.exception.NotFoundException;
import test.exambackend.errorhandling.exception.ValidationException;
import test.exambackend.participant.Participant;
import test.exambackend.participant.ParticipantRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {
    ResultRepository resultRepository;
    DisciplineRepository disciplineRepository;
    ParticipantRepository participantRepository;

    public ResultService(ResultRepository resultRepository, DisciplineRepository disciplineRepository, ParticipantRepository participantRepository) {
        this.resultRepository = resultRepository;
        this.disciplineRepository = disciplineRepository;
        this.participantRepository = participantRepository;
    }

    /**
     * Find all results
     * @return List of ResResultDTO
     */
    public List<ResResultDTO> findAll() {
        return resultRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Find result by id
     * @param id Long
     * @return Optional of ResResultDTO
     */
    public Optional<ResResultDTO> findById(Long id) {
        if (id == null || id < 0) {
            throw new ValidationException("Id must be provided");
        }

        Optional<Result> resultOptional = resultRepository.findById(id);

        if (resultOptional.isEmpty()) {
            throw new NotFoundException("Result not found, provided id: " + id);
        }

        return resultOptional.map(this::toDTO);
    }

    /**
     * Generate result value based on the discipline's result type
     * @param result Result
     * @return String
     */
    public String generateResultValue(Result result) {
        switch (result.getDiscipline().getResultsType()) {
            case TIME:
                return result.getHours() + ":" + result.getMinutes() + ":" + result.getSeconds() + "." + result.getHundredths();
            case DISTANCE:
                return result.getMeters() + "." + result.getCentimeters() + " m";
            case POINTS:
                if (result.getPoints() != null) {
                    return result.getPoints().toString();
                } else {
                    return "No points available";
                }
            default:
                return "Invalid result type";
        }
    }

    /**
     * Validate and set participant and discipline for the result
     * @param reqResultDTO ReqResultDTO
     * @param result Result
     */
     void validateAndSetParticipantAndDiscipline(ReqResultDTO reqResultDTO, Result result) {
        if (reqResultDTO == null) {
            throw new ValidationException("Request body cannot be null");
        }

        if (reqResultDTO.getParticipantId() == null || reqResultDTO.getParticipantId() < 0) {
            throw new ValidationException("Invalid participant id,  provided id:" + reqResultDTO.getParticipantId());
        }

        if (reqResultDTO.getDisciplineId() == null || reqResultDTO.getDisciplineId() < 0) {
            throw new ValidationException("Invalid discipline id provided id:" + reqResultDTO.getDisciplineId());
        }

        Participant participant = participantRepository.findById(reqResultDTO.getParticipantId())
                .orElseThrow(() -> new NotFoundException("Participant not found with provided id: " + reqResultDTO.getParticipantId()));

        Discipline discipline = disciplineRepository.findById(reqResultDTO.getDisciplineId())
                .orElseThrow(() -> new NotFoundException("Discipline not found with provided id: " + reqResultDTO.getDisciplineId()));

        if (!participant.getDisciplines().contains(discipline)) {
            throw new ValidationException("Participant is not part of the provided discipline");
        }

        result.setParticipant(participant);
        result.setDiscipline(discipline);
    }

    /**
     * Create a new result
     * @param reqResultDTO ReqResultDTO
     * @return ResResultDTO
     */
    public ResResultDTO createResult(ReqResultDTO reqResultDTO) {
        Result result = new Result();

        validateAndSetParticipantAndDiscipline(reqResultDTO, result);

        result.setResultDate(reqResultDTO.getResultDate());
        result.setHours(reqResultDTO.getHours());
        result.setMinutes(reqResultDTO.getMinutes());
        result.setSeconds(reqResultDTO.getSeconds());
        result.setHundredths(reqResultDTO.getHundredths());
        result.setMeters(reqResultDTO.getMeters());
        result.setCentimeters(reqResultDTO.getCentimeters());
        result.setPoints(reqResultDTO.getPoints());

        Result savedResult;

        try {
            savedResult = resultRepository.save(result);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the result", e);
        }

        return toDTO(savedResult);
    }

    /**
     * Delete result by id
     * @param id Long
     * @return ResResultDTO
     */
    public ResResultDTO deleteResult(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Result not found with provided id: " + id));

        resultRepository.deleteById(id);

        return toDTO(result);
    }

    /**
     * Update result by id
     * @param id Long
     * @param reqResultDTO ReqResultDTO
     * @return ResResultDTO
     */
    public ResResultDTO updateResult(Long id, ReqResultDTO reqResultDTO) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Result not found with provided id: " + id));

        validateAndSetParticipantAndDiscipline(reqResultDTO, result);

        result.setResultDate(reqResultDTO.getResultDate());
        result.setHours(reqResultDTO.getHours());
        result.setMinutes(reqResultDTO.getMinutes());
        result.setSeconds(reqResultDTO.getSeconds());
        result.setHundredths(reqResultDTO.getHundredths());
        result.setMeters(reqResultDTO.getMeters());
        result.setCentimeters(reqResultDTO.getCentimeters());
        result.setPoints(reqResultDTO.getPoints());

        Result savedResult;

        try {
            savedResult = resultRepository.save(result);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the result", e);
        }

        return toDTO(savedResult);
    }

    /**
     * Convert Result to ResResultDTO
     * @param result Result
     * @return ResResultDTO
     */
    public ResResultDTO toDTO(Result result) {
        ResResultDTO resResultDTO = new ResResultDTO();
        resResultDTO.setId(result.getId());
        resResultDTO.setDisciplineName(result.getDiscipline().getName());
        resResultDTO.setAgeGroup(result.getParticipant().getAgeGroup());
        resResultDTO.setResultsType(result.getDiscipline().getResultsType());
        resResultDTO.setResultDate(result.getResultDate());
        resResultDTO.setGender(result.getParticipant().getGender());
        resResultDTO.setAdjacentClub(result.getParticipant().getAdjacentClub());

        resResultDTO.setResultValue(generateResultValue(result));


        resResultDTO.setParticipantName(result.getParticipant().getFullName());

        return resResultDTO;
    }

}