package clinic.app; // Asegúrate de que este sea el paquete correcto

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    // 1. ATRIBUTOS (Variables de Instancia) - ¡Todo junto aquí!
    private int personId;
    private String name;
    private String role; 
    private String contact;
    
    // 2. CONSTRUCTOR
    public Person(int personId, String name, String role, String contact) {
        this.personId = personId;
        this.name = name;
        this.role = role;
        this.contact = contact;
    }
    
    // 3. GETTERS Y SETTERS
    
    public int getPersonId() { return personId; }
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() { return role; }
    public void setRole(String role) {
        this.role = role;
    }

    public String getContact() { return contact; }
    public void setContact(String contact) {
        this.contact = contact;
    }

    // Método estático movido a la sección de métodos
    public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    // 4. MÉTODO toString
    @Override
    public String toString() {
        return "ID: " + personId + ", Nombre: " + name + ", Rol: " + role;
    }
}