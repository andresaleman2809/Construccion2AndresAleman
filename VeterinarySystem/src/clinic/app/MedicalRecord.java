	package clinic.app;
	
	import java.io.Serializable;
	import java.time.LocalDate;

	public class MedicalRecord implements Serializable {
	    private static final long serialVersionUID = 1L; 
	    
	    // 1. ATRIBUTOS (Declaración ÚNICA de variables - todo junto)
	    private int recordId;
	    private LocalDate date;
	    private int petId; 
	    private int vetId; 
	    private String symptoms;
	    private String diagnosis;
	    private String procedure;
	    private String medication;
	    private String details;

	    // 2. CONSTRUCTOR
	    public MedicalRecord(int recordId, LocalDate date, int petId, int vetId, String symptoms, String diagnosis, String procedure, String medication, String details) {
	        this.recordId = recordId;
	        this.date = date;
	        this.petId = petId;
	        this.vetId = vetId;
	        this.symptoms = symptoms;
	        this.diagnosis = diagnosis;
	        this.procedure = procedure;
	        this.medication = medication;
	        this.details = details;
	    }
	    
	    // 3. GETTERS Y SETTERS (Todos los métodos generados)
	    
	    public int getRecordId() { return recordId; }
	    public void setRecordId(int recordId) { this.recordId = recordId; }

	    public int getPetId() { return petId; }
	    public void setPetId(int petId) { this.petId = petId; }

	    public int getVetId() { return vetId; }
	    public void setVetId(int vetId) { this.vetId = vetId; }

	    public String getSymptoms() { return symptoms; }
	    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

	    public String getProcedure() { return procedure; }
	    public void setProcedure(String procedure) { this.procedure = procedure; }

	    public String getDetails() { return details; }
	    public void setDetails(String details) { this.details = details; }

	    public LocalDate getDate() { return date; }
		public void setDate(LocalDate date) { this.date = date; }

	    public String getDiagnosis() { return diagnosis; }
		public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

	    public String getMedication() { return medication; }
		public void setMedication(String medication) { this.medication = medication; }
	    
	    // Método que movimos para que no interrumpa los atributos:
	    public static long getSerialversionuid() {
			return serialVersionUID;
		}
	}