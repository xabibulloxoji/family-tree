package uz.sodiqdev.sajara.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sodiqdev.sajara.dto.PersonDto;
import uz.sodiqdev.sajara.model.Person;
import uz.sodiqdev.sajara.service.PersonService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/add")
    public HttpEntity<?> addPerson(@RequestBody PersonDto personDto){
        String person = personService.addPerson(personDto);
        return ResponseEntity.status(person!= null?200:404).body(person);
    }
}
