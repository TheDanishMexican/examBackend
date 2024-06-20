package exam.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disciplines")
@NoArgsConstructor
@Getter
@Setter
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    public Discipline(String name, ResultType resultType) {
        this.name = name;
        this.resultType = resultType;
    }
}
