package exam.backend.configuration;

import exam.backend.entities.*;
import exam.backend.repositories.DisciplineRepository;
import exam.backend.repositories.ParticipantRepository;
import exam.backend.repositories.ResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class CreateData implements CommandLineRunner {
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;
    private final ResultRepository resultRepository;

    private Map<String, Discipline> disciplinesByName;
    private Map<String, Participant> participantsByName;

    public CreateData(ParticipantRepository participantRepository, DisciplineRepository disciplineRepository, ResultRepository resultRepository) {
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
        this.resultRepository = resultRepository;

        disciplinesByName = new HashMap<>();
        participantsByName = new HashMap<>();
    }

    @Override
    public void run(String... args) throws Exception {
        createDisciplines();
        createParticipants();
        createResults();
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

        Participant participant1 = new Participant("Daniel", Gender.MALE, "Aalborg", 25, List.of(sprint100m, longJump));
        Participant participant2 = new Participant("Line", Gender.FEMALE, "Aarhus", 30, List.of(highJump, discosThrow));
        Participant participant3 = new Participant("Erik", Gender.MALE, "Copenhagen", 35, List.of(sprint100m, highJump));
        Participant participant4 = new Participant("Mette", Gender.FEMALE, "Odense", 40, List.of(longJump, discosThrow));

        List<Participant> participants = List.of(participant1, participant2, participant3, participant4);

        participantRepository.saveAll(participants);

        for (Participant participant : participants) {
            participantsByName.put(participant.getName(), participant);
        }
    }

    public void createResults() {
        Participant daniel = participantsByName.get("Daniel");
        Participant line = participantsByName.get("Line");
        Participant erik = participantsByName.get("Erik");
        Participant mette = participantsByName.get("Mette");

        Discipline sprint100m = disciplinesByName.get("100m sprint");
        Discipline longJump = disciplinesByName.get("Long jump");
        Discipline highJump = disciplinesByName.get("High jump");
        Discipline discosThrow = disciplinesByName.get("Discos throw");

        Result result1 = new Result(LocalDate.of(2024, 6, 20), ResultType.TIME, "10.5", daniel, sprint100m);
        Result result2 = new Result(LocalDate.of(2024, 6, 20), ResultType.DISTANCE, "7.5", daniel, longJump);
        Result result3 = new Result(LocalDate.of(2024, 6, 20), ResultType.DISTANCE, "1.5", daniel, highJump);

        List<Result> results = List.of(result1, result2, result3);

        resultRepository.saveAll(results);
    }
}