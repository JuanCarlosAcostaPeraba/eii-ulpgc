package Polinomios;

// Paquetes importados
import java.util.Arrays;

/**
 * Clase UsaPolinomio
 * 
 * @author JuanSerberoCode
 */
public class UsaPolinomio {

	public static void main(String[] args) {

		float x;
		int[] arrayRandom;

		// --------------------------------------------------
		// Tests Polinomio
		// --------------------------------------------------
		// org.junit.runner.JUnitCore.main("PolinomioTest");

		// --------------------------------------------------
		// Polinomio 1 - Polinomio vacio
		// --------------------------------------------------
		System.out.println("\t\tPOLINOMIO 1");
		Polinomio pol1 = new Polinomio();
		x = 2;
		pruebaEstandar(pol1, x);

		System.out.println("\n");

		// --------------------------------------------------
		// Polinomio 2 - Polinomio lleno 1
		// --------------------------------------------------
		System.out.println("\t\tPOLINOMIO 2");
		arrayRandom = generaRandoms();
		Polinomio pol2 = new Polinomio(arrayRandom);
		x = 7;
		pruebaEstandar(pol2, x);

		System.out.println("\n");

		// --------------------------------------------------
		// Polinomio 3 - Polinomio lleno 2
		// --------------------------------------------------
		System.out.println("\t\tPOLINOMIO 3");
		arrayRandom = generaRandoms();
		Polinomio pol3 = new Polinomio(arrayRandom);
		x = 3;
		pruebaEstandar(pol3, x);

		System.out.println("\n");

		// --------------------------------------------------
		// Polinomio 4 - Polinomio para cambiar valores / rellenar
		// --------------------------------------------------
		System.out.println("\t\tPOLINOMIO 4");
		int[] arrayRelleno = new int[10];
		for (int i = 0; i < 10; i++) {
			arrayRelleno[i] = i + 1;
		}
		Polinomio pol4 = new Polinomio(arrayRelleno);
		System.out.println(pol4.toString());
		System.out.println("Cambiamos el coeficiente de grado 3 por el valor 10");
		pol4.coeficiente(3, 10);
		System.out.println(pol4.toString());
		System.out.println("Metemos un coeficiente de grado 20 con valor 9");
		pol4.coeficiente(20, 9);
		System.out.println(pol4.toString());
		System.out.println(pol4.grado());
		System.out.println(Arrays.toString(pol4.coeficientes()));

		System.out.println("\n");

		// --------------------------------------------------
		// Polinomio 5 - Polinomio para sumar/restar polinomios de igual grado
		// --------------------------------------------------
		System.out.println("\t\tPOLINOMIO 5");

		int[] arrayOperaciones = new int[10];
		int indice = 4;
		for (int i = 0; i < 10; i++) {
			arrayOperaciones[i] = indice;
			indice++;
		}
		Polinomio pol5 = new Polinomio(arrayOperaciones);
		System.out.println(pol5.toString());

		int[] arrayOperaciones1 = new int[10];
		for (int i = 0; i < 10; i++) {
			arrayOperaciones1[i] = i;
		}
		Polinomio pol6 = new Polinomio(arrayOperaciones1);
		System.out.println(pol6.toString());

		System.out.println("Suma de pol5 + pol6");
		System.out.println(pol5.suma(pol6).toString());

		System.out.println("Resta de pol5 - pol6");
		System.out.println(pol5.resta(pol6).toString());

		System.out.println("\n");

		// --------------------------------------------------
		// Polinomio 6 - Polinomio para sumar/restar polinomios de diferente grado
		// --------------------------------------------------
		System.out.println("\t\tPOLINOMIO 6");
		int[] arrayOperaciones2 = new int[20];
		int indice1 = -7;
		for (int i = 0; i < 20; i++) {
			if (i > 5) {
				arrayOperaciones2[i] = indice1;
				indice1++;
			}
		}
		Polinomio pol7 = new Polinomio(arrayOperaciones2);
		System.out.println(pol7.toString());

		int[] arrayOperaciones3 = new int[5];
		for (int i = 0; i < 5; i++) {
			arrayOperaciones3[i] = i;
		}
		Polinomio pol8 = new Polinomio(arrayOperaciones3);
		System.out.println(pol8.toString());

		System.out.println("Suma de pol5 + pol6");
		System.out.println(pol7.suma(pol8).toString());

		System.out.println("Resta de pol5 - pol6");
		System.out.println(pol7.resta(pol8).toString());
	}

	/**
	 * Funcion para realizar las pruebas estandar de un polinomio
	 * 
	 * @param p Polinomio
	 * @param x float
	 */
	public static void pruebaEstandar(Polinomio p, float x) {
		// grado
		System.out.println("El grado del polinomio es: " + p.grado());

		// coeficiente con grado
		int[] coeficiente1 = generaRandoms();
		for (int i = 0; i < coeficiente1.length; i++) {
			System.out.println("El coeficiente del polinomio con grado " + coeficiente1[i] + " es: "
					+ p.coeficiente(coeficiente1[i]));
		}

		// coeficientes
		System.out.println("Los coeficientes con grado del polinomio son:\n" + Arrays.toString(p.coeficientes()));

		// toString
		System.out.println("El polinomio tiene la forma:\n" + p.toString());

		// valor del polinomio para x = a un valor
		System.out.println("El polinomio tiene el valor: " + p.valor(x));
	}

	/**
	 * Funcion para generar un array de 10 numeros random entre -10 y 10
	 * 
	 * @return coeficiente1 int[]
	 */
	public static int[] generaRandoms() {
		int[] coeficiente1 = new int[10];
		int random;
		for (int i = 0; i < 5; i++) {
			random = (int) (Math.random() * (-10));
			coeficiente1[i] = random;
		}
		for (int i = 5; i < 10; i++) {
			random = (int) (Math.random() * (10));
			coeficiente1[i] = random;
		}
		return coeficiente1;
	}

}