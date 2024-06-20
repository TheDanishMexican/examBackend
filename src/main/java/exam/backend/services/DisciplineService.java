package exam.backend.services;

import exam.backend.entities.Discipline;
import exam.backend.repositories.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public HashMap<String, Discipline> getDisciplineMap() {
        List<Discipline> disciplines = disciplineRepository.findAll();
        HashMap<String, Discipline> disciplineMap = new HashMap<>();
        for (Discipline discipline : disciplines) {
            disciplineMap.put(discipline.getName(), discipline);
        }
        return disciplineMap;
    }
}
