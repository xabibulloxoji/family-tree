package uz.sodiqdev.sajara.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sodiqdev.sajara.dto.FeederMotherDto;
import uz.sodiqdev.sajara.dto.PersonDto;
import uz.sodiqdev.sajara.dto.PersonDtoForJson;
import uz.sodiqdev.sajara.dto.ReligionDto;
import uz.sodiqdev.sajara.model.FeederMother;
import uz.sodiqdev.sajara.model.Person;
import uz.sodiqdev.sajara.model.Religion;
import uz.sodiqdev.sajara.model.enam.ReligionType;
import uz.sodiqdev.sajara.projection.FeederMothersProjection;
import uz.sodiqdev.sajara.projection.ReligionProjection;
import uz.sodiqdev.sajara.projection.SpouseProjection;
import uz.sodiqdev.sajara.repository.FeederMotherRepository;
import uz.sodiqdev.sajara.repository.PersonRepository;
import uz.sodiqdev.sajara.repository.ReligionRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final FeederMotherRepository feederMotherRepository;

    private final ReligionRepository religionRepository;


    public String addPerson(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setIsDead(personDto.getIsDead());
        person.setAdopted(personDto.isAdopted());
        person.setGender(personDto.getGender());

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
        List<Integer> spouseIds = personDto.getSpouseIds();
        for (Integer spouseId : spouseIds) {
            Optional<Person> optionalSpouse = personRepository.findById(spouseId);
            if (optionalSpouse.isPresent()) {
                Person person1 = optionalSpouse.get();
                person1.getSpouse().add(person);
                if (person.getSpouse() != null) {
                    person.getSpouse().add(person1);
                } else {
                    person.setSpouse(new ArrayList<>(List.of(person1)));
                }
            }
        }

        ReligionDto religionDto = personDto.getReligionDto();
        List<Religion> religions = new ArrayList<>();
        Religion religion = new Religion();
        religion.setName(ReligionType.valueOf(religionDto.getName()));
        religion.setStartDate(religionDto.getStartDate());
        religion.setEndDate(religionDto.getEndDate());
        religions.add(religion);
        person.setReligons(religions);


        List<FeederMother> feederMothers = new ArrayList<>();
        FeederMotherDto feederMotherDto = personDto.getFeederMother();
        Integer feederMotherId = feederMotherDto.getFeederMotherId();
        Optional<Person> optionalPerson = personRepository.findById(feederMotherId);
        if (optionalPerson.isPresent()) {
            Person mother = optionalPerson.get();
            FeederMother feederMother = new FeederMother();
            feederMother.setStartDate(feederMotherDto.getStartDate());
            feederMother.setEndDate(feederMotherDto.getEndDate());
            feederMother.setMother(mother);
            feederMothers.add(feederMother);
        }
        person.setFeederMothers(feederMothers);
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
            if (person.getFather() != null)
                personDtoForJson.setFatherId(person.getFather().getId());
            if (person.getMother() != null)
                personDtoForJson.setMotherId(person.getMother().getId());
            personDtoForJson.setApdopted(person.isAdopted());
            personDtoForJson.setGender(person.getGender());
            personDtoForJson.setIsDead(person.getIsDead());
            personDtoForJson.setSpouses(getSpouse(id));
            if (!getFeederMothers(id).isEmpty()) {
                personDtoForJson.setFeederMothers(getFeederMothers(id));
            }
            personDtoForJson.setReligion(getReligionByUserId(id));
            List<Integer> childrenList = personRepository.getAllChildrenIds(id);

            List<PersonDtoForJson> children = new ArrayList<>();
            if (!childrenList.isEmpty()) {
                for (Integer integer : childrenList) {
                    PersonDtoForJson onePersonTree = getOnePersonTree(integer);
                    children.add(onePersonTree);
                }
            }
            personDtoForJson.setChildren(children);
            return personDtoForJson;

        }
        return null;
    }

    public List<SpouseProjection> getSpouse(Integer spouseId) {
        Optional<List<SpouseProjection>> optionalPersonList = personRepository.findAllBySpouseId(spouseId);
        return optionalPersonList.orElse(null);
    }

    public List<FeederMothersProjection> getFeederMothers(Integer id) {
        List<FeederMothersProjection> feederMothers = feederMotherRepository.findByFeederMothers(id);
        return feederMothers;
    }

    public ReligionProjection getReligionByUserId(Integer id) {
        Optional<ReligionProjection> optionalReligion = religionRepository.getReligionByUserId(id);
        return optionalReligion.orElse(null);
    }
}
