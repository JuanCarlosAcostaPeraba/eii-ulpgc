package tp.practicas;

/**
 * Clase ConjuntoDeStrings
 * 
 * @author JuanSerberoCode
 */
public class ConjuntoDeStrings {
	/**
	 * Atributos
	 */
	private String[] vector;
	private int cardinal;

	/**
	 * Constructor
	 * 
	 * @param args
	 */
	public ConjuntoDeStrings(String... args) {
		for (int i = 0; i < args.length; i++) { // recorre el array de Strings
			if (args[i] != null) {
				this.añade(args[i]);
			}
		}
	}

	/**
	 * Metodo que devuelve el numero
	 * de elementos del conjunto
	 * 
	 * @return cardinal
	 */
	public int cardinal() {
		return this.cardinal;
	}

	/**
	 * Metodo que dice si el array de strings esta vacio o lleno
	 * 
	 * @return true si esta vacio, false si esta lleno
	 */
	public boolean estáVacío() {
		if (this.cardinal() != 0) { // array lleno
			return false;
		} else { // array vacio
			return true;
		}
	}

	/**
	 * Metodo que mete nuevos elementos en el atributo vector
	 * 
	 * @param elemento String a meter
	 * @return true si se mete el nuevo elemento, false si NO se mete el nuevo
	 *         elemento
	 */
	public boolean añade(String elemento) {
		// comprobar que el elemento a meter no se repite
		if (this.pertenece(elemento)) { // se repite y NO se mete el elemento
			return false;
		} else { // no se repite y SI se mete el elemento
			int tempCardinal = this.cardinal() + 1;
			String[] tempVector = new String[tempCardinal];
			for (int i = 0; i < this.cardinal(); i++) {
				tempVector[i] = this.vector[i];
			}
			tempVector[tempCardinal - 1] = elemento;
			this.vector = tempVector;
			this.cardinal = tempCardinal;
			return true;
		}
	}

	/**
	 * Metodo para comprobar si el elemento buscado esta en el atributo vector
	 * 
	 * @param elemento String a comprobar si esta en el array
	 * @return true si se encuentra en el array, false si NO se encuentra en el
	 *         array
	 */
	public boolean pertenece(String elemento) {
		// comprobar que el elemento se encuentra en el atributo vector
		for (int i = 0; i < this.cardinal(); i++) {
			if (this.vector[i].equals(elemento)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para realizar la operacion de union de dos conjuntos
	 * 
	 * @param c ConjuntoDeStrings
	 * @return tempConjunto ConjuntoDeStrings
	 */
	public ConjuntoDeStrings unión(ConjuntoDeStrings c) {
		ConjuntoDeStrings tempConjunto = new ConjuntoDeStrings(this.elementos());
		for (int i = 0; i < c.cardinal(); i++) {
			if (!tempConjunto.pertenece(c.elementos()[i])) {
				tempConjunto.añade(c.elementos()[i]);
			}
		}
		return tempConjunto;
	}

	/**
	 * Metodo para realizar la operacion de interseccion de dos conjuntos
	 * 
	 * @param c ConjuntoDeStrings
	 * @return tempConjunto ConjuntoDeStrings
	 */
	public ConjuntoDeStrings intersección(ConjuntoDeStrings c) {
		int contador = 0;
		for (int i = 0; i < this.cardinal(); i++) {
			if (c.pertenece(this.elementos()[i])) {
				contador++;
			}
		}
		String[] tempVector = new String[contador];
		int indice = 0;
		for (int i = 0; i < this.cardinal(); i++) {
			if (c.pertenece(this.elementos()[i])) {
				tempVector[indice] = this.elementos()[i];
				indice++;
			}
		}
		ConjuntoDeStrings tempConjunto = new ConjuntoDeStrings(tempVector);
		return tempConjunto;
	}

	/**
	 * Metodo para realizar la operacion de diferenci de dos conjuntos
	 * 
	 * @param c ConjuntoDeStrings
	 * @return tempConjunto ConjuntoDeStrings
	 */
	public ConjuntoDeStrings diferencia(ConjuntoDeStrings c) {
		int contador = 0;
		for (int i = 0; i < this.cardinal(); i++) {
			if (!c.pertenece(this.elementos()[i])) {
				contador++;
			}
		}
		String[] tempVector = new String[contador];
		int indice = 0;
		for (int i = 0; i < this.cardinal(); i++) {
			if (!c.pertenece(this.elementos()[i])) {
				tempVector[indice] = this.elementos()[i];
				indice++;
			}
		}
		ConjuntoDeStrings tempConjunto = new ConjuntoDeStrings(tempVector);
		return tempConjunto;
	}

	/**
	 * Metodo para comparar la igualdad entre conjuntos
	 * Metodo redefinido de la clase Object
	 * 
	 * @param obj Object a comparar
	 * @return true si los objetos son iguales, false si los objetos no son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ConjuntoDeStrings)) {
			return false;
		}
		ConjuntoDeStrings tempConjunto = (ConjuntoDeStrings) obj;
		for (int i = 0; i < this.cardinal(); i++) {
			if (!tempConjunto.pertenece(this.elementos()[i])) {
				return false;
			}
		}
		for (int i = 0; i < tempConjunto.cardinal(); i++) {
			if (!this.pertenece(tempConjunto.elementos()[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Metodo para comprobar si un conjuton esta contenido en otro
	 * 
	 * @param c ConjuntoDeStrings a estudiar
	 * @return true si esta contenido, false si no esta contenido
	 */
	public boolean contenido(ConjuntoDeStrings c) {
		for (int i = 0; i < c.cardinal(); i++) {
			if (!this.pertenece(c.elementos()[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Metodo que devuelve un array con todos los elementos del objeto
	 * 
	 * @return temp String[]
	 */
	public String[] elementos() {
		String[] temp = new String[this.cardinal()];
		for (int i = 0; i < this.cardinal(); i++) {
			temp[i] = this.vector[i];
		}
		return temp;
	}
}
