package test.exambackend.participant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    /**
     * Get all participants
     * @return List of all participants
     */
    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> findAll () {
        return ResponseEntity.ok(participantService.findAll());
    }

    /**
     * Get participant by id
     * @return Participant with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> findById (@PathVariable Long id) {
        return ResponseEntity.of(participantService.findById(id));
    }

    /**
     * Create a new participant
     * @return Participant created
     */
    @PostMapping
    public ResponseEntity<ParticipantDTO> createParticipant (@RequestBody ParticipantDTO participantDTO) {
        return ResponseEntity.status(201).body(participantService.createParticipant(participantDTO));
    }

    /**
     * Delete a participant
     * @return Participant deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ParticipantDTO> deleteParticipant (@PathVariable Long id) {
        return ResponseEntity.ok(participantService.deleteParticipant(id));
    }

    /**
     * Update a participant
     * @return Participant updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDTO> updateParticipant (@PathVariable Long id, @RequestBody ParticipantDTO participantDTO) {
        return ResponseEntity.ok(participantService.updateParticipant(id, participantDTO));
    }
}
