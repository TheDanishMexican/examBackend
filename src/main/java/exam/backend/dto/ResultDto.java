package exam.backend.dto;

import exam.backend.entities.ResultType;

public record ResultDto(String date, ResultType resultType, Integer participantId, Integer disciplineId,
                        Integer value, String resultValue) {

}