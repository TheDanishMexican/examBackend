package exam.backend.dto;

import exam.backend.entities.Gender;

import java.util.List;

public record ParticipantDto(String name, Gender gender, String club, Integer age, List<String> disciplineNames) {
}
