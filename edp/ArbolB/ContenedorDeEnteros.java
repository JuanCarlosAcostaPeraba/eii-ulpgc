package ArbolB;

// Paquetes importados
import java.util.LinkedList;
import java.util.Stack;

/**
 * Clase ContenedorDeEnteros
 * 
 * @author JuanSerberoCode
 */
public class ContenedorDeEnteros {

	// Atributos
	private int numElementos;
	private int orden;
	private FicheroAyuda fichero;
	private String nombreFichero;
	private int raiz;
	private int minimoClaves;

	/**
	 * Constructor
	 */
	public ContenedorDeEnteros() {
		fichero = new FicheroAyuda();
	}

	/**
	 * Clase ParejaInsertar
	 */
	private class ParejaInsertar {
		int clave;
		int enlace;
	}

	/**
	 * Clase Nodo
	 */
	private class Nodo {

		// Atributos
		private int[] claves;
		private int[] enlaces;
		private int numClaves;
		private int direccion;

		/**
		 * Constructor
		 */
		public Nodo() {
			claves = new int[orden];
			enlaces = new int[orden + 1];
			numClaves = 0;
			direccion = FicheroAyuda.dirNula;
		}

		/**
		 * Metodo clave
		 * 
		 * @param pos
		 * @return claves[]
		 */
		public int clave(int pos) {
			return claves[pos - 1];
		}

		/**
		 * Metodo clave
		 * 
		 * @param pos
		 * @param num
		 */
		public void clave(int pos, int num) {
			claves[pos - 1] = num;
		}

		/**
		 * Metodo enlace
		 * 
		 * @param pos
		 * @return enlaces[]
		 */
		public int enlace(int pos) {
			return enlaces[pos];
		}

		/**
		 * Metodo enlace
		 * 
		 * @param pos
		 * @param num
		 */
		public void enlace(int pos, int num) {
			enlaces[pos] = num;
		}

		/**
		 * Metodo insertar
		 * 
		 * @param clave
		 * @param enlace
		 * @param pos
		 */
		public void insertar(int clave, int enlace, int pos) {
			numClaves++;
			for (int i = numClaves - 1; i >= pos; i--) {
				clave(i + 1, clave(i));
				enlace(i + 1, enlace(i));
			}
			clave(pos, clave);
			enlace(pos, enlace);
		}

		/**
		 * Metodo extraer
		 * 
		 * @param pos
		 */
		public void extraer(int pos) {
			for (int i = pos; i < numClaves; i++) {
				clave(i, clave(i + 1));
				enlace(i, enlace(i + 1));
			}
			numClaves--;
		}

		/**
		 * Metodo esta
		 * 
		 * @param num
		 * @return true/false
		 */
		public boolean esta(int num) {
			int limInf = 1, limSup = numClaves, medio;
			while (limInf <= limSup) {
				medio = (limInf + limSup) / 2;
				if (num == clave(medio)) {
					return true;
				} else if (num < clave(medio)) {
					limSup = medio - 1;
				} else {
					limInf = medio + 1;
				}
			}
			return false;
		}

		/**
		 * Metodo buscar
		 * 
		 * @param num
		 * @return pos
		 */
		public int buscar(int num) {
			int limInf = 1, limSup = numClaves, medio, pos;
			while (limInf <= limSup) {
				medio = (limInf + limSup) / 2;
				if (num == clave(medio)) {
					pos = medio;
					return pos;
				} else if (num < clave(medio)) {
					limSup = medio - 1;
				} else {
					limInf = medio + 1;
				}
			}
			pos = limInf - 1;
			return pos;
		}

		/**
		 * Metodo nodeSize
		 * 
		 * @return nodeSize
		 */
		public int nodeSize() {
			return (claves.length + enlaces.length + 1 + 1) * 4;
		}

