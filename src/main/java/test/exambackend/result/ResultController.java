package test.exambackend.result;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    /**
     * Get all results
     * @return List of all results
     */
    @GetMapping
    public ResponseEntity<List<ResResultDTO>> findAll () {
        return ResponseEntity.ok(resultService.findAll());
    }

    /**
     * Get result by id
     * @return Result with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResResultDTO> findById (@PathVariable Long id) {
        return ResponseEntity.of(resultService.findById(id));
    }

    /**
     * Create a new result
     * @return Result created
     */
    @PostMapping
    public ResponseEntity<ResResultDTO> createResult (@RequestBody ReqResultDTO reqResultDTO) {
        return ResponseEntity.status(201).body(resultService.createResult(reqResultDTO));
    }

    /**
     * Delete a result
     * @return Result deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResResultDTO> deleteResult (@PathVariable Long id) {
        return ResponseEntity.ok(resultService.deleteResult(id));
    }

    /**
     * Update a result
     * @return Result updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResResultDTO> updateResult (@PathVariable Long id, @RequestBody ReqResultDTO reqResultDTO) {
        return ResponseEntity.ok(resultService.updateResult(id, reqResultDTO));
    }
}
