package exam.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "participants")
@NoArgsConstructor
@Getter
@Setter
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String club;

    private Integer age;

    @ManyToMany
    private List<Discipline> disciplines;

    public Participant(String name, Gender gender, String club, Integer age, List<Discipline> disciplines) {
        this.name = name;
        this.gender = gender;
        this.club = club;
        this.age = age;
        this.disciplines = disciplines;
    }
}
