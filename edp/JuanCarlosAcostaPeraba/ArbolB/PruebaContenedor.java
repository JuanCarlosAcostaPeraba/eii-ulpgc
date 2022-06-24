package ArbolB;

// Paquetes importados
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Clase PruebaContenedor
 * 
 * @author JuanSerberoCode
 */
public class PruebaContenedor {

	public static void main(String[] args) throws IOException {
		System.out.println("||Practica 4||\n\n");

		ContenedorDeEnteros a = new ContenedorDeEnteros();
		a.crear("temp", 10);
		int[] v;
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");
		for (int i = 0; i < 10; i++) {
			a.insertar(i);
			a.buscar(i);
		}
		a.buscar(1);
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");
		v = a.elementos();
		for (int i = 0; i < a.cardinal(); i++) {
			System.out.println(v[i]);
		}
		a.vaciar();
		for (int i = 0; i < 100; i++) {
			a.insertar(i);
			a.extraer(i);
		}
		a.cerrar();
		a.abrir("temp");
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");
		v = a.elementos();
		for (int i = 0; i < a.cardinal(); i++) {
			System.out.println(v[i]);
		}
		a.cerrar();

		File file = new File("salida4.txt");

		file.delete();
		file.createNewFile();

		pruebas(file, "orden5", 5);
		pruebas(file, "orden7", 7);
		pruebas(file, "orden9", 9);
		pruebas(file, "orden11", 11);
		pruebas(file, "orden20", 20);
		pruebas(file, "orden25", 25);
		pruebas(file, "orden55", 55);
		pruebas(file, "orden75", 75);
		pruebas(file, "orden105", 105);
		pruebas(file, "orden201", 201);
		pruebas(file, "orden301", 301);

	}

	public static void pruebas(File file, String nombreFichero, int orden) throws IOException {

		// -------------------------------------------------------------------------------------
		// PRUEBAS
		// -------------------------------------------------------------------------------------

		System.out.println("PRUEBAS CON " + nombreFichero);

		ContenedorDeEnteros a = new ContenedorDeEnteros();

		a.crear(nombreFichero, orden);

		RandomAccessFile datos = new RandomAccessFile("datos.dat", "r");
		RandomAccessFile datosNo = new RandomAccessFile("datos_no.dat", "r");

		int[] vectorDatos = new int[100000];
		int[] vectorDatosNo = new int[20000];

		for (int i = 0; i < 100000; i++) {
			datos.seek(i * 4);
			vectorDatos[i] = datos.readInt();
		}
		for (int i = 0; i < 20000; i++) {
			datosNo.seek(i * 4);
			vectorDatosNo[i] = datosNo.readInt();
		}

		datos.close();
		datosNo.close();

		// -------------------------------------------------------------------------------------
		// INSERCIONES
		// -------------------------------------------------------------------------------------

		System.out.println("\n\t---INSERCIONES---");

		double[] inserciones = new double[10];
		double tiempoInsercion;
		for (int i = 0; i < 10; i++) {
			tiempoInsercion = System.currentTimeMillis();
			for (int j = 0; j < 10000; j++) {
				a.insertar(vectorDatos[i * 10000 + j]);
			}
			inserciones[i] = (System.currentTimeMillis() - tiempoInsercion) / 10;
		}
		System.out.println("\nTiempo promedio (ms) por cada 1000 inserciones: " + Arrays.toString(inserciones));
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");

		// -------------------------------------------------------------------------------------
		// EXTRACCIONES
		// -------------------------------------------------------------------------------------

		System.out.println("\n\t---EXTRACCIONES---");

		double[] extracciones = new double[10];
		double tiempoExtraccion;
		for (int i = 0; i < 10; i++) {
			tiempoExtraccion = System.currentTimeMillis();
			for (int j = 0; j < 10000; j++) {
				a.extraer(vectorDatos[i * 10000 + j]);
			}
			extracciones[i] = (System.currentTimeMillis() - tiempoExtraccion) / 10;
		}
		System.out.println("\nTiempo promedio (ms) por cada 1000 extracciones: " + Arrays.toString(extracciones));
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");

		// -------------------------------------------------------------------------------------
		// BUSQUEDAS EXITOSAS
		// -------------------------------------------------------------------------------------

		System.out.println("\n\t---BUSQUEDAS EXITOSAS---");

		double[] busquedasExitosas = new double[10];
		double tiempoBusquedaExitosa;
		a.vaciar();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10000; j++) {
				a.insertar(vectorDatos[i * 10000 + j]);
			}
			tiempoBusquedaExitosa = System.currentTimeMillis();
			for (int x = 0; x < 10000 * (i + 1); x++) {
				a.buscar(vectorDatos[x]);
			}
			busquedasExitosas[i] = (System.currentTimeMillis() - tiempoBusquedaExitosa) / ((i + 1) * 10);
		}
		System.out.println(
				"\nTiempo promedio (ms) por cada 1000 busquedas exitosas: " + Arrays.toString(busquedasExitosas));
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");

		// -------------------------------------------------------------------------------------
		// BUSQUEDAS INFRUCTUOSAS
		// -------------------------------------------------------------------------------------

		System.out.println("\n\t---BUSQUEDAS INFRUCTUOSAS---");

		double[] busquedasInfructuosas = new double[10];
		double tiempoBusquedaInfructuosa;
		a.vaciar();
		System.out.println("\nEl contenedor a tiene " + a.cardinal() + " elementos.");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10000; j++) {
				a.insertar(vectorDatos[i * 10000 + j]);
			}
			tiempoBusquedaInfructuosa = System.currentTimeMillis();
			for (int x = 0; x < 20000; x++) {
				a.buscar(vectorDatosNo[x]);
			}
			busquedasInfructuosas[i] = (System.currentTimeMillis() - tiempoBusquedaInfructuosa) / 20;
		}
		System.out.println(
				"Tiempo promedio (ms) por cada 1000 busquedas infructuosas: " + Arrays.toString(busquedasInfructuosas));
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");

		// -------------------------------------------------------------------------------------
		// ESCRITURA DE FICHERO DE SALIDA
		// -------------------------------------------------------------------------------------

		FileWriter fw = new FileWriter(file, true);

		fw.write("\n\n\n\n\t\t------ARBOL B " + nombreFichero + "------\n\n");

		fw.write("\n\n\t---INSERCIONES---");

		fw.write("\n\nTiempo promedio (ms) por cada 1000 inserciones: " + Arrays.toString(inserciones));

		fw.write("\n\n\t---EXTRACCIONES---");

		fw.write("\n\nTiempo promedio (ms) por cada 1000 extracciones: " + Arrays.toString(extracciones));

		fw.write("\n\n\t---BUSQUEDAS EXITOSAS---");

		fw.write("\n\nTiempo promedio (ms) por cada 1000 busquedas exitosas: " + Arrays.toString(busquedasExitosas));

		fw.write("\n\n\t---BUSQUEDAS INFRUCTUOSAS---");

		fw.write("\n\nTiempo promedio (ms) por cada 1000 busquedas infructuosas: "
				+ Arrays.toString(busquedasInfructuosas));

		fw.close();
		System.out.println("\n\n\n***TERMINADO***\n\n\n");

		a.cerrar();
	}
}
