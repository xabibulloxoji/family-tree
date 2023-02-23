package uz.sodiqdev.sajara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sodiqdev.sajara.model.template.AbsEntity;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class FeederMother extends AbsEntity {

    @OneToOne
    private Person mother;
}
