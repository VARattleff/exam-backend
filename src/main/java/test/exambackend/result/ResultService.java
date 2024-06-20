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

    public List<ResResultDTO> findAll() {
        return resultRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ResResultDTO> findById(Long id) {
        if (id == null || id < 0) {
            throw new ValidationException("Id must be provided");
        }

        Optional<Result> resultOptional = resultRepository.findById(id);
        return resultOptional.map(this::toDTO);
    }



    private String generateResultValue(Result result) {
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

    public ResResultDTO createResult(ReqResultDTO reqResultDTO) {
        Participant participant = participantRepository.findById(reqResultDTO.getParticipantId())
                .orElseThrow(() -> new NotFoundException("Participant not found"));

        Discipline discipline = disciplineRepository.findById(reqResultDTO.getDisciplineId())
                .orElseThrow(() -> new NotFoundException("Discipline not found"));

        Result result = new Result();
        result.setParticipant(participant);
        result.setDiscipline(discipline);
        result.setResultDate(reqResultDTO.getResultDate());
        result.setHours(reqResultDTO.getHours());
        result.setMinutes(reqResultDTO.getMinutes());
        result.setSeconds(reqResultDTO.getSeconds());
        result.setHundredths(reqResultDTO.getHundredths());
        result.setMeters(reqResultDTO.getMeters());
        result.setCentimeters(reqResultDTO.getCentimeters());
        result.setPoints(reqResultDTO.getPoints());


        return toDTO(resultRepository.save(result));
    }
}