package VectorOrdenado;

/**
 * Clase ContenedorDeEnteros
 * 
 * @author JuanSerberoCode
 */
public class ContenedorDeEnteros {

	/**
	 * Atributos
	 */
	private int maxLen; // Longitud del vector
	private int[] vector; // Array del vector
	private int actualLen; // Numero de elementos del array

	/**
	 * Constructor del vector
	 * 
	 * @param maxLen Numero entero que indica la longitud del array
	 */
	public ContenedorDeEnteros(int maxLen) {
		this.maxLen = maxLen;
		this.vector = new int[this.maxLen];
		this.actualLen = 0;
	}

	/**
	 * Metodo para saber el numero de elementos que posee el array
	 * 
	 * @return this.atualLen Numero de elementos del array
	 */
	public int cardinal() {
		return this.actualLen;
	}

	/**
	 * Metodo que inserta el numero entero indicado por parametro en el vector
	 * 
	 * @param elemento Numero entero a insertar
	 * @return true/false True si se inserta correctamente / False si no se inserta
	 *         correctamente
	 */
	public boolean insertar(int elemento) {
		if (this.actualLen == this.maxLen) {
			return false;
		}
		int indice = 0, limInf = 0, limSup = this.actualLen - 1;
		while (limInf <= limSup) {
			int medio = (limInf + limSup) / 2;
			if (this.vector[medio] == elemento) {
				return false;
			} else if (this.vector[medio] < elemento) {
				limInf = medio + 1;
			} else {
				limSup = medio - 1;
			}
			indice = limInf;
		}
		for (int i = actualLen; i > indice; i--) {
			this.vector[(i - 1) + 1] = this.vector[(i - 1)];
		}
		this.vector[indice] = elemento;
		this.actualLen++;
		return true;
	}

	/**
	 * Metodo que extrae el numero entero indicado por parametro
	 * 
	 * @param elemento Numero entero a extraer
	 * @return true/false True si se extrae correctamente / False si no se extrae
	 *         correctamente
	 */
	public boolean extraer(int elemento) {
		if (this.actualLen == 0) {
			return false;
		}
		int indice = 0;
		int limInf = 0;
		int limSup = this.actualLen - 1;
		while (limInf <= limSup) {
			int medio = (limInf + limSup) / 2;
			indice = medio;
			if (this.vector[medio] == elemento) {
				break;
			} else if (this.vector[medio] < elemento) {
				limInf = medio + 1;
			} else {
				limSup = medio - 1;
			}
		}
		if (limInf > limSup) {
			return false;
		}

		for (int i = indice; i < this.actualLen - 1; i++) {
			this.vector[i] = this.vector[i + 1];
		}
		this.actualLen--;
		return true;
	}

	/**
	 * Metodo que realiza una busqueda dicotomica para hallar el numero entero
	 * indicado por parametro
	 * 
	 * @param elemento Numero entero a buscar
	 * @return true/false True si se encuentra / False si no se encuentra
	 */
	public boolean buscar(int elemento) {
		int limInf = 0;
		int limSup = this.actualLen - 1;
		while (limInf <= limSup) {
			int medio = (limInf + limSup) / 2;
			if (this.vector[medio] == elemento) {
				return true;
			} else if (this.vector[medio] < elemento) {
				limInf = medio + 1;
			} else {
				limSup = medio - 1;
			}
		}
		return false;
	}

	/**
	 * Metodo que vacia el vector
	 */
	public void vaciar() {
		this.actualLen = 0;
	}

	/**
	 * Metodo que devuelve un array con todos los elementos del vector
	 * 
	 * @return aux Array con los elementos del vector
	 */
	public int[] elementos() {
		int[] aux = new int[this.actualLen];
		for (int i = 0; i < this.actualLen; i++) {
			aux[i] = this.vector[i];
		}
		return aux;
	}

}
