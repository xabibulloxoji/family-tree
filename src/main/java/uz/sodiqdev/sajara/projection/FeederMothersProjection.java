package uz.sodiqdev.sajara.projection;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public interface FeederMothersProjection {

    Integer getId();

    String getFirstName();

    LocalDate getStartDate();

    LocalDate getEndDate();

}
