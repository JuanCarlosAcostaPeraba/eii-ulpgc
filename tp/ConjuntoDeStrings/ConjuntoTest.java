package ConjuntoDeStrings;

// Paquetes importados
import org.junit.*;
import static org.junit.Assert.*;
import tp.practicas.*;

/**
 * Clase ConjuntoTest
 * 
 * @author JuanSerberoCode
 */
public class ConjuntoTest {

	@Test
	public void constructorGeneraConjuntoLleno() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] { "Hola" });
		assertArrayEquals("Constructor falla al generar conjunto lleno",
				new String[] { "Hola" }, c.elementos());
		assertEquals("Constructor falla al calcular el cardinal del conjunto lleno",
				1, c.cardinal());
	}

	@Test
	public void constructorGeneraConjuntoVacio() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] {});
		assertArrayEquals("Constructor falla al generar conjunto vacio",
				new String[] {}, c.elementos());
		assertEquals("Constructor falla al calcular el cardinal del conjunto vacio",
				0, c.cardinal());
	}

	@Test
	public void cardinalIgualACero() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] {});
		assertEquals("Metodo cardinal da mal. Tiene que ser 0",
				0, c.cardinal());
	}

	@Test
	public void cardinalDistintoDeCero() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] { "Hola" });
		assertEquals("Metodo cardinal da mal. Tiene que ser 1",
				1, c.cardinal());
	}

	@Test
	public void estaVacioDevuelveFalse() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] { "Hola" });
		assertEquals("Metodo estaVacio tiene que devolver false",
				false, c.estáVacío());
	}

	@Test
	public void estaVacioDevuelveTrue() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] {});
		assertEquals("Metodo estaVacio tiene que devolver true",
				true, c.estáVacío());
	}

	@Test
	public void anadeDevuelveFalse() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] { "Hola" });
		assertEquals("Metodo anade tiene que devolver false",
				false, c.añade("Hola"));
	}

	@Test
	public void anadeDevuelveTrue() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] {});
		assertEquals("Metodo anade tiene que devolver true",
				true, c.añade("Hola"));
	}

	@Test
	public void perteneceDevuelveFalse() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] {});
		assertEquals("Metodo pertenece tiene que devolver false",
				false, c.pertenece("Hola"));
	}

	@Test
	public void perteneceDevuelveTrue() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] { "Hola" });
		assertEquals("Metodo pertenece tiene que devolver true",
				true, c.pertenece("Hola"));
	}

	@Test
	public void unionDeConjuntosDiferentes() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "mundo" });
		ConjuntoDeStrings c3 = new ConjuntoDeStrings(new String[] { "Hola", "mundo" });
		assertEquals("Metodo union une mal dos conjuntos diferentes",
				c3, c1.unión(c2));
	}

	@Test
	public void unionDeConjuntosIguales() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c3 = new ConjuntoDeStrings(new String[] { "Hola" });
		assertEquals("Metodo union une mal dos conjuntos iguales",
				c3, c1.unión(c2));
	}

	@Test
	public void interseccionDeConjuntosDiferentes() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "mundo" });
		ConjuntoDeStrings c3 = new ConjuntoDeStrings(new String[] {});
		assertEquals("Metodo interseccion hace mal la interseccion de dos conjuntos diferentes",
				c3, c1.intersección(c2));
	}

	@Test
	public void interseccionDeConjuntosIguales() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c3 = new ConjuntoDeStrings(new String[] { "Hola" });
		assertEquals("Metodo interseccion hace mal la interseccion de dos conjuntos iguales",
				c3, c1.intersección(c2));
	}

	@Test
	public void diferenciaDeConjuntosDiferentes() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "mundo" });
		ConjuntoDeStrings c3 = new ConjuntoDeStrings(new String[] { "Hola" });
		assertEquals("Metodo diferencia hace mal la diferencia de dos conjuntos diferentes",
				c3, c1.diferencia(c2));
	}

	@Test
	public void diferenciaDeConjuntosIguales() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c3 = new ConjuntoDeStrings(new String[] {});
		assertEquals("Metodo diferencia hace mal la diferencia de dos conjuntos iguales",
				c3, c1.diferencia(c2));
	}

	@Test
	public void equalsDeConjuntosDiferentes() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "mundo" });
		assertEquals("Metodo equals comprueba mal que dos conjuntos son diferentes",
				false, c1.equals(c2));
	}

	@Test
	public void equalsDeConjuntosIguales() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "Hola" });
		assertEquals("Metodo equals comprueba mal que dos conjuntos son iguales",
				true, c1.equals(c2));
	}

	@Test
	public void conjuntoContenidodentroDeOtro() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola", "mundo", "!" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "Hola", "mundo" });
		assertEquals("Metodo contenido no devuelve que un conjunto c2 este contenido en otro conjunto c1",
				true, c1.contenido(c2));
	}

	@Test
	public void conjuntoNoContenidoDentroDeOtro() {
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola" });
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] { "mundo" });
		assertEquals("Metodo contenido devuelve que un conjunto c2 este contenido en otro conjunto c1",
				false, c1.contenido(c2));
	}

	@Test
	public void elementosDeUnConjuntoDevuelveBien() {
		ConjuntoDeStrings c = new ConjuntoDeStrings(new String[] { "Hola" });
		assertArrayEquals("Metodo elementos falla al devolver el array de Strings con los elementos del conjunto",
				new String[] { "Hola" }, c.elementos());
	}
}
