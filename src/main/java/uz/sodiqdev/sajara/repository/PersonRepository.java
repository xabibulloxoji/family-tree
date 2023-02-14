package uz.sodiqdev.sajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.sodiqdev.sajara.model.Person;
import uz.sodiqdev.sajara.projection.FamilyTreeProjection;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(nativeQuery = true, value = "select p.first_name as firstName,\n" +
                    "        p.is_adopted as isAdopted,\n" +
                    "        p.gender as gender,\n" +
                    "        p.is_dead as isDead,\n" +
                    "       (select p2.id as spouseIds\n" +
                    "        from persons p2\n" +
                    "        where id = ps\n" +
                    "            .spouse_id),\n" +
                    "       p2.id as childIds\n" +
                    "from persons p\n" +
                    "         JOIN persons p2 on p2.father_id = p.id\n" +
                    "         join persons_spouse ps on p.id = ps.persons_id\n" +
                    "where p.id = :id")
    List<FamilyTreeProjection> findAllInfo(Integer id);

}
