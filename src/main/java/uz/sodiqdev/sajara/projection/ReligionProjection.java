package uz.sodiqdev.sajara.projection;

import uz.sodiqdev.sajara.model.enam.ReligionType;

import java.time.LocalDate;

public interface ReligionProjection {
    String getName();

    LocalDate getStartDate();

    LocalDate getEndDate();
}
