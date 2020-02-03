package cl.micro.domain;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {
	
	 @Id
	 @GeneratedValue(generator = "uuid2")
	 @GenericGenerator(name = "uuid", strategy = "uuid2")
	 @Type(type="uuid-char")
	 @Column(length = 36)
	 private UUID uuid;

     @Column(name="name")
     private String name;
       
	 @Column(name="email", nullable=false, length=200)
	 private String email;
     
     @Column(name="password")
	 private String password;
     
     @Column(name="token")
	 private String token;
    
     @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JsonManagedReference
     private Set<Phone> phones;
    
    
    public String getEmail() {
 		return email;
 	}

 	public UUID getUuid() {
 		return uuid;
 	}

 	public void setUuid(UUID uuid) {
 		this.uuid = uuid;
 	}

 	public void setEmail(String email) {
 		this.email = email;
 	}

 	public String getPassword() {
 		return password;
 	}

 	public void setPassword(String password) {
 		this.password = password;
 	}

 	public String getToken() {
 		return token;
 	}

 	public void setToken(String token) {
 		this.token = token;
 	}

 	public Set<Phone> getPhones() {
 		return phones;
 	}

 	public void setPhones(Set<Phone> phones) {
 		this.phones = phones;
 	}

    public Person(){

    }

    public Person(String name) {
        this.name = name;
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [uuid=");
		builder.append(uuid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", token=");
		builder.append(token);
		builder.append(", phones=");
		builder.append(phones);
		builder.append("]");
		return builder.toString();
	}
}
