package clinic.app;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Optional; 

public class VeterinarySystem { 
    
    public static void main(String[] args) {
        
        System.out.println("--- SISTEMA VETERINARIO INTERACTIVO ---");
        
        ClinicManager manager = new ClinicManager();
        Scanner scanner = new Scanner(System.in);
        int option;
        
        // Bucle principal del menú
        do {
            System.out.println("\n-------------------------------------");
            System.out.println("           MENÚ PRINCIPAL            ");
            System.out.println("-------------------------------------");
            System.out.println("1. Crear Nueva Persona (Dueño/Vet)");
            System.out.println("2. Crear Nueva Mascota");
            System.out.println("3. Crear Registro Médico");
            System.out.println("4. Buscar Registros por Mascota");
            System.out.println("5. Mostrar todas las Mascotas");
            System.out.println("6. Crear Factura ");
            System.out.println("7. Buscar Facturas por Mascota"); // Nueva Opción
            System.out.println("0. SALIR Y GUARDAR DATOS");
            System.out.print("Seleccione una opción: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea pendiente

                switch (option) {
                    case 1:
                        createPerson(manager, scanner);
                        break;
                    case 2:
                        createPet(manager, scanner);
                        break;
                    case 3:
                        createMedicalRecord(manager, scanner);
                        break;
                    case 4:
                        searchMedicalRecords(manager, scanner);
                        break;
                    case 5:
                        displayAllPets(manager);
                        break;
                    case 6:
                        createOrderInvoice(manager, scanner);
                        break;
                    case 7: // Llamada al nuevo método de búsqueda de facturas
                        searchInvoices(manager, scanner);
                        break;
                    case 0:
                        System.out.println("\n--- FINALIZANDO Y GUARDANDO DATOS ---");
                        manager.saveAllData();
                        System.out.println("¡Datos guardados! Adiós.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                scanner.nextLine(); 
                option = -1; 
            }
        } while (option != 0);
        
        scanner.close();
    }
    
// --------------------------------------------------------------------------
// MÉTODOS DE CREACIÓN INTERACTIVA (Todos son STATIC)
// --------------------------------------------------------------------------

    private static void createPerson(ClinicManager manager, Scanner scanner) {
        try {
            System.out.println("\n--- CREAR NUEVA PERSONA ---");
            System.out.print("ID de Persona: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nombre: ");
            String name = scanner.nextLine();
            
            String role = "";
            while (!role.equalsIgnoreCase("Owner") && !role.equalsIgnoreCase("Vet")) {
                System.out.print("Rol (Owner o Vet): ");
                role = scanner.nextLine();
            }
            
            System.out.print("Contacto (teléfono/email): ");
            String contact = scanner.nextLine();
            
            Person newPerson = new Person(id, name, role, contact);
            manager.addPerson(newPerson);
            System.out.println("✅ Persona (" + role + ") creada con ID: " + id);
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Error de formato en el ID. Intente de nuevo.");
            scanner.nextLine();
        }
    }

    private static void createPet(ClinicManager manager, Scanner scanner) {
        try {
            System.out.println("\n--- CREAR NUEVA MASCOTA ---");
            System.out.print("ID de Mascota: ");
            int petId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("ID del Dueño (debe existir y ser Owner): ");
            int ownerId = scanner.nextInt();
            scanner.nextLine();
            
            // Asumimos que ClinicManager tiene findPersonById
            if (manager.findPersonById(ownerId).filter(p -> p.getRole().equalsIgnoreCase("Owner")).isEmpty()) {
                System.out.println("❌ Error: El Dueño con ID " + ownerId + " no existe o no tiene el rol 'Owner'.");
                return;
            }
            
            System.out.print("Nombre de la Mascota: ");
            String name = scanner.nextLine();
            
            System.out.print("Edad: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Especie (Perro/Gato, etc.): ");
            String species = scanner.nextLine();
            
            System.out.print("Raza: ");
            String breed = scanner.nextLine();
            
            System.out.print("Color: ");
            String color = scanner.nextLine();
            
            System.out.print("Peso (en kg, ej: 15.5): ");
            double weight = scanner.nextDouble();
            scanner.nextLine();

            Pet newPet = new Pet(petId, ownerId, name, age, species, breed, color, weight);
            manager.addPet(newPet); 
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Error de formato en un campo numérico. Intente de nuevo.");
            scanner.nextLine();
        }
    }

    private static void createMedicalRecord(ClinicManager manager, Scanner scanner) {
        try {
            System.out.println("\n--- CREAR REGISTRO MÉDICO ---");
            
            System.out.print("ID de Registro (ej: 6001): ");
            int recordId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("ID de Mascota: ");
            int petId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("ID de Veterinario (Vet): ");
            int vetId = scanner.nextInt();
            scanner.nextLine();
            
            // Las validaciones se manejan en ClinicManager.addMedicalRecord()

            System.out.print("Síntomas: ");
            String symptoms = scanner.nextLine();
            
            System.out.print("Diagnóstico: ");
            String diagnosis = scanner.nextLine();
            
            System.out.print("Procedimiento/Pruebas: ");
            String procedure = scanner.nextLine();
            
            System.out.print("Medicación: ");
            String medication = scanner.nextLine();
            
            System.out.print("Notas de seguimiento: ");
            String details = scanner.nextLine();

            MedicalRecord record = new MedicalRecord(
                recordId, 
                LocalDate.now(), 
                petId,
                vetId,
                symptoms,
                diagnosis,
                procedure,
                medication,
                details
            );
            manager.addMedicalRecord(record);
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Error de formato en un campo numérico. Intente de nuevo.");
            scanner.nextLine();
        }
    }
    
    private static void createOrderInvoice(ClinicManager manager, Scanner scanner) {
        try {
            System.out.println("\n--- CREAR NUEVA FACTURA ---");
            
            System.out.print("ID de Factura (ej: 8001): ");
            int invoiceId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("ID de Mascota: ");
            int petId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("ID de Veterinario (Vet): ");
            int vetId = scanner.nextInt();
            scanner.nextLine();
            
            // Las validaciones se manejan en ClinicManager.addOrderInvoice()

            System.out.print("Descripción del Servicio: ");
            String description = scanner.nextLine();

            System.out.print("Costo Total (ej: 75.50): ");
            double totalCost = scanner.nextDouble();
            scanner.nextLine();
            
            OrderInvoice invoice = new OrderInvoice(
                invoiceId, 
                petId, 
                vetId, 
                LocalDate.now(), 
                description, 
                totalCost
            );
        
            manager.addOrderInvoice(invoice);
        
        } catch (InputMismatchException e) {
            System.out.println("❌ Error de formato en un campo numérico. Intente de nuevo.");
            scanner.nextLine();
        }
    }

// --------------------------------------------------------------------------
// MÉTODOS DE BÚSQUEDA Y VISUALIZACIÓN (Todos son STATIC)
// --------------------------------------------------------------------------

    private static void searchMedicalRecords(ClinicManager manager, Scanner scanner) {
        try {
            System.out.println("\n--- BÚSQUEDA DE REGISTROS ---");
            System.out.print("Ingrese el ID de la Mascota para buscar sus registros: ");
            int petId = scanner.nextInt();
            scanner.nextLine();
            
            List<MedicalRecord> records = manager.getRecordsByPetId(petId);
            
            if (!records.isEmpty()) {
                System.out.println("\n✅ Se encontraron " + records.size() + " registros para la Mascota ID " + petId + ":");
                for (MedicalRecord r : records) {
                    Optional<Person> vet = manager.findPersonById(r.getVetId());
                    String vetName = vet.isPresent() ? vet.get().getName() : "Desconocido";

                    System.out.println("-------------------------------------");
                    System.out.println("  Registro #" + r.getRecordId() + " (Fecha: " + r.getDate() + ")");
                    System.out.println("  Veterinario: " + vetName);
                    System.out.println("  Diagnóstico: " + r.getDiagnosis());
                    System.out.println("  Medicación: " + r.getMedication());
                }
                System.out.println("-------------------------------------");
            } else {
                System.out.println("⚠️ No se encontraron registros médicos para la Mascota ID " + petId);
            }
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Error de formato en el ID. Intente de nuevo.");
            scanner.nextLine();
        }
    }
    
    private static void displayAllPets(ClinicManager manager) {
        List<Pet> allPets = manager.getAllPets(); 

        if (allPets.isEmpty()) {
            System.out.println("⚠️ No hay mascotas guardadas en el sistema.");
            return;
        }
        
        System.out.println("\n--- LISTADO COMPLETO DE MASCOTAS CARGADAS ---");
        for (Pet p : allPets) {
            System.out.println("ID: " + p.getPetId() + 
                               " | Dueño ID: " + p.getOwnerId() + 
                               " | Nombre: " + p.getName() + 
                               " | Especie: " + p.getSpecies() + 
                               " | Peso: " + p.getWeight() + " kg");
        }
    }
    
    private static void searchInvoices(ClinicManager manager, Scanner scanner) {
	    try {
	        System.out.println("\n--- BÚSQUEDA DE FACTURAS ---");
	        System.out.print("Ingrese el ID de la Mascota para buscar sus facturas: ");
	        int petId = scanner.nextInt();
	        scanner.nextLine();
	        
	        // Asegúrate de que este método exista en ClinicManager.java
	        List<OrderInvoice> invoices = manager.getInvoicesByPetId(petId); 
	        
	        if (!invoices.isEmpty()) {
	            System.out.println("\n✅ Se encontraron " + invoices.size() + " facturas para la Mascota ID " + petId + ":");
	            for (OrderInvoice i : invoices) {
	                // Buscar el nombre del veterinario para mostrarlo
	                Optional<Person> vet = manager.findPersonById(i.getVetId());
	                String vetName = vet.isPresent() ? vet.get().getName() : "Desconocido";

	                System.out.println("-------------------------------------");
	                System.out.println("  Factura #" + i.getInvoiceId() + " (Fecha: " + i.getDate() + ")");
	                System.out.println("  Veterinario: " + vetName);
	                System.out.println("  Descripción: " + i.getDescription());
	                System.out.println("  Costo Total: $" + i.getTotalCost());
	            }
	            System.out.println("-------------------------------------");
	        } else {
	            System.out.println("⚠️ No se encontraron facturas para la Mascota ID " + petId);
	        }
	        
	    } catch (InputMismatchException e) {
	        System.out.println("❌ Error de formato en el ID. Intente de nuevo.");
	        scanner.nextLine();
	    }
	}
}