package uz.sodiqdev.sajara.projection;

import java.time.LocalDate;
import java.util.List;

public interface FamilyTreeProjection {

    String getFirstName();

    Boolean getIsApdopted();

    String getGender();

    LocalDate getIsDead();

    List<Integer> getSpouseIds();

    List<Integer> getChildIds();

}
