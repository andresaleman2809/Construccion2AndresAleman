package clinic.app; // Asegúrate de que este sea el paquete correcto

import java.io.Serializable;

public class Pet implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    // 1. ATRIBUTOS (Declaración ÚNICA de variables)
    private int petId;
    private int ownerId; 
    private String name;
    private int age;
    private String species;
    private String breed;
    private String color;
    private double weight; 

    // 2. CONSTRUCTOR
    public Pet(int petId, int ownerId, String name, int age, String species, String breed, String color, double weight) {
        this.petId = petId;
        this.ownerId = ownerId;
        this.name = name;
        this.age = age;
        this.species = species;
        this.breed = breed;
        this.color = color;
        this.weight = weight;
    }
    
    // 3. GETTERS Y SETTERS (Todos los métodos de acceso)
    
    public int getPetId() { return petId; }
    public void setPetId(int petId) {
		this.petId = petId;
	}

    public int getOwnerId() { return ownerId; }
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

    public String getName() { return name; }
	public void setName(String name) {
		this.name = name;
	}
    
    public int getAge() { return age; }
	public void setAge(int age) {
		this.age = age;
	}

	public String getSpecies() { return species; }
	public void setSpecies(String species) {
		this.species = species;
	}

	public String getBreed() { return breed; }
	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getColor() { return color; }
	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() { return weight; }
	public void setWeight(double weight) {
		this.weight = weight;
	}

    // Método estático
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}