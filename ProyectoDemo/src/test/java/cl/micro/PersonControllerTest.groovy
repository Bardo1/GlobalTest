package cl.micro

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import cl.micro.domain.Person
import cl.micro.domain.Phone
import cl.micro.presentation.PersonController
import cl.micro.response.PersonResponse
import org.springframework.http.HttpEntity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.client.RestTemplate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.core.ParameterizedTypeReference;

import cl.micro.service.PersonService;


/**
 * @author Walter Muñoz.
 */
class PersonControllerTest extends Specification {

    MockMvc mockMvc;
	
	@Autowired(required = false)
	private PersonService personService;

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PersonController()).build()
    }
	
	
	//si se vuelve a ejecutar cuando está en runtime, se debe cambiar el mail de la persona a probar
	def "Deberia verificar el correcto ingreso de una persona"() {
		
		given:
		Person person1 = new Person("Paersona c1a");
		person1.setEmail("ak123aa111@aqhha.com");
		person1.setPassword("A11a1qyy36");
		person1.setToken("asdasad");
		
		Set<Phone> Phone1 = new HashSet<>();
		Phone1.add(new Phone("PhoneQ A1","D","S",person1));
		Phone1.add(new Phone("Phone A2","D","S",person1));
		Phone1.add(new Phone("Phone A3","D","S",person1));
		person1.setPhones(Phone1);
		HttpEntity<Person> requestEntity = new HttpEntity<>(person1);
		
		when: "Cuando se haga la llamada a nuestro MS, y este corriendo"
		RestTemplate restTemplate = new RestTemplate()
		ResponseEntity<PersonResponse> response = restTemplate.exchange("http://localhost:8080/persons",
			HttpMethod.POST,
			requestEntity,
			PersonResponse.class);
		
		then: "Verificamos que si existe"
		response!=null
	}
	
	
	
	def "Deberia traer la lista de personas y verificar si hay mas de 1 cargada"() {
		
		RestTemplate restTemplate = new RestTemplate()
		
		when: "Cuando se haga la llamada a nuestro MS, y este corriendo"
		ResponseEntity<List<Person>> response = restTemplate.exchange(
			"http://localhost:8080/persons",
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<Person>>() {});
		
		then: "Verificamos que si existe"
		response.getBody().size() > 0
	}
	

	def "Prueba de integración de Spock con mockmvc"() {
		when:
		def response = mockMvc.perform(get("/persons/hello"))
		
		then:
		response.andExpect(status().isOk())
				.andExpect(content().string("hello"))
				
		response.andReturn().response.contentAsString == "hello"
	}
	
	
	
	void 'Deberia lanzar una exception'() {
		when:
			Long.valueOf("foo")

		then:
			thrown NumberFormatException
	}
	
	
	/*
	 *
	 *
	 *
	def " Daaata Prueba de inserción de una persona, con mockmvc"() {
	   
		given:
		def Map data = [
			name  : '1111',
			email : 'asdf@gmail.com',
			password   : 'asd',
			phones: [
				[number:'Judith',citycode:'9',countrycode:'57'],
				[number:'Adriana',citycode:'6',countrycode:'12']
			]
		]
		
		when:
		def results = mockMvc.perform(post('/persons').contentType(APPLICATION_JSON).content(toJson(data))).andReturn().response    // 4

		then:
		results.andExpect(status().isCreated())
	}
	 * {
	"uuid": "856e239e-762b-4811-a0d5-655158a3499b",
	"modifiedDate": "02-02-2020 04:28:31",
	"lastLogin": "02-02-2020 04:28:31",
	"creationDate": "02-02-2020 04:28:31",
	"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NDQ0NCIsInJvbGVzIjpbIkFETUlOIiwiVVNFUiJdLCJpYXQiOjE1ODA2NzE3MTEsImV4cCI6MTU4MDY3NTMxMX0.GqywcLfNaFWN1plsEke6x-gPlrtoHf31XTRJDml6MiI",
	"isactive": true
	   }
	 */


}