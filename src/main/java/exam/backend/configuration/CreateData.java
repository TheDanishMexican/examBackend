package exam.backend.configuration;

import exam.backend.entities.*;
import exam.backend.repositories.DisciplineRepository;
import exam.backend.repositories.ParticipantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class CreateData implements CommandLineRunner {
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;

    private Map<String, Discipline> disciplinesByName;

    public CreateData(ParticipantRepository participantRepository,
                      DisciplineRepository disciplineRepository) {
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
        this.disciplinesByName = new HashMap<>();
    }

    @Override
    public void run(String... args) throws Exception {
        createDisciplines();
        createParticipants();
    }

    private void createDisciplines() {
        Discipline discipline1 = new Discipline("100m sprint", ResultType.TIME);
        Discipline discipline2 = new Discipline("Long jump", ResultType.DISTANCE);
        Discipline discipline3 = new Discipline("High jump", ResultType.DISTANCE);
        Discipline discipline4 = new Discipline("Discos throw", ResultType.DISTANCE);

        List<Discipline> disciplines = List.of(discipline1, discipline2, discipline3, discipline4);

        disciplineRepository.saveAll(disciplines);

        for (Discipline discipline : disciplines) {
            disciplinesByName.put(discipline.getName(), discipline);
        }
    }


    private void createParticipants() {
        Discipline sprint100m = disciplinesByName.get("100m sprint");
        Discipline longJump = disciplinesByName.get("Long jump");
        Discipline highJump = disciplinesByName.get("High jump");
        Discipline discosThrow = disciplinesByName.get("Discos throw");

        Participant participant1 = new Participant("Daniel", Gender.MALE, Club.Aalborg, 25, List.of(sprint100m, longJump));
        Participant participant2 = new Participant("Line", Gender.FEMALE, Club.Aarhus, 30, List.of(highJump, discosThrow));
        Participant participant3 = new Participant("Erik", Gender.MALE, Club.Copenhagen, 35, List.of(sprint100m, highJump));
        Participant participant4 = new Participant("Mette", Gender.FEMALE, Club.Odense, 40, List.of(longJump, discosThrow));

        List<Participant> participants = List.of(participant1, participant2, participant3, participant4);

        participantRepository.saveAll(participants);
    }
}