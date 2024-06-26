package exam.backend.controllers;

import exam.backend.dto.ParticipantDto;
import exam.backend.entities.Participant;
import exam.backend.services.ParticipantService;
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

    @GetMapping
    public List<Participant> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> addParticipant(@RequestBody ParticipantDto request) {
       return ResponseEntity.ok(participantService.addParticipant(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> editParticipant(@RequestBody ParticipantDto request, @PathVariable int id) {
        return ResponseEntity.ok(participantService.editParticipant(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable int id) {
        return participantService.deleteParticipant(id);
    }
}
