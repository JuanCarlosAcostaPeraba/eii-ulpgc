package ArbolBinarioDeBusqueda;

// Paquetes importados
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

	public static void main(String[] args) throws IOException {
		System.out.println("||Practica 3||\n");

		ContenedorDeEnteros b = new ContenedorDeEnteros();

		// Contenedor vacio
		System.out.println(b.buscar(1));
		System.out.println(Arrays.toString(b.elementos()));
		System.out.println(b.extraer(1));

		System.out.println("El contenedor b tiene " + b.cardinal() + " elementos.");
		// Contenedor lleno
		b.insertar(8);
		b.insertar(3);
		b.insertar(10);
		b.insertar(1);
		b.insertar(6);
		b.insertar(14);
		b.insertar(4);
		b.insertar(7);
		b.insertar(13);
		int[] v;
		v = b.elementos();
		System.out.println(Arrays.toString(v));
		System.out.println("El contenedor b tiene " + b.cardinal() + " elementos.");
		// Extraer
		b.extraer(6);
		b.extraer(10);
		b.extraer(7);
		v = b.elementos();
		System.out.println(Arrays.toString(v));
		System.out.println("El contenedor b tiene " + b.cardinal() + " elementos.");

		// -------------------------------------------------------------------------------------
		// PRUEBAS
		// -------------------------------------------------------------------------------------

		ContenedorDeEnteros a = new ContenedorDeEnteros();

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
			tiempoInsercion = System.nanoTime();
			for (int j = 0; j < 10000; j++) {
				a.insertar(vectorDatos[i * 10000 + j]);
			}
			inserciones[i] = (System.nanoTime() - tiempoInsercion) / 10000000;
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
			tiempoExtraccion = System.nanoTime();
			for (int j = 0; j < 10000; j++) {
				a.extraer(vectorDatos[i * 10000 + j]);
			}
			extracciones[i] = (System.nanoTime() - tiempoExtraccion) / 10000000;
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
			for (int k = 0; k < 1000; k++) {
				for (int x = 0; x < 10000 * (i + 1); x++) {
					a.buscar(vectorDatos[x]);
				}
			}
			busquedasExitosas[i] = (System.currentTimeMillis() - tiempoBusquedaExitosa) / ((i + 1) * 10);
		}
		for (int i = 0; i < 10; i++) {
			busquedasExitosas[i] = busquedasExitosas[i] / 1000;
		}
		System.out.println("\nTiempo promedio (ms) por cada 1000 busquedas exitosas (1000 iteraciones cada busqueda): "
				+ Arrays.toString(busquedasExitosas));
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
			for (int k = 0; k < 1000; k++) {
				for (int x = 0; x < 20000; x++) {
					a.buscar(vectorDatosNo[x]);
				}
			}
			busquedasInfructuosas[i] = (System.currentTimeMillis() - tiempoBusquedaInfructuosa) / 20;
		}
		for (int i = 0; i < 10; i++) {
			busquedasInfructuosas[i] = busquedasInfructuosas[i] / 1000;
		}
		System.out
				.println("Tiempo promedio (ms) por cada 1000 busquedas infructuosas (1000 iteraciones cada busqueda): "
						+ Arrays.toString(busquedasInfructuosas));
		System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");

		// -------------------------------------------------------------------------------------
		// ESCRITURA DE FICHERO DE SALIDA
		// -------------------------------------------------------------------------------------

		File file = new File("salida3.txt");
		file.createNewFile();

		PrintWriter pw = new PrintWriter(file);

		pw.println("\t\t------ARBOL BINARIO------\n\n");

		pw.println("\t---INSERCIONES---");

		pw.println("\nTiempo promedio (ms) por cada 1000 inserciones: " + Arrays.toString(inserciones));

		pw.println("\n\t---EXTRACCIONES---");

		pw.println("\nTiempo promedio (ms) por cada 1000 extracciones: " + Arrays.toString(extracciones));

		pw.println("\n\t---BUSQUEDAS EXITOSAS---");

		pw.println("\nTiempo promedio (ms) por cada 1000 busquedas exitosas (1000 iteraciones cada busqueda): "
				+ Arrays.toString(busquedasExitosas));

		pw.println("\n\t---BUSQUEDAS INFRUCTUOSAS---");

		pw.println("\nTiempo promedio (ms) por cada 1000 busquedas infructuosas (1000 iteraciones cada busqueda): "
				+ Arrays.toString(busquedasInfructuosas));

		pw.close();
		System.out.println("\n\n\n***TERMINADO***");
	}

}