		/**
		 * Metodo aByte
		 * 
		 * @return result
		 */
		public byte[] aByte() {
			int tamano = nodeSize();
			byte[] result = new byte[tamano];
			int pos = 0;
			pos = Conversor.anade(result, Conversor.aByte(direccion), pos);
			pos = Conversor.anade(result, Conversor.aByte(numClaves), pos);
			for (int i = 1; i <= numClaves; i++) {
				pos = Conversor.anade(result, Conversor.aByte(clave(i)), pos);
			}
			for (int i = 0; i <= numClaves; i++) {
				pos = Conversor.anade(result, Conversor.aByte(enlace(i)), pos);
			}
			return result;
		}

		/**
		 * Metodo deByte
		 * 
		 * @param datos
		 */
		public void deByte(byte[] datos) {
			byte[] numData;
			direccion = Conversor.aInt(Conversor.toma(datos, 0, 4));
			numClaves = Conversor.aInt(Conversor.toma(datos, 4, 4));
			int baseClaves = 4 * 2;
			int baseEnlaces = baseClaves + (numClaves) * 4;
			for (int i = 1; i <= numClaves; i++) {
				numData = Conversor.toma(datos, baseClaves + (i - 1) * 4, 4);
				clave(i, Conversor.aInt(numData));
			}
			for (int i = 0; i <= numClaves; i++) {
				numData = Conversor.toma(datos, baseEnlaces + i * 4, 4);
				enlace(i, Conversor.aInt(numData));
			}
		}
	}

	/**
	 * Clase InfoPila
	 */
	private class InfoPila {

		// Atributos
		private Nodo nodo;
		private int pos;

		/**
		 * Constructor
		 * 
		 * @param nodo
		 * @param pos
		 */
		public InfoPila(Nodo nodo, int pos) {
			this.nodo = nodo;
			this.pos = pos;
		}
	}

	/**
	 * Metodo leerNodo
	 * 
	 * @param dirNodo
	 * @return
	 */
	private Nodo leerNodo(int dirNodo) {
		Nodo nodo = new Nodo();
		nodo.deByte(fichero.leer(dirNodo));
		return nodo;
	}

	/**
	 * Metodo escribirNodo
	 * 
	 * @param nodo
	 */
	private void escribirNodo(Nodo nodo) {
		fichero.escribir(nodo.aByte(), nodo.direccion);
	}

	/**
	 * Metodo buscar
	 * 
	 * @param nodo
	 */
	private boolean buscar(int num, Stack<InfoPila> pila) {
		int dirNodo, pos;
		Nodo nodo = new Nodo();
		dirNodo = raiz;
		pila.clear();
		while (dirNodo != FicheroAyuda.dirNula) {
			nodo = leerNodo(dirNodo);
			pos = nodo.buscar(num);
			pila.add(new InfoPila(nodo, pos));
			if (nodo.esta(num)) {
				return true;
			}
			dirNodo = nodo.enlace(pos);
		}
		return false;
	}

	/**
	 * Metodo particion1_2
	 * 
	 * @param nodo
	 * @return pa
	 */
	private ParejaInsertar particion1_2(Nodo nodo) {
		ParejaInsertar pa = new ParejaInsertar();
		Nodo nuevoNodo = new Nodo();
		int numClavesNuevo = orden / 2;
		int numClavesNodo = orden - numClavesNuevo - 1;
		int dirNuevo = fichero.tomarPagina();
		nuevoNodo.direccion = dirNuevo;
		nuevoNodo.numClaves = numClavesNuevo;
		nuevoNodo.enlace(0, nodo.enlace(numClavesNodo + 1));
		for (int i = 1; i <= nuevoNodo.numClaves; i++) {
			nuevoNodo.clave(i, nodo.clave(numClavesNodo + 1 + i));
			nuevoNodo.enlace(i, nodo.enlace(numClavesNodo + 1 + i));
		}
		pa.clave = nodo.clave(numClavesNodo + 1);
		pa.enlace = nuevoNodo.direccion;
		nodo.numClaves = numClavesNodo;
		escribirNodo(nodo);
		escribirNodo(nuevoNodo);
		return pa;
	}

