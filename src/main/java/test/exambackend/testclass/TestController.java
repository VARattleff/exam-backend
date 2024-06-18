package test.exambackend.testclass;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {
    TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public ResponseEntity<List<TestDTO>> findAll () {
        return ResponseEntity.ok(testService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> findById (@PathVariable Long id) {
        return ResponseEntity.of(testService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TestDTO> createTest (@RequestBody TestDTO testDTO) {
        return ResponseEntity.status(201).body(testService.createTest(testDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TestDTO> deleteTest (@PathVariable Long id) {
        return ResponseEntity.ok(testService.deleteTest(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> updateTest (@PathVariable Long id, @RequestBody TestDTO testDTO) {
        return ResponseEntity.ok(testService.updateTest(id, testDTO));
    }
}
