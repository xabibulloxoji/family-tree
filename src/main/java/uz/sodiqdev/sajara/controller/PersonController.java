package uz.sodiqdev.sajara.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sodiqdev.sajara.dto.PersonDto;
import uz.sodiqdev.sajara.model.Person;
import uz.sodiqdev.sajara.service.PersonService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/add")
    public HttpEntity<?> addPerson(@RequestBody PersonDto personDto) {
        String person = personService.addPerson(personDto);
        return ResponseEntity.status(person != null ? 200 : 404).body(person);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getFamilyTreeById(@PathVariable Integer id) {
        return ResponseEntity.status(personService.getFamilyTreeById(id) != null ? 200 : 404).body(personService.getFamilyTreeById(id));
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getOneTree(@PathVariable Integer id) {
        return ResponseEntity.status(personService.getOneTree(id) != null ? 200 : 404).body(personService.getOneTree(id));
    }
}