	/**
	 * Metodo rotacionIzqDer
	 * 
	 * @param padre
	 * @param posIzq
	 * @param izq
	 * @param der
	 */
	private void rotacionIzqDer(Nodo padre, int posIzq, Nodo izq, Nodo der) {
		int clavesRepartir = izq.numClaves + der.numClaves;
		int numClavesIzq = clavesRepartir / 2;
		int numClavesDer = clavesRepartir - numClavesIzq;
		int numClavesPasar = numClavesDer - der.numClaves;
		int antNumClavesDer = der.numClaves;
		der.numClaves = numClavesDer;
		for (int i = antNumClavesDer; i >= 1; i--) {
			der.clave(i + numClavesPasar, der.clave(i));
			der.enlace(i + numClavesPasar, der.enlace(i));
		}
		der.enlace(numClavesPasar, der.enlace(0));
		der.clave(numClavesPasar, padre.clave(posIzq + 1));
		for (int i = numClavesIzq + 2; i <= izq.numClaves; i++) {
			der.clave(i - (numClavesIzq + 1), izq.clave(i));
			der.enlace(i - (numClavesIzq + 1), izq.enlace(i));
		}
		der.enlace(0, izq.enlace(numClavesIzq + 1));
		padre.clave(posIzq + 1, izq.clave(numClavesIzq + 1));
		izq.numClaves = numClavesIzq;
		escribirNodo(padre);
		escribirNodo(izq);
		escribirNodo(der);
	}

	/**
	 * Metodo rotacionDerIzq
	 * 
	 * @param padre
	 * @param posIzq
	 * @param izq
	 * @param der
	 */
	private void rotacionDerIzq(Nodo padre, int posIzq, Nodo izq, Nodo der) {
		int clavesRepartir = izq.numClaves + der.numClaves;
		int numClavesDer = clavesRepartir / 2;
		int numClavesIzq = clavesRepartir - numClavesDer;
		int numClavesPasar = der.numClaves - numClavesDer;
		int antNumClavesIzq = izq.numClaves;
		izq.numClaves = numClavesIzq;
		izq.clave(antNumClavesIzq + 1, padre.clave(posIzq + 1));
		izq.enlace(antNumClavesIzq + 1, der.enlace(0));
		for (int i = 1; i < numClavesPasar; i++) {
			izq.clave(antNumClavesIzq + 1 + i, der.clave(i));
			izq.enlace(antNumClavesIzq + 1 + i, der.enlace(i));
		}
		padre.clave(posIzq + 1, der.clave(numClavesPasar));
		der.enlace(0, der.enlace(numClavesPasar));
		for (int i = 1; i <= numClavesDer; i++) {
			der.clave(i, der.clave(i + numClavesPasar));
			der.enlace(i, der.enlace(i + numClavesPasar));
		}
		der.numClaves = numClavesDer;
		escribirNodo(padre);
		escribirNodo(izq);
		escribirNodo(der);
	}

