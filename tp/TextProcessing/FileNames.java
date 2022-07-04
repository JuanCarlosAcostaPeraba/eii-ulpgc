package TextProcessing;

// Paquetes importados
import java.util.*;

/**
 * Clase FileNames
 * 
 * @author JuanSerberoCode
 */
public class FileNames {

    // Atributos
    private Queue<String> queue = new LinkedList<String>();
    private boolean moreNames = true;

    /**
     * Metodo que almacena un nuevo nombre de fichero en el objeto
     * 
     * @param fileName Nombre del fichero
     */
    public synchronized void addName(String fileName) {
        queue.add(fileName);
        notify();
    }

    /**
     * Metodo que extrae del objeto y devuelve un nombre de fichero
     * Cuando no hay ficheros disponibles el hilo solicitante debe
     * esperar a que cambie la situación, salvo que se haya llamado
     * a noMoreNames(), en cuyo caso inmediatamente se devuelve null
     * 
     * @return nombre del fichero / null si se ha llamado a noMoreNames() / error
     *         from catch
     */
    public synchronized String getName() {
        while (queue.isEmpty()) {
            if (!moreNames) {
                return null;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return queue.poll();
    }

    /**
     * Metodo que da lugar a que el objeto no admita más nombres de fichero
     */
    public synchronized void noMoreNames() {
        moreNames = false;
        notifyAll();
    }
}