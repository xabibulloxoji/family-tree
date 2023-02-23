package uz.sodiqdev.sajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.sodiqdev.sajara.model.Religion;
import uz.sodiqdev.sajara.projection.ReligionProjection;

import java.util.Optional;

public interface ReligionRepository extends JpaRepository<Religion, Integer> {

    @Query(nativeQuery = true, value = "select r.name as name, r.start_date as startDate, r" +
            ".end_date as endDate " +
            "from religion r where r" +
            ".person_id = :id " +
            "order by r.id desc limit 1")
    Optional<ReligionProjection> getReligionByUserId(Integer id);
}