	/**
	 * Metodo particion2_3
	 * 
	 * @param padre
	 * @param posIzq
	 * @param izq
	 * @param der
	 */
	private void particion2_3(Nodo padre, int posIzq, Nodo izq, Nodo der) {
		int clavesRepartir = izq.numClaves + der.numClaves - 1;
		Nodo reg = new Nodo();
		int numClavesIzq = clavesRepartir / 3;
		int numClavesReg = (clavesRepartir + 1) / 3;
		int numClavesDer = (clavesRepartir + 2) / 3;
		int antNumClavesDer = der.numClaves;
		int antNumClavesIzq = izq.numClaves;
		reg.direccion = fichero.tomarPagina();
		padre.insertar(izq.clave(numClavesIzq + 1), reg.direccion, posIzq + 1);
		reg.numClaves = numClavesReg;
		reg.enlace(0, izq.enlace(numClavesIzq + 1));
		for (int i = numClavesIzq + 2; i <= antNumClavesIzq; i++) {
			reg.clave(i - numClavesIzq - 1, izq.clave(i));
			reg.enlace(i - numClavesIzq - 1, izq.enlace(i));
		}
		izq.numClaves = numClavesIzq;
		reg.clave(antNumClavesIzq - numClavesIzq, padre.clave(posIzq + 2));
		int pos1 = antNumClavesIzq - numClavesIzq;
		reg.enlace(pos1, der.enlace(0));
		for (int i = pos1 + 1; i <= numClavesReg; i++) {
			reg.clave(i, der.clave(i - pos1));
			reg.enlace(i, der.enlace(i - pos1));
		}
		int numClavesPasar = antNumClavesDer - numClavesDer;
		padre.clave(posIzq + 2, der.clave(numClavesPasar));
		der.enlace(0, der.enlace(numClavesPasar));
		for (int i = numClavesPasar + 1; i <= antNumClavesDer; i++) {
			der.clave(i - numClavesPasar, der.clave(i));
			der.enlace(i - numClavesPasar, der.enlace(i));
		}
		der.numClaves = numClavesDer;
		escribirNodo(izq);
		escribirNodo(reg);
		escribirNodo(der);
	}

	/**
	 * Metodo recombinacion2_1
	 * 
	 * @param padre
	 * @param posIzq
	 * @param izq
	 * @param der
	 */
	private void recombinacion2_1(Nodo padre, int posIzq, Nodo izq, Nodo der) {
		int antNumClavesIzq = izq.numClaves;
		izq.numClaves = izq.numClaves + 1 + der.numClaves;
		izq.clave(antNumClavesIzq + 1, padre.clave(posIzq + 1));
		izq.enlace(antNumClavesIzq + 1, der.enlace(0));
		for (int i = 1; i <= der.numClaves; i++) {
			izq.clave(antNumClavesIzq + 1 + i, der.clave(i));
			izq.enlace(antNumClavesIzq + 1 + i, der.enlace(i));
		}
		padre.extraer(posIzq + 1);
		escribirNodo(izq);
		fichero.liberarPagina(der.direccion);
	}

	/**
	 * Metodo recombinacion3_2
	 * 
	 * @param padre
	 * @param posReg
	 * @param izq
	 * @param reg
	 * @param der
	 */
	private void recombinacion3_2(Nodo padre, int posReg, Nodo izq, Nodo reg, Nodo der) {
		int aRepartir = izq.numClaves + reg.numClaves + der.numClaves + 1;
		int numClavesDer = aRepartir / 2;
		int numClavesIzq = aRepartir - numClavesDer;
		int antNumClavesIzq = izq.numClaves;
		int antNumClavesDer = der.numClaves;
		izq.numClaves = numClavesIzq;
		izq.clave(antNumClavesIzq + 1, padre.clave(posReg));
		izq.enlace(antNumClavesIzq + 1, reg.enlace(0));
		for (int i = antNumClavesIzq + 2; i <= numClavesIzq; i++) {
			izq.clave(i, reg.clave(i - antNumClavesIzq - 1));
			izq.enlace(i, reg.enlace(i - antNumClavesIzq - 1));
		}
		der.numClaves = numClavesDer;
		int numClavesPasar = numClavesDer - antNumClavesDer;
		for (int i = antNumClavesDer; i >= 1; i--) {
			der.clave(i + numClavesPasar, der.clave(i));
			der.enlace(i + numClavesPasar, der.enlace(i));
		}
		der.enlace(numClavesPasar, der.enlace(0));
		der.clave(numClavesPasar, padre.clave(posReg + 1));
		for (int i = numClavesPasar - 1; i >= 1; i--) {
			der.clave(i, reg.clave(reg.numClaves + i - numClavesPasar + 1));
			der.enlace(i, reg.enlace(reg.numClaves + i - numClavesPasar + 1));
		}
		der.enlace(0, reg.enlace(reg.numClaves - numClavesPasar + 1));
		fichero.liberarPagina(reg.direccion);
		escribirNodo(izq);
		escribirNodo(der);
		padre.extraer(posReg);
		padre.clave(posReg, reg.clave(reg.numClaves - numClavesPasar + 1));
	}

