package test.exambackend.result;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/results")
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    //todo get all results

    //todo get result by id

    //todo get result by discipline id

    //todo create result

    //todo delete result

    //todo update result
}
