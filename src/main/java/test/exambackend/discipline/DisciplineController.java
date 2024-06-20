package test.exambackend.discipline;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {
    DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    //todo get all disciplines

    //todo get discipline by id

    //todo create discipline

    //todo delete discipline

    //todo update discipline

}
