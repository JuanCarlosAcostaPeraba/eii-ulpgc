package VectorOrdenado;

//Paquetes importados
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Clase PruebaContenedor
 * 
 * @author JuanSerberoCode
 */
public class PruebaContenedor {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		ContenedorDeEnteros b = new ContenedorDeEnteros(100050);

		int[] v;
		System.out.println("El contenedor b tiene " + b.cardinal() + " elementos.");
		for (int i = 0; i < 10; i++) {
			b.insertar(i);
			b.buscar(i);
		}
		v = b.elementos();
		for (int i = 0; i < b.cardinal(); i++)
			System.out.println(v[i]);
		b.vaciar();
		for (int i = 0; i < 100; i++) {
			b.insertar(i);
			b.extraer(i);
		}

		b.insertar(10);
		b.insertar(20);
		b.insertar(30);
		b.insertar(-10);
		b.insertar(-20);
		b.insertar(-30);
		v = b.elementos();
		for (int i : v) {
			System.out.println(i);
		}
		System.out.println("El contenedor b tiene " + b.cardinal() + " elementos.");
		b.extraer(100);
		b.extraer(200);
		b.extraer(30);
		b.extraer(-20);
		v = b.elementos();
		for (int i : v) {
			System.out.println(i);
		}
		System.out.println("El contenedor b tiene " + b.cardinal() + " elementos.");
		b.vaciar();
		b.extraer(100);
		b.extraer(500);
		System.out.println("El contenedor b tiene " + b.cardinal() + " elementos.");

		// -------------------------------------------------------------------------------------
		// PRUEBAS
		// -------------------------------------------------------------------------------------

		ContenedorDeEnteros a = new ContenedorDeEnteros(100050);

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
		for (int i = 0; i < 10; i++) {
			busquedasExitosas[i] = busquedasExitosas[i] / 1000;
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
		for (int i = 0; i < 10; i++) {
			busquedasInfructuosas[i] = busquedasInfructuosas[i] / 1000;
		}
		System.out.println(
				"Tiempo promedio (ms) por cada 1000 busquedas infructuosas: " + Arrays.toString(busquedasInfructuosas));
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");

		// -------------------------------------------------------------------------------------
		// ESCRITURA DE FICHERO DE SALIDA
		// -------------------------------------------------------------------------------------

		File file = new File("salida2.txt");
		file.createNewFile();

		PrintWriter pw = new PrintWriter(file);

		pw.println("\t\t------VECTOR ORDENADO------\n\n");

		pw.println("\t---INSERCIONES---");

		pw.println("\nTiempo promedio (ms) por cada 1000 inserciones: " + Arrays.toString(inserciones));

		pw.println("\n\t---EXTRACCIONES---");

		pw.println("\nTiempo promedio (ms) por cada 1000 extracciones: " + Arrays.toString(extracciones));

		pw.println("\n\t---BUSQUEDAS EXITOSAS---");

		pw.println("\nTiempo promedio (ms) por cada 1000 busquedas exitosas: " + Arrays.toString(busquedasExitosas));

		pw.println("\n\t---BUSQUEDAS INFRUCTUOSAS---");

		pw.println("\nTiempo promedio (ms) por cada 1000 busquedas infructuosas: "
				+ Arrays.toString(busquedasInfructuosas));

		pw.close();
		System.out.println("\n\n\n***TERMINADO***");
	}

}