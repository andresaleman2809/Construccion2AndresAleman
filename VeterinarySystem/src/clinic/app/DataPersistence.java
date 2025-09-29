package clinic.app;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataPersistence {
    
    // Constantes para los nombres de los archivos
    public static final String PEOPLE_FILE = "people_data.ser";
    public static final String PETS_FILE = "pets_data.ser";
    public static final String RECORDS_FILE = "records_data.ser";
    public static final String INVOICES_FILE = "invoices_data.ser";

    // --- GUARDAR DATOS (SERIALIZACIÓN) ---
    public static <T> void saveData(List<T> dataList, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            
            objectOut.writeObject(dataList);
            System.out.println("✅ Datos de " + fileName + " guardados.");
            
        } catch (IOException i) {
            System.err.println("❌ ERROR al guardar " + fileName + ": " + i.getMessage());
        }
    }

    // --- CARGAR DATOS (DESERIALIZACIÓN) ---
    @SuppressWarnings("unchecked")
    public static List<?> loadData(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("⚠️ Archivo " + fileName + " no encontrado. Lista vacía.");
            return new ArrayList<>();
        }
        
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            
            List<?> loadedList = (List<?>) objectIn.readObject();
            System.out.println("💾 Datos de " + fileName + " cargados.");
            return loadedList;
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ ERROR al cargar " + fileName + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}