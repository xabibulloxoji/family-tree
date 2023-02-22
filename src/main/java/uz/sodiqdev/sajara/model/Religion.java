package uz.sodiqdev.sajara.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sodiqdev.sajara.model.enam.ReligionType;
import uz.sodiqdev.sajara.model.template.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Religion extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private ReligionType name;
}
