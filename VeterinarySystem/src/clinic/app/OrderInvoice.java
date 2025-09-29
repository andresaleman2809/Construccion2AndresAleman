package clinic.app;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // 1. ATRIBUTOS
    private int invoiceId;
    private int petId;
    private int vetId;
    private LocalDate date;
    private String description;
    private double totalCost;

    // 2. CONSTRUCTOR (Debe coincidir EXACTAMENTE con la llamada en VeterinarySystem)
    public OrderInvoice(int invoiceId, int petId, int vetId, LocalDate date, String description, double totalCost) {
        this.invoiceId = invoiceId;
        this.petId = petId;
        this.vetId = vetId;
        this.date = date;
        this.description = description;
        this.totalCost = totalCost;
    }

    // 3. MÉTODOS GETTERS (Necesarios para que VeterinarySystem pueda leer los datos)

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getPetId() {
        return petId;
    }
    
    public int getVetId() {
        return vetId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // Opcional: método toString para imprimir fácilmente el objeto completo
    @Override
    public String toString() {
        return "Factura #" + invoiceId + 
               " | Mascota ID: " + petId +
               " | Costo: $" + totalCost + 
               " | Descripción: " + description;
    }
}