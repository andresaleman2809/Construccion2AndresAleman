package clinic.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClinicManager implements Serializable {
    private static final long serialVersionUID = 1L;

    // ATRIBUTOS
    private List<Person> people;
    private List<Pet> pets;
    private List<MedicalRecord> medicalRecords;
    private List<OrderInvoice> orderInvoices;

    /**
     * Constructor: Inicializa el Manager cargando todos los datos de persistencia.
     */
    @SuppressWarnings("unchecked") 
    public ClinicManager() {
        // Carga de datos al iniciar el Manager usando casting directo (solución al error de tipo)
        this.people = (List<Person>) DataPersistence.loadData(DataPersistence.PEOPLE_FILE);
        this.pets = (List<Pet>) DataPersistence.loadData(DataPersistence.PETS_FILE);
        this.medicalRecords = (List<MedicalRecord>) DataPersistence.loadData(DataPersistence.RECORDS_FILE);
        this.orderInvoices = (List<OrderInvoice>) DataPersistence.loadData(DataPersistence.INVOICES_FILE);

        // Si la carga devuelve null, inicializa con listas vacías
        if (this.people == null) this.people = new ArrayList<>();
        if (this.pets == null) this.pets = new ArrayList<>();
        if (this.medicalRecords == null) this.medicalRecords = new ArrayList<>();
        if (this.orderInvoices == null) this.orderInvoices = new ArrayList<>();
    }

    // --------------------------------------------------------------------------
    // MÉTODOS DE PERSISTENCIA
    // --------------------------------------------------------------------------

    public void saveAllData() {
        DataPersistence.saveData(this.people, DataPersistence.PEOPLE_FILE);
        DataPersistence.saveData(this.pets, DataPersistence.PETS_FILE);
        DataPersistence.saveData(this.medicalRecords, DataPersistence.RECORDS_FILE);
        DataPersistence.saveData(this.orderInvoices, DataPersistence.INVOICES_FILE);
    }

    // --------------------------------------------------------------------------
    // MÉTODOS CRUD BÁSICOS (PERSONAS)
    // --------------------------------------------------------------------------

    public void addPerson(Person p) {
        this.people.add(p);
        System.out.println("✅ Persona ID " + p.getPersonId() + " (" + p.getRole() + ") añadida.");
    }

    public Optional<Person> findPersonById(int personId) {
        return this.people.stream()
                .filter(p -> p.getPersonId() == personId)
                .findFirst();
    }

    // --------------------------------------------------------------------------
    // MÉTODOS CRUD BÁSICOS (MASCOTAS)
    // --------------------------------------------------------------------------

    public void addPet(Pet pet) {
        // Validar que el dueño (Owner) exista antes de añadir la mascota
        Optional<Person> ownerOpt = findPersonById(pet.getOwnerId());
        
        if (ownerOpt.isPresent() && ownerOpt.get().getRole().equalsIgnoreCase("Owner")) {
            this.pets.add(pet);
            System.out.println("✅ Mascota '" + pet.getName() + "' añadida al dueño ID " + pet.getOwnerId());
        } else {
            System.out.println("❌ Error: No se pudo añadir la mascota. El dueño ID " + pet.getOwnerId() + " no existe o no tiene el rol 'Owner'.");
        }
    }
    
    // --------------------------------------------------------------------------
    // MÉTODOS CRUD (REGISTROS MÉDICOS)
    // --------------------------------------------------------------------------

    public void addMedicalRecord(MedicalRecord record) {
        // 1. Verificar si la mascota existe
        boolean petExists = this.pets.stream().anyMatch(p -> p.getPetId() == record.getPetId());
        
        // 2. Verificar si el veterinario existe y tiene el rol "Vet"
        boolean vetExistsAndIsVet = this.people.stream()
            .anyMatch(p -> p.getPersonId() == record.getVetId() && p.getRole().equalsIgnoreCase("Vet"));

        if (petExists && vetExistsAndIsVet) {
            this.medicalRecords.add(record);
            System.out.println("✅ Registro Médico #" + record.getRecordId() + " añadido con éxito.");
        } else {
            System.out.println("❌ Error: No se pudo añadir el registro. Mascota o Veterinario (Vet) no existen.");
        }
    }

    /**
     * Busca y devuelve todos los registros médicos asociados a un Pet ID específico.
     */
    public List<MedicalRecord> getRecordsByPetId(int petId) {
        return this.medicalRecords.stream()
            .filter(record -> record.getPetId() == petId) 
            .collect(Collectors.toList()); 
    }
    
    // --------------------------------------------------------------------------
    // MÉTODOS CRUD (FACTURAS / INVOICES) - NUEVO Y ORDENADO
    // --------------------------------------------------------------------------

    public void addOrderInvoice(OrderInvoice invoice) {
        // 1. Verificar si la mascota existe
        boolean petExists = this.pets.stream().anyMatch(p -> p.getPetId() == invoice.getPetId());

        // 2. Verificar si el veterinario existe y tiene el rol "Vet"
        boolean vetExistsAndIsVet = this.people.stream()
            .anyMatch(p -> p.getPersonId() == invoice.getVetId() && p.getRole().equalsIgnoreCase("Vet"));

        if (petExists && vetExistsAndIsVet) {
            this.orderInvoices.add(invoice);
            System.out.println("✅ Factura #" + invoice.getInvoiceId() + " creada con éxito.");
        } else {
            System.out.println("❌ Error: No se pudo crear la factura. Mascota o Veterinario (Vet) no existen.");
        }
    }
    
    /**
     * Busca y devuelve todas las facturas asociadas a un Pet ID específico.
     */
    public List<OrderInvoice> getInvoicesByPetId(int petId) {
        return this.orderInvoices.stream()
            .filter(invoice -> invoice.getPetId() == petId) 
            .collect(Collectors.toList()); 
    }
    
    // --------------------------------------------------------------------------
    // MÉTODOS DE VISUALIZACIÓN
    // --------------------------------------------------------------------------

    /**
     * Devuelve la lista completa de todas las mascotas cargadas en el Manager.
     */
    public List<Pet> getAllPets() {
        return this.pets;
    }
}