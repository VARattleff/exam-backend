package test.exambackend.participant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    //todo: get deltager
    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> findAll () {
        return ResponseEntity.ok(participantService.findAll());
    }

    //todo: get deltagerer(id)

    //todo: post deltager

    //todo: delete deltager(id)

    //todo: put deltager(id)
}
