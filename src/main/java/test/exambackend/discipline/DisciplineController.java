package test.exambackend.discipline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {
    DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    /**
     * Get all disciplines
     * @return List of all disciplines
     */
    @GetMapping
    public ResponseEntity<List<DisciplineDTO>> findAll() {
        return ResponseEntity.ok(disciplineService.findAll());
    }

    /**
     * Get discipline by id
     * @return Discipline with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDTO> findById(@PathVariable  Long id) {
        return ResponseEntity.of(disciplineService.findById(id));
    }

    /**
     * Create a new discipline
     * @return Discipline created
     */
    @PostMapping
    public ResponseEntity<DisciplineDTO> createDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        return ResponseEntity.status(201).body(disciplineService.createDiscipline(disciplineDTO));
    }

    /**
     * Delete a discipline
     * @return Discipline deleted
     */
    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDTO> updateDiscipline(@PathVariable Long id, @RequestBody DisciplineDTO disciplineDTO) {
        return ResponseEntity.ok(disciplineService.updateDiscipline(id, disciplineDTO));
    }

}
