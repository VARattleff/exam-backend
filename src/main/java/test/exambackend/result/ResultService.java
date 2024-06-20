package test.exambackend.result;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
    ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<ResultDTO> findAll() {
        return resultRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
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

    public ResultDTO toDTO(Result result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(result.getId());
        resultDTO.setDisciplineName(result.getDiscipline().getName());
        resultDTO.setAgeGroup(result.getParticipant().getAgeGroup());
        resultDTO.setResultsType(result.getDiscipline().getResultsType());
        resultDTO.setResultDate(result.getResultDate());
        resultDTO.setGender(result.getParticipant().getGender());
        resultDTO.setAdjacentClub(result.getParticipant().getAdjacentClub());

        resultDTO.setResultValue(generateResultValue(result));


        resultDTO.setParticipantName(result.getParticipant().getFullName());

        return resultDTO;
    }
}