package exam.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "results")
@NoArgsConstructor
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    private String resultValue;

    @ManyToOne
    private Participant participant;

    @ManyToOne
    private Discipline discipline;

    private String resultSuffix;

    public Result(LocalDate date, ResultType resultType, String resultValue, Participant participant, Discipline discipline) {
        this.date = date;
        this.resultType = resultType;
        this.resultValue = resultValue;
        this.participant = participant;
        this.discipline = discipline;
        decideResultSuffix();
    }

    public void decideResultSuffix() {
        switch(resultType) {
            case TIME:
                resultSuffix = "Time in MM:SS:MS";
                break;
            case DISTANCE:
                resultSuffix = "Meters";
                break;
            case POINTS:
                resultSuffix = "Points";
                break;
    }}
}