	/**
	 * Metodo crear
	 * 
	 * @param nombre
	 * @param orden
	 */
	public void crear(String nombre, int orden) {
		cerrar();
		this.orden = orden;
		nombreFichero = nombre;
		Nodo nodo = new Nodo();
		fichero.crear(nombre, nodo.nodeSize(), 3);
		raiz = FicheroAyuda.dirNula;
		numElementos = 0;
		minimoClaves = ((orden + 1) / 2) - 1;
		fichero.adjunto(0, raiz);
		fichero.adjunto(1, numElementos);
		fichero.adjunto(2, orden);
	}

	/**
	 * Metodo abrir
	 * 
	 * @param nombre
	 */
	public void abrir(String nombre) {
		fichero.abrir(nombre);
		raiz = fichero.adjunto(0);
		numElementos = fichero.adjunto(1);
		orden = fichero.adjunto(2);
		minimoClaves = ((orden + 1) / 2) - 1;
	}

	/**
	 * Metodo cerrar
	 */
	public void cerrar() {
		fichero.cerrar();
	}

	/**
	 * Metodo cardinal
	 * 
	 * @return numElementos
	 */
	public int cardinal() {
		return numElementos;
	}

	/**
	 * Metodo insertar
	 * 
	 * @param num
	 * @return true/false
	 */
	public boolean insertar(int num) {
		Stack<InfoPila> pila = new Stack<>();
		if (buscar(num, pila)) {
			return false;
		}
		Nodo nodoActual = new Nodo();
		InfoPila info;
		ParejaInsertar pa = new ParejaInsertar();
		pa.clave = num;
		pa.enlace = FicheroAyuda.dirNula;
		fichero.adjunto(1, ++numElementos);
		if (!pila.empty()) {
			info = pila.pop();
			nodoActual = info.nodo;
			int pos = info.pos;
			nodoActual.insertar(pa.clave, pa.enlace, pos + 1);
			if (nodoActual.numClaves < orden) {
				escribirNodo(nodoActual);
				return true;
			}
			while (!pila.empty()) {
				info = pila.pop();
				Nodo der, izq;
				Nodo padre = info.nodo;
				pos = info.pos;
				if (pos > 0) {
					izq = leerNodo(padre.enlace(pos - 1));
					if (izq.numClaves < (orden - 1)) {
						rotacionDerIzq(padre, pos - 1, izq, nodoActual);
						return true;
					}
				}
				if (pos < padre.numClaves) {
					der = leerNodo(padre.enlace(pos + 1));
					if (der.numClaves < (orden - 1)) {
						rotacionIzqDer(padre, pos, nodoActual, der);
						return true;
					}
				}
				if (pos == 0) {
					der = leerNodo(padre.enlace(pos + 1));
					particion2_3(padre, pos, nodoActual, der);
				} else {
					izq = leerNodo(padre.enlace(pos - 1));
					particion2_3(padre, pos - 1, izq, nodoActual);
				}
				if (padre.numClaves < orden) {
					escribirNodo(padre);
					return true;
				}
				nodoActual = padre;
			}
			pa = particion1_2(nodoActual);
		}
		nodoActual.numClaves = 1;
		nodoActual.enlace(0, raiz);
		nodoActual.clave(1, pa.clave);
		nodoActual.enlace(1, pa.enlace);
		nodoActual.direccion = fichero.tomarPagina();
		raiz = nodoActual.direccion;
		escribirNodo(nodoActual);
		fichero.adjunto(0, raiz);
		return true;
	}

