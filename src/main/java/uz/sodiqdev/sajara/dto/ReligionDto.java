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
public class ReligionDto {

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;
}
