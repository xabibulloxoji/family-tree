package uz.sodiqdev.sajara.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sodiqdev.sajara.dto.PersonDto;
import uz.sodiqdev.sajara.dto.PersonDtoForJson;
import uz.sodiqdev.sajara.projection.SpouseProjection;
import uz.sodiqdev.sajara.model.Person;
import uz.sodiqdev.sajara.repository.PersonRepository;

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


    public PersonDtoForJson getOnePersonTree(Integer id) {
        PersonDtoForJson personDtoForJson = new PersonDtoForJson();
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (!optionalPerson.isEmpty()) {
            Person person = optionalPerson.get();
            personDtoForJson.setId(person.getId());
            personDtoForJson.setFirstName(person.getFirstName());
            personDtoForJson.setApdopted(person.isAdopted());
            personDtoForJson.setGender(person.getGender());
            personDtoForJson.setIsDead(person.getIsDead());
            personDtoForJson.setSpouses(getSpouses(id));
            Optional<List<Integer>> optionalList;
            if (person.getGender().equalsIgnoreCase("male")) {
                optionalList = personRepository.findByFatherId(id);
            } else if (person.getGender().equalsIgnoreCase("female")) {
                optionalList = personRepository.findByMotherId(id);
            } else {
                return null;
            }
            List<PersonDtoForJson> children = new ArrayList<>();
            if (optionalList.isPresent()) {
                List<Integer> ids = optionalList.get();
                for (Integer integer : ids) {
                    PersonDtoForJson onePersonTree = getOnePersonTree(integer);
                    children.add(onePersonTree);
                }
            }
            personDtoForJson.setChildren(children);
            return personDtoForJson;
        }
        return null;
    }


    public List<SpouseProjection> getSpouses(Integer spouseId) {
        Optional<List<SpouseProjection>> optionalSpouseDtoList = personRepository.findAllBySpouseId(spouseId);
        return optionalSpouseDtoList.orElse(null);
    }
}
