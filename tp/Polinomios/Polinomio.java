package Polinomios;

/**
 * Clase Polinomio
 * 
 * @author JuanSerberoCode
 */
public class Polinomio {

	/**
	 * Atributos
	 */
	private int grado; // grado del polinomio
	private int[] vectorDeEnteros; // array que almacena los coeficientes del polinomio

	/**
	 * Constructor del polinomio vacio (por defecto)
	 * Se crea un array de 1 elemento lleno con un 0,
	 * y se establece el grado a 0
	 */
	public Polinomio() {
		int[] vector = new int[1];
		this.vectorDeEnteros = new int[vector.length];
		for (int i = 0; i < vector.length; i++) {
			this.vectorDeEnteros[i] = vector[i];
		}
		this.grado = 0;
	}

	/**
	 * Constructor del polinomio
	 * Se crea un array de n elementos pasados por parámetros,
	 * y se calcula el grado del polinomio
	 * 
	 * @param vector int[]
	 */
	public Polinomio(int[] vector) {
		this.vectorDeEnteros = new int[vector.length];
		for (int i = 0; i < vector.length; i++) {
			this.vectorDeEnteros[i] = vector[i];
		}

		this.calculaGrado();
	}

	/**
	 * Metodo para saber el grado del polinomio
	 * Devolvemos el valor del grado del polinomio
	 * 
	 * @return this.grado int
	 */
	public int grado() {
		return this.grado;
	}

	/**
	 * Metodo para saber el valor del coeficiente en una posicion determinada del
	 * polinimio
	 * 
	 * @param i int
	 * @return this.vectorDeEnteros[i] int
	 */
	public int coeficiente(int i) {
		if (i < 0 || this.grado() < i) {
			return 0;
		} else {
			return this.vectorDeEnteros[i];
		}
	}

	/**
	 * Metodo para cambiar el valor de un coeficiente del polinomio
	 * en el lugar dado por parámetro
	 * 
	 * @param i int
	 * @param v int
	 */
	public void coeficiente(int i, int v) {
		if (0 <= i) {
			if (this.grado() < i) {
				int[] temp = new int[i + 1];
				for (int j = 0; j <= this.grado(); j++) {
					temp[j] = this.coeficiente(j);
				}
				temp[i] = v;
				this.vectorDeEnteros = temp;
			} else {
				this.vectorDeEnteros[i] = v;
			}

			this.calculaGrado();
		}
	}

	/**
	 * Metodo que devuelve todos los coeficientes del polinomio
	 * desde el termino independiente hasta el coeficiente de mayor grado
	 * 
	 * @return vector int[]
	 */
	public int[] coeficientes() {
		int[] vector = new int[this.grado() + 1];
		for (int i = 0; i <= this.grado(); i++) {
			vector[i] = this.coeficiente(i);
		}
		return vector;
	}

