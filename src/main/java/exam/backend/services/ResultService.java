package exam.backend.services;

import exam.backend.dto.ResultDto;
import exam.backend.entities.Discipline;
import exam.backend.entities.Participant;
import exam.backend.entities.Result;
import exam.backend.repositories.DisciplineRepository;
import exam.backend.repositories.ParticipantRepository;
import exam.backend.repositories.ResultRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ResultService {
    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;

    public ResultService(ResultRepository resultRepository, ParticipantRepository participantRepository, DisciplineRepository disciplineRepository) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Result addResult(ResultDto request) {
        Participant participant = participantRepository.findById(request.participantId()).orElseThrow();
        Discipline discipline = disciplineRepository.findById(request.disciplineId()).orElseThrow();
        LocalDate date = LocalDate.parse(request.date());

        Result result = new Result(date, request.resultType(),
                request.resultValue(), participant, discipline);


        return resultRepository.save(result);

    }
}
