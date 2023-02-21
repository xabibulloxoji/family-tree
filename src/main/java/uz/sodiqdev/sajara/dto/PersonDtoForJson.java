package uz.sodiqdev.sajara.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sodiqdev.sajara.projection.FeederMothersProjection;
import uz.sodiqdev.sajara.projection.SpouseProjection;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDtoForJson {

    private Integer id;

    private String firstName;

    private Integer fatherId;

    private Integer motherId;

    private boolean isApdopted;

    private String gender;

    private LocalDate isDead;

    private List<SpouseProjection> spouses;

//    private List<FeederMothersProjection> feederMmothers;

    private List<PersonDtoForJson> children;

}
