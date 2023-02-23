package uz.sodiqdev.sajara.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeederMotherDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer feederMotherId;

}
