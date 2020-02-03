package cl.micro.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import cl.micro.config.JwtTokenProvider;
import cl.micro.domain.Person;
import cl.micro.exception.ServiceException;
import cl.micro.repository.PersonRepository;
import cl.micro.response.PersonResponse;
 
@Service
public class PersonService {
    
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);  
    
    public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(.*[AZ].*)(?=\\w*[a-z])(?=.*\\d{2,})\\S{4,}$", Pattern.CASE_INSENSITIVE);  
        
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    PersonRepository repository;
     
    public List<Person> getAllPersons()
    {
    	LOGGER.info("se ejecuta getAllPersons");
        List<Person> personList = repository.findAll();
        if(personList.size() > 0){
        	//ordenamiento de lista de obetos personas con expresión Lambda de Java 8
            Collections.sort(personList, (o1, o2) -> o2.getUuid().compareTo(o1.getUuid()));
            return personList;
        } else {
    		throw new ServiceException(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),"La lista de personas se encuentra vacia.");
        }
    }
     
    public PersonResponse createPerson(Person entity)
    {	
    	LOGGER.info("Se ejecuta createPerson");
    	Person p = repository.findByEmail(entity.getEmail());
        //validación de correo repetido    	
    	if(p!=null) {
    		throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"El email ya se encuetran ingresado en el sistema.");
    	}
        //validación de password
    	if(!validarPassword(entity.getPassword())) {
    		throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Error en el formato del password.");
    	}
    	//validacion de formato de correo
    	if(!validarMail(entity.getEmail())) {
    		throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Error en formato de email.");
    	}
    	 
    	LOGGER.info("Pasa las validaciónes de parámetros de entrada");
	    //Crea el token con las credenciales de entrada 	
        String token= crearToken(entity);
        entity.setToken(token);
        PersonResponse response= new PersonResponse();
        repository.saveAll(Arrays.asList(entity));
        //Hacemos la validación del token por el proveedor.
        boolean validaToken =jwtTokenProvider.validateToken(token);
        response.setUuid(entity.getUuid());
        response.setToken(token);
        response.setIsactive(validaToken);
    	LOGGER.info("Crea token con el nombre de la persona y lo asocia a esta. Persiste la persona y sus teléfonos a la vez.");
        return response;
    }
     
    public static boolean validarPassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX .matcher(password);
        return matcher.find();
    }
    
    public static boolean validarMail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
    
    public String crearToken(Person per) {
        try {
            String name = per.getName();
            List<String> roles= new ArrayList<String>(); // Colocamos los roles por defecto solo para esta prueba
            roles.add("ADMIN");
            roles.add("USER");
            String token = jwtTokenProvider.createToken(name, roles);
            return token;
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Person");
        }
    }
    
}