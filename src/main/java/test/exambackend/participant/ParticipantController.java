package test.exambackend.participant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    //todo: get deltager

    //todo: get deltagerer(id)

    //todo: post deltager

    //todo: delete deltager(id)

    //todo: put deltager(id)
}
