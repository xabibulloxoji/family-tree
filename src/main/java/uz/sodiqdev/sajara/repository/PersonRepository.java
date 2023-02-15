package uz.sodiqdev.sajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.sodiqdev.sajara.projection.SpouseProjection;
import uz.sodiqdev.sajara.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(nativeQuery = true, value = "select p.id from persons p where p.mother_id = :motherId")
    Optional<List<Integer>> findByMotherId(Integer motherId);


    @Query(nativeQuery = true, value = "select p.id\n" +
            "from persons p where p.father_id = :fatherId")
    Optional<List<Integer>> findByFatherId(Integer fatherId);


    @Query(nativeQuery = true, value = "select p.id as id, p.first_name as firstName\n" +
            "from persons p\n" +
            "         join persons_spouse ps on p.id = ps.persons_id\n" +
            "where ps.spouse_id = :id")
    Optional<List<SpouseProjection>> findAllBySpouseId(@Param("id") Integer spouseId);
}
