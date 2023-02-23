package uz.sodiqdev.sajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.sodiqdev.sajara.projection.SpouseProjection;
import uz.sodiqdev.sajara.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(nativeQuery = true, value = "select p.id as id, p.first_name as firstName from person" +
            " p join " +
            "person_spouse " +
            "ps " +
            "on p" +
            ".id = " +
            "ps.person_id where ps.spouse_id = :id")
    Optional<List<SpouseProjection>> findAllBySpouseId(@Param("id") Integer spouseId);

    @Query(nativeQuery = true, value = "select ch.id\n" +
            "from person p\n" +
            "left join person_spouse ps on p.id = ps.person_id\n" +
            "left join person s on s.id = ps.spouse_id\n" +
            "left join person ch on (ch.mother_id = s.id or ch.father_id = s.id)\n" +
            "where person_id = :id")
    List<Integer> getAllChildrenIds(Integer id);

}
