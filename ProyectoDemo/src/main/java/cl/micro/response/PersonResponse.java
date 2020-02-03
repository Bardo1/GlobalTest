package cl.micro.response;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;


/**
Clase que representa la respuesta a la creación de una entidad Persona. 
Además utiliza la libreria time para gestionar los tipos de fecha con Java 8
 */
public class PersonResponse{
	
    private UUID uuid; //ID GENERADO
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime modifiedDate; // fecha de ultima modificación
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime lastLogin; //fecha de ultima entrada
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDateTime creationDate;//FECHA DE CREACIÓN
	private String token; //Token generado
	private boolean isactive; // Validación de activo
	
	public PersonResponse() {
	
		this.creationDate = LocalDateTime.now();
		this.lastLogin = this.creationDate;
		this.modifiedDate= this.creationDate;
	}
    
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
	
	
	
	
	


	