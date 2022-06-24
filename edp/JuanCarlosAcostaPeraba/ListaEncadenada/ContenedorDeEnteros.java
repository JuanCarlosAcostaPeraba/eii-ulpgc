package ListaEncadenada;

/**
 * Clase ContenedorDeEnteros
 * 
 * @author JuanSerberoCode
 */
public class ContenedorDeEnteros {

    /**
     * Clase para representar un elemento de la lista
     */
    class Nodo {
        // Atributos
        int info; // valor del elemento
        Nodo sig; // nodo siguiente en la lista
    }

    // Atributos
    private Nodo raiz; // nodo raíz de la lista
    private int tamano; // número de elementos totales que hay en la lista

    /**
     * Constructor del contenedor
     */
    public ContenedorDeEnteros() {
        raiz = null;
        tamano = 0;
    }

    /**
     * Devuelve el tamaño de la lista
     * 
     * @return this.tamano Número de elementos que tiene la lista
     */
    public int cardinal() {
        return this.tamano;
    }

    /**
     * Inserta un elemento al final de la lista si no se encuentra en ella
     * 
     * @param i Elemento a añadir a la lista
     * @return true/false TRUE si lo añade, FALSE si no lo añade
     */
    public boolean insertar(int i) {
        if (buscar(i)) { // busca el elemento
            return false;
        }

        Nodo actual = new Nodo(); // crea nuevo nodo
        actual.info = i; // establece el valor del nodo

        if (raiz == null) { // comprueba la raiz
            actual.sig = null;
            raiz = actual;
        } else {
            actual.sig = raiz;
            raiz = actual;
        }

        tamano++; // aumenta el tamaño de la lista
        return true;
    }

    /**
     * Extrae un número de la lista si se encuentra en ella
     * 
     * @param i El número a extraer
     * @return true/false TRUE si se extrae, FALSE si no se extrae
     */
    public boolean extraer(int i) {
        Nodo actual = raiz;
        Nodo anterior = null;
        while (actual != null) {
            if (actual.info == i) {
                if (anterior != null) {
                    anterior.sig = actual.sig;
                } else {
                    raiz = raiz.sig;
                }
                tamano--;
                return true;
            }
            anterior = actual;
            actual = actual.sig;
        }
        return false;
    }

    /**
     * Busca el número indicado en la lista encadenada
     * 
     * @param i Número a buscar
     * @return true/false TRUE si se encuentra, FALSE si no se encuentra
     */
    public boolean buscar(int i) {
        Nodo actual = raiz;
        while (actual != null) {
            if (actual.info == i) {
                return true;
            }
            actual = actual.sig;
        }
        return false;
    }

    /**
     * Deja vacia la lista (tamano = 0)
     */
    public void vaciar() {
        raiz = null;
        tamano = 0;
    }

    /**
     * Devuelve un vector con los elementos de la lista
     * 
     * @return vector Vector con los elementos de la lista
     */
    public int[] elementos() {
        int[] vector = new int[cardinal()];
        int pos = 0;
        Nodo actual = raiz;
        while (actual != null) {
            vector[pos] = actual.info;
            actual = actual.sig;
            pos++;
        }
        return vector;
    }
}
