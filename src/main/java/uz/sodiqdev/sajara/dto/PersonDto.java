package uz.sodiqdev.sajara.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private String firstName;

    private String lastName;

    private LocalDate isDead;

    private boolean isStep;

    private String gender;

    private Integer spouseId;

    private Integer fatherId;

    private Integer motherId;

}
