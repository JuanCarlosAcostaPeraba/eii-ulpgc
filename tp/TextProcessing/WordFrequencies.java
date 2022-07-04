package TextProcessing;

// Paquetes importados
import java.util.*;

/**
 * Clase WordFrequencies
 * 
 * @author JuanSerberoCode
 */
public class WordFrequencies {

    // Atributos
    private HashMap<String, Integer> frecuencia = new HashMap<String, Integer>();

    /**
     * Metodo que añade al objeto actual la información de palabras / frecuencias
     * pasada
     * 
     * @param frecCalc HashMap con las palabras y las fecuencias de las mismas
     */
    public synchronized void addFrequencies(Map<String, Integer> frecCalc) {
        for (String key : frecCalc.keySet()) {
            if (frecuencia.containsKey(key)) {
                frecuencia.put(key, frecCalc.get(key) + frecuencia.get(key));
            } else {
                frecuencia.put(key, frecCalc.get(key));
            }
        }
    }

    /**
     * Metodo que devuelve las parejas de palabras / frecuencias acumuladas
     * 
     * @return HashMap con las palabras y las frecuencias calculadas
     */
    public synchronized Map<String, Integer> getFrequencies() {
        return new HashMap<String, Integer>(frecuencia);
    }
}
