package exam.backend.dto;

public record UpdateResultDto(Integer id, String date, Integer participantId,
                              Integer disciplineId, String resultValue) {
}
