package uz.sodiqdev.sajara.dto;

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
public class PersonDtoForJson {

    private String firstName;

    private boolean isApdopted;

    private String gender;

    private LocalDate isDead;

    private Integer spouseIds;

    private List<PersonDtoForJson> children;

    private List<PersonDtoForJson> childrens;
}
