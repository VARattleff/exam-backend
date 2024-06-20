package test.exambackend.participant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.exambackend.testclass.TestDTO;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> findAll () {
        return ResponseEntity.ok(participantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> findById (@PathVariable Long id) {
        return ResponseEntity.of(participantService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ParticipantDTO> createParticipant (@RequestBody ParticipantDTO participantDTO) {
        return ResponseEntity.status(201).body(participantService.createParticipant(participantDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ParticipantDTO> deleteParticipant (@PathVariable Long id) {
        return ResponseEntity.ok(participantService.deleteParticipant(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDTO> updateParticipant (@PathVariable Long id, @RequestBody ParticipantDTO participantDTO) {
        return ResponseEntity.ok(participantService.updateParticipant(id, participantDTO));
    }
}
