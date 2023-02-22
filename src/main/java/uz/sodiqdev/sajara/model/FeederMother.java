package uz.sodiqdev.sajara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sodiqdev.sajara.model.template.AbsEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FeederMother extends AbsEntity {

    @ManyToMany(mappedBy = "feederMothers")
    @JsonIgnoreProperties(value = {"feederMothers"}, allowSetters = true)
    private List<Person> feederMothers;
}
