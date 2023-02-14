package uz.sodiqdev.sajara.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sodiqdev.sajara.dto.PersonDto;
import uz.sodiqdev.sajara.dto.PersonDtoForJson;
import uz.sodiqdev.sajara.model.Person;
import uz.sodiqdev.sajara.projection.FamilyTreeProjection;
import uz.sodiqdev.sajara.repository.PersonRepository;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;


    public String addPerson(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setGender(personDto.getGender());
        person.setAdopted(personDto.isAdopted());
        person.setIsDead(personDto.getIsDead());

        Optional<Person> optionalMother = personRepository.findById(personDto.getMotherId());
        if (optionalMother.isPresent()) {
            Person mother = optionalMother.get();
            person.setMother(mother);
        }

        Optional<Person> optionalFather = personRepository.findById(personDto.getFatherId());
        if (optionalFather.isPresent()) {
            Person father = optionalFather.get();
            person.setFather(father);
        }
        Optional<Person> optionalSpouse = personRepository.findById(personDto.getSpouseId());
        if (optionalSpouse.isPresent()) {
            Person olimjon = optionalSpouse.get();
            olimjon.getSpouse().add(person);
            if (person.getSpouse() != null) {
                person.getSpouse().add(olimjon);
            } else {
                person.setSpouse(new ArrayList<>(List.of(olimjon)));
            }
        }
        personRepository.save(person);
        return "Successfully added";
    }


    public List<FamilyTreeProjection>  getFamilyTreeById(Integer id){
        return personRepository.findAllInfo(id);
    }

    public Person getOneTree(Integer id){
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            return person;
        }
        return null;
    }
}
