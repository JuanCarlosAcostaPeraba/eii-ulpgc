package TextProcessing;

// Paquetes importados
import java.util.*;

/**
 * Clase FileContents
 * 
 * @author JuanSerberoCode
 */
public class FileContents {

    // Atributos
    private Queue<String> queue = new LinkedList<String>();
    private int registerCount = 0;
    private boolean closed = false;
    private int files;
    private int chars;

    /**
     * Constructor
     * 
     * @param maxFiles numero maximo de archivos que se pueden almacenar
     * @param maxChars tamano total maximo de las ristras
     *                 que se pueden almacenar
     */
    public FileContents(int maxFiles, int maxChars) {
        files = maxFiles;
        chars = maxChars;
    }

    /**
     * Metodo para indicar que hay un nuevo FileReader usando el objeto
     */
    public synchronized void registerWriter() {
        registerCount++;
    }

    /**
     * Metodo que indica que un FileReader ha dejado de producir contenido
     * Si el contador llega a 0, no se
     */
    public synchronized void unregisterWriter() {
        registerCount--;
        if (registerCount == 0) {
            closed = true; // no recibe contenido
        }
        notifyAll();
    }

    /**
     * Metodo para añadir el contenido de un fichero.
     * Si se ha alcanzado el límite de ficheros
     * el hilo se espera hasta que cambie la situación
     * 
     * @param contents
     */
    public synchronized void addContents(String contents) {
        if (!closed) {
            while ((files <= queue.size()) ||
                    (chars <= contents.length()) &&
                            (!queue.isEmpty())) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            queue.add(contents);
        }
        notify();
    }

    /**
     * Metodo para extraer el contenido de un fichero.
     * Si no hay contenido disponible, el hilo se espera a que lo haya,
     * salvo que el último FileReader se haya desregistrado,
     * entonces se devuelve null.
     * 
     * @return Datos del fichero / null si el fichero se ha desregistrado / error
     *         from catch
     */
    public synchronized String getContents() {
        while (queue.isEmpty()) {
            if (closed) {
                return null;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        notifyAll();
        return queue.poll();
    }
}
