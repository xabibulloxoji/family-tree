package uz.sodiqdev.sajara.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sodiqdev.sajara.model.enam.ReligionType;
import uz.sodiqdev.sajara.model.template.AbsEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Religion extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private ReligionType name;
}