	/**
	 * Metodo que devuelve un String que representa el polinomio
	 * 
	 * @return polinomimo String
	 */
	@Override
	public String toString() {
		String polinomio = "";
		for (int i = this.grado(); i >= 0; i--) {
			if (this.coeficiente(i) != 0) { // descarta coeficientes de valor 0
				if (i == this.grado()) { // coeficiente de mayor grado
											// y termino independiente cuando no hay grado
					if (this.grado() != 0) { // hay grado
						if (this.coeficiente(i) == 1) { // coeficiente = 1
							if (i == 1) { // grado = 1
								polinomio = polinomio + "x";
							} else { // grado != 1
								polinomio = polinomio + "x^" + i;
							}
						} else if (this.coeficiente(i) == -1) { // coeficiente = -1
							if (i == 1) { // grado = 1
								polinomio = polinomio + "-x";
							} else { // grado != 1
								polinomio = polinomio + "-x^" + i;
							}
						} else { // coeficiente = cualquier valor
							if (i == 1) { // grado = 1
								polinomio = polinomio + this.coeficiente(i) + "x";
							} else { // grado != 1
								polinomio = polinomio + this.coeficiente(i) + "x^" + i;
							}
						}
					} else { // no hay grado
						polinomio = polinomio + this.coeficiente(i);
					}
				} else if (0 < i && i != this.grado()) { // coeficientes entre termino independiente
															// y termino de mayor grado
					if (this.coeficiente(i) < 0) { // coeficiente negativo
						if (this.coeficiente(i) == -1) { // coeficiente = -1
							if (i == 1) { // grado = 1
								polinomio = polinomio + "-x";
							} else { // grado != 1
								polinomio = polinomio + "-x^" + i;
							}
						} else { // coeficiente cualquier valor negativo
							if (i == 1) { // grado = 1
								polinomio = polinomio + this.coeficiente(i) + "x";
							} else { // grado != 1
								polinomio = polinomio + this.coeficiente(i) + "x^" + i;
							}
						}
					} else { // coeficiente positivo
						if (this.coeficiente(i) == 1) { // coeficiente = 1
							if (i == 1) { // grado = 1
								polinomio = polinomio + "+" + "x";
							} else { // grado != 1
								polinomio = polinomio + "+" + "x^" + i;
							}
						} else { // coeficiente cualquier valor positivo
							if (i == 1) { // grado = 1
								polinomio = polinomio + "+" + this.coeficiente(i) + "x";
							} else { // grado != 1
								polinomio = polinomio + "+" + this.coeficiente(i) + "x^" + i;
							}
						}
					}
				} else if (i == 0) { // termino independiente
					if (this.coeficiente(i) < 0) { // termino independiente negativo
						polinomio = polinomio + this.coeficiente(i);
					} else { // termino independiente positivo
						polinomio = polinomio + "+" + this.coeficiente(i);
					}
				}
			}
		}
		if (polinomio == "") {
			polinomio = "0";
		}
		return polinomio;
	}

	/**
	 * Metodo para hallar el valor del polinomio,
	 * tras haberle dado un valor a la x del polinomio
	 * 
	 * @param v float
	 * @return x float
	 */
	public float valor(float v) {
		float x = 0;
		for (int i = this.grado(); i >= 0; i--) {
			if (this.coeficiente(i) != 0) {
				if (0 < i) {
					float temp0 = 1; // variable temporal 0
					for (int j = 0; j < i; j++) {
						temp0 = temp0 * v;
					}
					x = x + (this.coeficiente(i) * temp0);
				}
				if (i == 0) {
					x = x + this.coeficiente(i);
				}
			}
		}
		return x;
	}

	/**
	 * Metodo para sumar dos polimonios
	 * 
	 * @param p Polinomio
	 * @return Final Polinomio
	 */
	public Polinomio suma(Polinomio p) {
		int grado = 1;

		if (p.grado() <= this.grado()) {
			grado = this.grado() + 1;
		} else {
			grado = p.grado() + 1;
		}

		int[] vector = new int[grado];

		for (int i = 0; i < grado; i++) {
			vector[i] = (this.coeficiente(i)) + (p.coeficiente(i));
		}

		Polinomio Final = new Polinomio(vector);

		return Final;
	}

	/**
	 * Metodo para restar dos polimonios
	 * 
	 * @param p Polinomio
	 * @return Final Polinomio
	 */
	public Polinomio resta(Polinomio p) {
		int grado = 1;

		if (p.grado() <= this.grado()) {
			grado = this.grado() + 1;
		} else {
			grado = p.grado() + 1;
		}

		int[] vector = new int[grado];

		for (int i = 0; i < grado; i++) {
			vector[i] = (this.coeficiente(i)) - (p.coeficiente(i));
		}

		Polinomio Final = new Polinomio(vector);

		return Final;
	}

	/**
	 * Medoto privado para calcular el grado del polinomio
	 */
	private void calculaGrado() {
		// calculamos el grado del polinomio
		for (int i = 0; i < this.vectorDeEnteros.length; i++) {
			if (this.vectorDeEnteros[i] != 0) {
				this.grado = i;
			}
		}
	}
}
