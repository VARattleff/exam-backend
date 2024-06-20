package test.exambackend.result;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    //todo get all results
    @GetMapping
    public ResponseEntity<List<ResultDTO>> findAll () {
        return ResponseEntity.ok(resultService.findAll());
    }

    //todo get result by id

    //todo get result by discipline id

    //todo create result

    //todo delete result

    //todo update result
}
