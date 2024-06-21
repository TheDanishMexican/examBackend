package exam.backend.services;

import exam.backend.dto.ResultDto;
import exam.backend.dto.UpdateResultDto;
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

        Result result = new Result(date, discipline.getResultType(),
                request.resultValue(), participant, discipline);


        return resultRepository.save(result);
    }

    public Result updateResult(Integer id, UpdateResultDto request) {
        Result result = resultRepository.findById(id).orElseThrow();
        Participant participant = participantRepository.findById(request.participantId()).orElseThrow();
        Discipline discipline = disciplineRepository.findById(request.disciplineId()).orElseThrow();
        LocalDate date = LocalDate.parse(request.date());

        result.setDate(date);
        result.setParticipant(participant);
        result.setDiscipline(discipline);
        result.setResultValue(request.resultValue());
        result.decideResultSuffix();

        return resultRepository.save(result);
    }

    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }
}
