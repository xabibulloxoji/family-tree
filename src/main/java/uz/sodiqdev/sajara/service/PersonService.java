package uz.sodiqdev.sajara.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sodiqdev.sajara.dto.PersonDto;
import uz.sodiqdev.sajara.dto.PersonDtoForJson;
import uz.sodiqdev.sajara.model.Person;
import uz.sodiqdev.sajara.projection.FeederMothersProjection;
import uz.sodiqdev.sajara.projection.SpouseProjection;
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
            if (person.getFather() != null)
                personDtoForJson.setFatherId(person.getFather().getId());
            if (person.getMother() != null)
                personDtoForJson.setMotherId(person.getMother().getId());
            personDtoForJson.setApdopted(person.isAdopted());
            personDtoForJson.setGender(person.getGender());
            personDtoForJson.setIsDead(person.getIsDead());
            personDtoForJson.setSpouses(getSpouse(id));
//            if (getFeederMothers(id)!=null) {
//                personDtoForJson.setFeederMmothers(getFeederMothers(id));
//            }

            Optional<List<Integer>> optionalList;
            if (person.getGender().equalsIgnoreCase("male")) {
                optionalList = personRepository.findByFatherId(id);
            } else if (person.getGender().equalsIgnoreCase("female")) {
                optionalList = personRepository.findByMotherId(id);
            } else {
                return null;
            }

//            List<Person> spouses = getSpouses(id);
//            List<SpouseWithChildredDto> spouseWithChildredDtos = new ArrayList<>();
//            List<PersonDtoForJson> children1 = new ArrayList<>();
//            for (int i = 0; i < spouses.size(); i++) {
//                SpouseWithChildredDto spouseWithChildredDto = new SpouseWithChildredDto();
//                spouseWithChildredDto.setId(spouses.get(i).getId());
//                spouseWithChildredDto.setFirstName(spouses.get(i).getFirstName());
//                if (optionalList.isPresent()) {
//                    List<Integer> integers = optionalList.get();
//                    for (Integer integer : integers) {
//                        Optional<Person> optionalChild = personRepository.findById(integer);
//                        if (optionalChild.isPresent()) {
//                            Person child = optionalChild.get();
//                            if (person.getGender().equalsIgnoreCase("male") && child.getMother().getId() != person.getId()) {
//                                PersonDtoForJson onePersonTree = getOnePersonTree(integer);
//                                children1.add(onePersonTree);
//                            } else if (person.getGender().equalsIgnoreCase("female") && child.getFather().getId() != person.getId()){
//                                PersonDtoForJson onePersonTree = getOnePersonTree(integer);
//                                children1.add(onePersonTree);
//                            }
//                        }
//                    }
//                }
//                spouseWithChildredDto.setChildren(children1);
//                spouseWithChildredDtos.add(spouseWithChildredDto);
//            }
//            personDtoForJson.setSpouses(spouseWithChildredDtos);

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


    public List<SpouseProjection> getSpouse(Integer spouseId) {
        Optional<List<SpouseProjection>> optionalPersonList = personRepository.findAllBySpouseId(spouseId);
        return optionalPersonList.orElse(null);
    }

    public List<FeederMothersProjection> getFeederMothers(Integer id){
        List<FeederMothersProjection> feederMothers = personRepository.findByFeederMothers(id);
        return feederMothers;
    }
}
