package uz.sodiqdev.sajara.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sodiqdev.sajara.model.FeederMother;
import uz.sodiqdev.sajara.model.enam.ReligionType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private String firstName;

    private String lastName;

    private LocalDate isDead;

    private boolean isAdopted;

    private String gender;

    private Integer fatherId;

    private Integer motherId;

    private List<Integer> spouseIds;

    private ReligionDto religionDto;

    private FeederMotherDto feederMother;

}
