package TextProcessing;

// Paquetes importados
import java.util.*;

/**
 * Clase FileProcessor
 * 
 * @author JuanSerberoCode
 */
public class FileProcessor extends Thread {

    // Atributos
    private FileContents contents;
    private WordFrequencies frecuencies;

    /**
     * Metodo que inicializa un objeto estableciendo
     * el FileContents y WordFrequencies que usará en su ejecución
     * 
     * @param fileContents    Objeto FileContents
     * @param wordFrecuencies Objeto WordFrequencies
     */
    public FileProcessor(FileContents fileContents, WordFrequencies wordFrecuencies) {
        contents = fileContents;
        frecuencies = wordFrecuencies;
    }

    /**
     * Metodo para leer un fichero y almacena el resultado
     * en un objeto WordFrequencies
     */
    public void run() {
        String content;
        while ((content = contents.getContents()) != null) {
            Map<String, Integer> wordFrecuencies = new HashMap<String, Integer>();
            String[] splitWords = content.split("[^A-Za-z0-9ñÑÀ-ÿ]+");
            for (String i : splitWords) {
                if (wordFrecuencies.containsKey(i)) {
                    wordFrecuencies.put(i, wordFrecuencies.get(i) + 1);
                } else {
                    wordFrecuencies.put(i, 1);
                }
            }
            frecuencies.addFrequencies(wordFrecuencies);
        }
    }
}
