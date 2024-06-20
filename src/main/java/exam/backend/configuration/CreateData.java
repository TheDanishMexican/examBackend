package exam.backend.configuration;

import exam.backend.entities.Club;
import exam.backend.entities.Gender;
import exam.backend.entities.Participant;
import exam.backend.repositories.ParticipantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CreateData implements CommandLineRunner {
    private final ParticipantRepository participantRepository;

    public CreateData(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createParticipants();
    }

    private void createParticipants() {
        Participant participant1 = new Participant("Daniel", Gender.MALE, Club.Aalborg, 25);
        Participant participant2 = new Participant("Line", Gender.FEMALE, Club.Aarhus, 30);
        Participant participant3 = new Participant("Erik", Gender.MALE, Club.Copenhagen, 35);
        Participant participant4 = new Participant("Mette", Gender.FEMALE, Club.Odense, 40);

        List<Participant> participants = List.of(participant1, participant2, participant3, participant4);

        participantRepository.saveAll(participants);
    }
}