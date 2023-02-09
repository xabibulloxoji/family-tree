package uz.sodiqdev.sajara.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.sodiqdev.sajara.dto.PersonDto;
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
        person.setStep(personDto.isStep());
        person.setIsDead(personDto.getIsDead());
        Person save = personRepository.save(person);
        saveSpouse(personDto,save);
        saveFather(personDto,save);
        saveMother(personDto,save);
        return "Successfully added";
    }

    private void saveMother(PersonDto personDto, Person person) {
        Optional<Person> optionalMother = personRepository.findById(personDto.getMotherId());
        if (optionalMother.isPresent()) {
            Person  mother = optionalMother.get();
            person.setMother(mother);
            personRepository.save(mother);
        }
    }

    private void saveFather(PersonDto personDto, Person person) {
        Optional<Person> optionalFather = personRepository.findById(personDto.getFatherId());
        if (optionalFather.isPresent()) {
            Person father = optionalFather.get();
            person.setFather(father);
            personRepository.save(father);
        }
    }

    private void saveSpouse(PersonDto personDto, Person person) {
        List<Person> lsi1 = new ArrayList<>();
        List<Person> list2 = new ArrayList<>();
        Optional<Person> optionalSpouse= personRepository.findById(personDto.getSpouseId());
        if (optionalSpouse.isPresent()) {
            Person spouse = optionalSpouse.get();
            lsi1.add(spouse);
            list2.add(person);
            person.setSpouse(lsi1);
            spouse.setSpouse(list2);
            personRepository.save(spouse);
        }
    }
}
