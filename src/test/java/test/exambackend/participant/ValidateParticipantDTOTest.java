package test.exambackend.participant;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ValidateParticipantDTOTest {

    ParticipantService participantService = Mockito.mock(ParticipantService.class);

    @Test
    void validateParticipantDTO_ValidInput_NoExceptionThrown() {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setFullName("Viktor A. Rattleff");
        participantDTO.setAge(23);
        participantDTO.setGender(Gender.MALE);
        participantDTO.setAdjacentClub("Club Name");
        participantDTO.setCountry(Country.DENMARK);

        assertDoesNotThrow(() -> participantService.validateParticipantDTO(participantDTO));
    }
}