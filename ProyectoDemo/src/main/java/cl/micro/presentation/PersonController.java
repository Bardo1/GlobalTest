package cl.micro.presentation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.micro.domain.Person;
import cl.micro.response.PersonResponse;
import cl.micro.service.PersonService;
import io.swagger.annotations.ApiOperation;
 
@RestController
@RequestMapping("/persons")
public class PersonController
{ 
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
    PersonService service;
	
	
	@GetMapping(value = "/hello")
    public String salutation() {
        return "hello";
    }
 
    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getAllPersons() {
    	LOGGER.info("Inicia llamada a la lista de personas");
        List<Person> list = service.getAllPersons();
    	LOGGER.info("Termina llamada a la lista de personas");
        return new ResponseEntity<List<Person>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @ApiOperation(value = "Endpoint para el ingreso de una persona")
    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person){ 
    	LOGGER.info("Inicia la llamada para el ingreso de una persona");
        PersonResponse response = service.createPerson(person);
    	LOGGER.info("Termina la llamada para el ingreso de una persona");
        return new ResponseEntity<PersonResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }
        
}