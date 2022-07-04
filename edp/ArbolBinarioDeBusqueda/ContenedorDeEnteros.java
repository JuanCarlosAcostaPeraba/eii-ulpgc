package ArbolBinarioDeBusqueda;

/**
 * Clase ContenedorDeEnteros
 * 
 * @author JuanSerberoCode
 */
public class ContenedorDeEnteros {

	/**
	 * Clase del nodo en el que trabajamos
	 */
	class Node {
		/**
		 * Atributos
		 */
		private int value; // Valor del nodo
		private Node left; // Hijo izquierdo
		private Node right; // Hijo derecho

		/**
		 * Constructor del nodo
		 * 
		 * @param value Valor que recibe el nodo
		 */
		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	/**
	 * Atributos
	 */
	private Node raiz; // Valor del nodo actual
	private int tamano; // Tamano del arbol

	/**
	 * Constructor de la clase
	 */
	public ContenedorDeEnteros() {
		this.raiz = null;
		this.tamano = 0;
	}

	/**
	 * Devuelve el tamano del arbol
	 * 
	 * @return this.tamano Numero de elementos del arbol
	 */
	public int cardinal() {
		return this.tamano;
	}

	/**
	 * Metodo privado para hacer una recursividad que recorra el arbol e inserte el
	 * nuevo nodo
	 * 
	 * @param nodo Nodo nuevo
	 * @param num  Valor a insertar
	 * @return true/false TRUE si se inserta, FALSE si no se inserta
	 */
	private boolean insertarPrivate(Node nodo, int num) {
		if (this.raiz == null) {
			this.raiz = new Node(num);
			this.tamano++;
			return true;
		}
		if (nodo.value > num) {
			if (nodo.left != null) {
				return insertarPrivate(nodo.left, num);
			} else {
				nodo.left = new Node(num);
				this.tamano++;
				return true;
			}
		} else if (nodo.value < num) {
			if (nodo.right != null) {
				return insertarPrivate(nodo.right, num);
			} else {
				nodo.right = new Node(num);
				this.tamano++;
				return true;
			}
		}
		return false;
	}

	/**
	 * Inserta valores nuevos en el arbol
	 * 
	 * @param num Valor a insertar
	 * @return true/false TRUE si se inserta, FALSE si no se inserta
	 */
	public boolean insertar(int num) {
		return insertarPrivate(this.raiz, num);
	}

	/**
	 * Metodo privado para hacer una recursividad que recorra el arbol y encuentre
	 * el nodo que se tiene que extraer
	 * 
	 * @param nodo Nodo sometido a estudio
	 * @param num  Valor que se busca para extraer
	 * @return nodo Nodo en el que se encuetra el valor
	 */
	private Node extraerPrivate(Node nodo, int num) {
		if (nodo != null) {
			if (num == nodo.value) {
				if (nodo.left == null || nodo.right == null) {
					this.tamano--;
					if (nodo.left == null) {
						return nodo.right;
					} else {
						return nodo.left;
					}
				} else {
					nodo.right = extraerSucesor(nodo, nodo.right);
				}
			} else {
				if (num < nodo.value) {
					nodo.left = extraerPrivate(nodo.left, num);
				} else {
					nodo.right = extraerPrivate(nodo.right, num);
				}
			}
		}
		return nodo;
	}

	/**
	 * Metodo privado para extraer el nodo buscado
	 * 
	 * @param nodoExtraer Nodo a extraer
	 * @param nodo        Nodo sometido a estudio
	 * @return nodo Nodo donde se cumple los parámetros de la busqueda de extraccion
	 */
	private Node extraerSucesor(Node nodoExtraer, Node nodo) {
		if (nodo.left == null) {
			nodoExtraer.value = nodo.value;
			this.tamano--;
			nodo = nodo.right;
		} else {
			nodo.left = extraerSucesor(nodoExtraer, nodo.left);
		}
		return nodo;
	}

	/**
	 * Extrae el nodo indicado por parametro
	 * 
	 * @param i Nodo a extraer
	 * @return true/false TRUE si se extrae, FALSE si no se extrae
	 */
	public boolean extraer(int i) {
		int tamano = cardinal();
		this.raiz = extraerPrivate(this.raiz, i);
		if (tamano == cardinal()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Metodo privado para hacer una recursividad que recorra el arbol en busca de
	 * un nodo
	 * 
	 * @param nodo Nodo en el que se realiza la busqueda
	 * @param num  Valor a buscar
	 * @return true/false TRUE si se encuentra, FALSE si no se encuentra
	 */
	private boolean buscarPrivate(Node nodo, int num) {
		if (nodo == null)
			return false;
		if (num == nodo.value) {
			return true;
		} else if (num < nodo.value) {
			if (nodo.left != null) {
				return buscarPrivate(nodo.left, num);
			} else {
				return false;
			}
		} else {
			if (nodo.right != null) {
				return buscarPrivate(nodo.right, num);
			} else {
				return false;
			}
		}
	}

	/**
	 * Busca un nodo en el arbol
	 * 
	 * @param i Nodo a buscar
	 * @return true/false TRUE si se encuentra, FALSE si no se encuentra
	 */
	public boolean buscar(int i) {
		return buscarPrivate(this.raiz, i);
	}

	/**
	 * Vacia el arbol
	 */
	public void vaciar() {
		this.raiz = null;
		this.tamano = 0;
	}

	/**
	 * Metodo privado para hacer una recursividad que recorra el arbol de forma
	 * inorden
	 * 
	 * @param nodo   Nodo a examinar
	 * @param vector Array en el que se almacenaran los valores de los nodos
	 * @param pos    Posicion del array en la que se guardaran los valores de los
	 *               nodos
	 */
	private void inOrdenPrivate(Node nodo, int[] vector, EnteroMutable pos) {
		if (nodo == null)
			return;
		inOrdenPrivate(nodo.left, vector, pos);
		vector[pos.valor] = nodo.value;
		pos.incrementar();
		inOrdenPrivate(nodo.right, vector, pos);
		return;
	}

	/**
	 * Clase de EnteroMutable para el control de la posicion del vector
	 */
	private class EnteroMutable {
		private int valor = 0;

		public void incrementar() {
			this.valor++;
		}
	}

	/**
	 * Devuelve un vector con los elementos del arbol ordenados de menor a mayor
	 * 
	 * @return vector Vector con los elementos del arbol
	 */
	public int[] elementos() {
		int[] vector = new int[cardinal()];
		EnteroMutable pos = new EnteroMutable();
		inOrdenPrivate(this.raiz, vector, pos);
		return vector;
	}
}
