package cl.micro;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cl.micro.domain.Person;
import cl.micro.domain.Phone;
import cl.micro.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProyectoDemoApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ProyectoDemoApplication.class);

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProyectoDemoApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        // save a couple of categories
        final Person person1 = new Person("Persona A");
        person1.setEmail("ak123@aqhha.com");
        person1.setPassword("Aqyy36");
        person1.setToken("asdasd");
        
        Set<Phone> Phone1 = new HashSet<>();
        Phone1.add(new Phone("Phone A1","D","S",person1));
        Phone1.add(new Phone("Phone A2","D","S",person1));
        Phone1.add(new Phone("Phone A3","D","S",person1));
        person1.setPhones(Phone1);

        final Person person2 = new Person("Persona B");
        person2.setEmail("11ak121@aqhha.com");
        person2.setPassword("11Aqyy36");
        person2.setToken("11asdasd");
        Set<Phone> Phone2 = new HashSet<>();
        Phone2.add(new Phone("Phone B1","D","S", person2));
        Phone2.add(new Phone("Phone B2","D","S", person2));
        Phone2.add(new Phone("Phone B3","D","S", person2));
        person2.setPhones(Phone2);

        personRepository.saveAll(Arrays.asList(person1, person2));

        // fetch all persons
        for (Person person : personRepository.findAll()) {
            logger.info(person.toString());
        }
    }
}
