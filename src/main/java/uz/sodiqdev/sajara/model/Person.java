package uz.sodiqdev.sajara.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate isDead;

    private String gender;

    private boolean isStep;

    @ManyToOne
    private Person father;

    @ManyToOne
    private Person mother;

    @ManyToMany
    private List<Person> spouse;

    @ManyToMany
    private List<Person> children;

}
