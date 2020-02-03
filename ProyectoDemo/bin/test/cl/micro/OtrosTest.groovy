package cl.micro

import spock.lang.IgnoreIf
import spock.lang.Requires
import spock.lang.Specification
import spock.util.environment.Jvm
import spock.util.environment.OperatingSystem


class OtrosTest extends Specification {

    void 'Deberia invertir un string'() {
        given: 'a string'
            def myString = 'Hello World!'

        when: 'invirtiéndolo'
            def reversed = myString.reverse()

        then: 'está al revés'
            reversed == '!dlroW olleH'
    }

    void 'Deberia invertir un string (II)'() {
        expect:
            'Hello World!'.reverse() == '!dlroW olleH'
    }
	
	
	@Requires({ OperatingSystem.current.linux })
	void 'Deberia solo correr en Linux'() {
		expect:
			true
	}

	@Requires({ OperatingSystem.current.windows })
	void 'Deberia solo correr en Windows'() {
		expect:
			true
	}

	@IgnoreIf({ Jvm.current.java8Compatible })
	void 'Deberia ser ignorado en Java8+'() {
		expect:
			false
	}

	@IgnoreIf({ Jvm.current.java9 })
	void 'Deberia ser ignorado en Java9'() {
		expect:
			true
	}

	@Requires({ busquedaEnArreglo('Greach')})
	void 'deberia encontrarse en el arreglo'() {
		expect:
			true
	}

	static boolean busquedaEnArreglo(String conference) {
		conference in ['Greach', 'GR8Conf']
	}

}
