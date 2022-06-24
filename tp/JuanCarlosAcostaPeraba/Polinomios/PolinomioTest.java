package Polinomios;

// Paquetes importados
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Clase PolinomioTest
 * 
 * @author JuanSerberoCode
 */
public class PolinomioTest {

	@Test
	public void constructorPorDefectoGeneraPolinomioVacio() {
		Polinomio pol = new Polinomio();
		assertArrayEquals("Constructor por defecto falla",
				new int[] { 0 }, pol.coeficientes());
	}

	@Test
	public void constructorPorDefectoGrado() {
		Polinomio pol = new Polinomio();
		assertEquals("Constructor por defecto falla al calcular el grado del polinomio",
				0, pol.grado());
	}

	@Test
	public void constructorPorDefectoCoeficienteiDentroDelRango() {
		Polinomio pol = new Polinomio();
		assertEquals("Constructor por defecto falla al devolver el valor 0 del array",
				0, pol.coeficiente(0));
	}

	@Test
	public void constructorPorDefectoCambiaValor0Por10() {
		Polinomio pol = new Polinomio();
		pol.coeficiente(0, 10);
		assertArrayEquals("Constructor por defecto falla al cambiar el valor 0 en la posicion 0 por un 10",
				new int[] { 10 }, pol.coeficientes());
		assertEquals("Constructor por defecto falla al cambiar el valor 0 en la posicion 0 por un 10",
				10, pol.coeficiente(0));
	}

	@Test
	public void constructorPorDefectoAnadeValor10EnPosicion3() {
		Polinomio pol = new Polinomio();
		pol.coeficiente(3, 10);
		assertArrayEquals("Constructor por defecto falla al añadir el valor 10 en la posicion 3 (coeficientes)",
				new int[] { 0, 0, 0, 10 }, pol.coeficientes());
		assertEquals("Constructor por defecto falla al añadir el valor 10 en la posicion 3 (coeficiente)",
				10, pol.coeficiente(3));
		assertEquals("Constructor por defecto falla al añadir el valor 10 en la posicion 3 (grado)",
				3, pol.grado());
	}

	@Test
	public void constructorPorDefectoToString() {
		Polinomio pol = new Polinomio();
		assertEquals("Constructor por defecto crea el string del polinomio mal.", "0", pol.toString());
	}

	@Test
	public void constructorPorDefectoSumaPolinomioPortDefectoMasPolinomioConGrado() {
		Polinomio pol1 = new Polinomio();
		Polinomio pol2 = new Polinomio(new int[] { 1, 1, 1, 1 });
		Polinomio resultado = pol1.suma(pol2);
		assertEquals("La suma de " + pol1.toString() + " + " + pol2.toString() + "da un mal resultado (grado)",
				3, resultado.grado());
		assertArrayEquals(
				"La suma de " + pol1.toString() + " + " + pol2.toString() + "da un mal resultado (coeficientes)",
				new int[] { 1, 1, 1, 1 }, resultado.coeficientes());
	}

	@Test
	public void constructorPorDefectoRestaPolinomioPortDefectoMenosPolinomioConGrado() {
		Polinomio pol1 = new Polinomio();
		Polinomio pol2 = new Polinomio(new int[] { 1, 1, 1, 1 });
		Polinomio resultado = pol1.resta(pol2);
		assertEquals("La suma de " + pol1.toString() + " + " + pol2.toString() + "da un mal resultado (grado)",
				3, resultado.grado());
		assertArrayEquals(
				"La suma de " + pol1.toString() + " + " + pol2.toString() + "da un mal resultado (coeficientes)",
				new int[] { -1, -1, -1, -1 }, resultado.coeficientes());
	}
}
