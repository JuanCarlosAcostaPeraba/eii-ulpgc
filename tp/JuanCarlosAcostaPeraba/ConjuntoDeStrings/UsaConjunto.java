package ConjuntoDeStrings;

// Paquetes importados
import java.util.Arrays;
import tp.practicas.*;

/**
 * Clase UsaConjunto
 * 
 * @author JuanSerberoCode
 */
public class UsaConjunto {

	public static void main(String[] args) {

		// --------------------------------------------------
		// Tests Conjunto
		// --------------------------------------------------
		org.junit.runner.JUnitCore.main("tp.practicas.ConjuntoTest");

		// --------------------------------------------------
		// Conjunto 1 - Cardinal
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 - CARDINAL");
		ConjuntoDeStrings c1 = new ConjuntoDeStrings(new String[] { "Hola", "mundo", "!" });
		System.out.println("El cardinal del ConjuntoDeStrings 'c1' es " + c1.cardinal());

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 - No Esta vacio
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 - NO ESTA VACIO");
		System.out.println("El ConjuntoDeStrings 'c1' esta vacio? " + c1.estáVacío());

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 2 - Esta vacio
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 2 - ESTA VACIO");
		ConjuntoDeStrings c2 = new ConjuntoDeStrings(new String[] {});
		System.out.println("El ConjuntoDeStrings 'c2' esta vacio? " + c2.estáVacío());

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 - No anade
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 - NO ANADE");
		System.out.println("Se anade el String 'Hola' al ConjuntoDeString 'c1'? " + c1.añade("Hola"));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 2 - Anade
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 2 - ANADE");
		System.out.println("Se anade el String 'Hello' al ConjuntoDeString 'c2'? " + c2.añade("Hello"));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 - No pertenece
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 - NO PERTENECE");
		System.out.println("Pertenece la String 'Hello' al ConjuntoDeString 'c1'? " + c1.pertenece("Hello"));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 - Pertenece
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 - PERTENECE");
		System.out.println("Pertenece la String 'mundo' al ConjuntoDeString 'c1'? " + c1.pertenece("mundo"));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 & Conjunto 3 - Union nula
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 & CONJUNTO 2 - UNION NULA");
		ConjuntoDeStrings c3 = new ConjuntoDeStrings(new String[] { "Hola" });
		System.out.println("c1 = " + Arrays.toString(c1.elementos()));
		System.out.println("c3 = " + Arrays.toString(c3.elementos()));
		ConjuntoDeStrings temp = c1.unión(c3);
		System.out.println("La union de 'c1' y 'c3' = " + Arrays.toString(temp.elementos()));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 & Conjunto 2 - Union
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 & CONJUNTO 2 - UNION");
		System.out.println("c1 = " + Arrays.toString(c1.elementos()));
		System.out.println("c2 = " + Arrays.toString(c2.elementos()));
		ConjuntoDeStrings temp0 = c1.unión(c2);
		System.out.println("La union de 'c1' y 'c2' = " + Arrays.toString(temp0.elementos()));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 & Conjunto 3 - Interseccion nula
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 & CONJUNTO 2 - INTERSECCION NULA");
		System.out.println("c1 = " + Arrays.toString(c1.elementos()));
		System.out.println("c2 = " + Arrays.toString(c2.elementos()));
		ConjuntoDeStrings temp2 = c1.intersección(c2);
		System.out.println("La interseccion de 'c1' y 'c2' = " + Arrays.toString(temp2.elementos()));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 & Conjunto 3 - Interseccion
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 & CONJUNTO 3 - INTERSECCION");
		System.out.println("c1 = " + Arrays.toString(c1.elementos()));
		System.out.println("c3 = " + Arrays.toString(c3.elementos()));
		ConjuntoDeStrings temp1 = c1.intersección(c3);
		System.out.println("La interseccion de 'c1' y 'c3' = " + Arrays.toString(temp1.elementos()));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 & Conjunto 2 - Diferencia
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 & CONJUNTO 2 - DIFERENCIA");
		System.out.println("c1 = " + Arrays.toString(c1.elementos()));
		c2.añade("Hola");
		System.out.println("c2 = " + Arrays.toString(c2.elementos()));
		ConjuntoDeStrings temp3 = c1.diferencia(c2);
		System.out.println("La diferencia de 'c1' y 'c2' = " + Arrays.toString(temp3.elementos()));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 & Conjunto 2 - No Equals
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 & CONJUNTO 2 - NO EQUALS");
		System.out.println("c1 = " + Arrays.toString(c1.elementos()));
		System.out.println("c2 = " + Arrays.toString(c2.elementos()));
		System.out.println("El ConjuntoDeStrings 'c1' es igual al ConjuntoDeStrings 'c2'? " + c1.equals(c2));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 3 & Conjunto 4 - Equals
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 3 & CONJUNTO 4 - EQUALS");
		ConjuntoDeStrings c4 = new ConjuntoDeStrings(new String[] { "Hola" });
		System.out.println("c3 = " + Arrays.toString(c3.elementos()));
		System.out.println("c4 = " + Arrays.toString(c4.elementos()));
		System.out.println("El ConjuntoDeStrings 'c3' es igual al ConjuntoDeStrings 'c4'? " + c3.equals(c4));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 & Conjunto 3 - No contenido
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 & CONJUNTO 3 - NO CONTENIDO");

		System.out
				.println("Esta el ConjuntoDeStrings 'c1' contenido en el ConjuntoDeStrings 'c3'? " + c3.contenido(c1));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjunto 1 & Conjunto 3 - Contenido
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTO 1 & CONJUNTO 3 - CONTENIDO");
		System.out
				.println("Esta el ConjuntoDeStrings 'c3' contenido en el ConjuntoDeStrings 'c1'? " + c1.contenido(c3));

		System.out.println("\n");

		// --------------------------------------------------
		// Conjuntos - Elementos
		// --------------------------------------------------
		System.out.println("\t\tCONJUNTOS - ELEMENTOS");
		System.out.println("c1 = " + Arrays.toString(c1.elementos()));
		System.out.println("c2 = " + Arrays.toString(c2.elementos()));
		System.out.println("c3 = " + Arrays.toString(c3.elementos()));
		System.out.println("c4 = " + Arrays.toString(c4.elementos()));

	}

}
