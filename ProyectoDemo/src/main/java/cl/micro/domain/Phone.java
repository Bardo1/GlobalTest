package cl.micro.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Phone{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid")
    @JsonBackReference
    private Person person;
    
     @Column(name="number")
	 private String number;
	 
	 @Column(name="citycode")
	 private String citycode;   
	 
	 @Column(name="countrycode")
	 private String countrycode;
	 
	 
	  /**
	 * @param person
	 * @param number
	 * @param citycode
	 * @param countrycode
	 */
	public Phone( String number, String citycode, String countrycode,Person person) {
		this.person = person;
		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
	}

	public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}

    public Phone() {

    }

    public Phone(String name) {
    }

    public Phone(Person person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}




}