	/**
	 * Metodo extraer
	 * 
	 * @param num
	 * @return true/false
	 */
	public boolean extraer(int num) {
		Stack<InfoPila> pila = new Stack<>();
		if (!buscar(num, pila)) {
			return false;
		}
		fichero.adjunto(1, --numElementos);
		InfoPila info = pila.pop();
		Nodo nodoActual = info.nodo;
		int pos = info.pos;
		if (nodoActual.enlace(0) != FicheroAyuda.dirNula) {
			pila.add(new InfoPila(info.nodo, info.pos));
			LinkedList<InfoPila> cola = new LinkedList<>();
			int dir = nodoActual.enlace(pos);
			do {
				nodoActual = leerNodo(dir);
				dir = nodoActual.enlace(0);
				if (dir == FicheroAyuda.dirNula) {
					pos = 1;
				} else {
					pos = 0;
				}
				cola.addLast(new InfoPila(nodoActual, pos));
			} while (dir != FicheroAyuda.dirNula);
			info = pila.pop();
			info.nodo.clave(info.pos, nodoActual.clave(1));
			escribirNodo(info.nodo);
			pila.add(info);
			while (!cola.isEmpty()) {
				pila.add(cola.getFirst());
				cola.removeFirst();
			}
			info = pila.pop();
			nodoActual = info.nodo;
			pos = info.pos;
		}
		nodoActual.extraer(pos);
		while (nodoActual.numClaves < minimoClaves && nodoActual.direccion != raiz) {
			Nodo padre, der = new Nodo(), izq = new Nodo();
			info = pila.pop();
			padre = info.nodo;
			pos = info.pos;
			if (pos < padre.numClaves) {
				der = leerNodo(padre.enlace(pos + 1));
				if (der.numClaves > minimoClaves) {
					rotacionDerIzq(padre, pos, nodoActual, der);
					return true;
				}
			}
			if (pos > 0) {
				izq = leerNodo(padre.enlace(pos - 1));
				if (izq.numClaves > minimoClaves) {
					rotacionIzqDer(padre, pos - 1, izq, nodoActual);
					return true;
				}
			}
			if (pos > 0 && pos < padre.numClaves) {
				recombinacion3_2(padre, pos, izq, nodoActual, der);
			} else if (pos > 0) {
				recombinacion2_1(padre, pos - 1, izq, nodoActual);
			} else {
				recombinacion2_1(padre, pos, nodoActual, der);
			}
			nodoActual = padre;
		}
		if (nodoActual.numClaves > 0) {
			escribirNodo(nodoActual);
		} else {
			raiz = nodoActual.enlace(0);
			fichero.liberarPagina(nodoActual.direccion);
			fichero.adjunto(0, raiz);
		}
		return true;
	}

	/**
	 * Metodo buscar
	 * 
	 * @param num
	 * @return true/false
	 */
	public boolean buscar(int num) {
		Stack<InfoPila> pila = new Stack<>();
		return buscar(num, pila);
	}

	/**
	 * Metodo vaciar
	 */
	public void vaciar() {
		cerrar();
		crear(nombreFichero, orden);
	}

	/**
	 * Metodo elementos
	 * 
	 * @return vector[]
	 */
	public int[] elementos() {
		int[] vector = new int[numElementos];
		EnteroMutable pos = new EnteroMutable();
		inOrdenPrivate(raiz, vector, pos);
		return vector;
	}

	/**
	 * Metodo inOrdenPrivate
	 * 
	 * @param dirNodo
	 * @param vector
	 * @param pos
	 */
	private void inOrdenPrivate(int dirNodo, int[] vector, EnteroMutable pos) {
		if (dirNodo == fichero.dirNula)
			return;
		Nodo nodoActual = leerNodo(dirNodo);
		int i = 1;
		inOrdenPrivate(nodoActual.enlace(0), vector, pos);
		while (i <= nodoActual.numClaves) {
			vector[pos.valor] = nodoActual.clave(i);
			pos.incrementar();
			inOrdenPrivate(nodoActual.enlace(i), vector, pos);
			i++;
		}
	}

	/**
	 * Clase EnteroMutable
	 */
	private class EnteroMutable {

		// Atributos
		private int valor = 0;

		/**
		 * Metodo incrementar
		 */
		public void incrementar() {
			this.valor++;
		}
	}
}
