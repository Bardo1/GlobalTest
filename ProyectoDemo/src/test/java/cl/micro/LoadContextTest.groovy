package cl.micro

import cl.micro.presentation.PersonController
import cl.micro.service.PersonService
import cl.micro.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("Application Specification")
@Narrative("Specification which beans are expected")
@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired(required = false)
    private PersonController personController;
	
	@Autowired(required = false)
	private PersonService personService;
	
	@Autowired(required = false)
	private PersonRepository personRepository;


    def "cuando se carga el contexto, se crean todos los beans esperados"() {
        expect: "the personController is created"
        personController
    }
	
	def "cuando se carga el contexto, se crean todos los beans esperados 1"() {
		expect: "the personService is created"
		personService
	}
	
	def "cuando se carga el contexto, se crean todos los beans esperados 2"() {
		expect: "the personRepository is created"
		personRepository
	}
}
