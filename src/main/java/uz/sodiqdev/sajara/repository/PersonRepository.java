package uz.sodiqdev.sajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sodiqdev.sajara.model.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
