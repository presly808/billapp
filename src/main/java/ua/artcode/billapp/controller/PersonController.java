package ua.artcode.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.artcode.billapp.dto.ResponseMessage;
import ua.artcode.billapp.model.Person;
import ua.artcode.billapp.repository.PersonRepository;

import java.util.List;

/**
 * Created by serhii on 02.12.17.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // /person?name=Serhii
    // /peson/{name} -> PathParam
    @RequestMapping(path = "/person", method = RequestMethod.GET)
    public ResponseEntity<Object> getPerson(@RequestParam(name = "name") String name){
        List<Person> byLastName = personRepository.findByFirstName(name);

        if(byLastName == null || byLastName.isEmpty()){
            return new ResponseEntity<>(new ResponseMessage("User not found", ""), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(byLastName.get(0), HttpStatus.OK);
    }



}
