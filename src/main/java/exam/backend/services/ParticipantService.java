package exam.backend.services;

import exam.backend.dto.ParticipantDto;
import exam.backend.entities.Discipline;
import exam.backend.entities.Participant;
import exam.backend.repositories.DisciplineRepository;
import exam.backend.repositories.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final DisciplineService disciplineService;

    public ParticipantService(ParticipantRepository participantRepository, DisciplineService disciplineService) {
        this.participantRepository = participantRepository;
        this.disciplineService = disciplineService;
    }

    public List<Discipline> getDisciplinesFromName(List<String> disciplineNames) {
        HashMap<String, Discipline> disciplineMap = disciplineService.getDisciplineMap();

        List<Discipline> disciplines = new ArrayList<>();

        for (String disciplineName : disciplineNames) {
            Discipline add =disciplineMap.get(disciplineName);
            disciplines.add(add);
        }

        return disciplines;
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public ParticipantDto addParticipant(ParticipantDto request) {

        List<Discipline> disciplines = getDisciplinesFromName(request.disciplineNames());

        Participant newParticipant = new Participant(request.name(), request.gender(), request.club(),
                request.age(), disciplines);

         participantRepository.save(newParticipant);

            return toDto(newParticipant);
    }

    public ParticipantDto toDto(Participant participant) {

        List<String> disciplineNames = new ArrayList<>();

        for (Discipline discipline : participant.getDisciplines()) {
            disciplineNames.add(discipline.getName());
        }

        return new ParticipantDto(participant.getName(), participant.getGender(),
                participant.getClub(), participant.getAge(), disciplineNames);
    }

    public ParticipantDto editParticipant(ParticipantDto request, int id) {
        Participant participant = participantRepository.findById(id).orElseThrow();

        participant.setDisciplines(getDisciplinesFromName(request.disciplineNames()));
        participant.setName(request.name());
        participant.setAge(request.age());
        participant.setClub(request.club());

        participantRepository.save(participant);

        return toDto(participant);
    }
}

