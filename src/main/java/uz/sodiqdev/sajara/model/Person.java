package uz.sodiqdev.sajara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    private boolean isAdopted;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Person father;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Person mother;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> spouse;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_feeder_mother",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "feeder_mother_id")
    )
    @JsonIgnoreProperties(value = "person", allowSetters = true)
    private List<Person> feederMothers;

    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Religion> religons;
}
