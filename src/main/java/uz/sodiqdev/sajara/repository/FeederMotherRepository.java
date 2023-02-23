package uz.sodiqdev.sajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.sodiqdev.sajara.model.FeederMother;
import uz.sodiqdev.sajara.projection.FeederMothersProjection;

import java.util.List;

public interface FeederMotherRepository extends JpaRepository<FeederMother, Integer> {


    @Query(nativeQuery = true, value = "select p2.id as id, p2.first_name as firstName, fm" +
            ".start_date as startDate, fm" +
            ".end_date as endDate\n" +
            "from person p\n" +
            "         join person_feeder_mother pfm on p.id = pfm.person_id\n" +
            "         join feeder_mother fm on fm.id = pfm.feeder_mother_id\n" +
            "         join person p2 on p2.id = fm.mother_id\n" +
            "where p.id = :id and p.mother_id != fm.mother_id")
    List<FeederMothersProjection> findByFeederMothers(Integer id);
}
