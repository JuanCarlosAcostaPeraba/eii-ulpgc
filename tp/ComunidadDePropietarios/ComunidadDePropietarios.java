package ComunidadDePropietarios;

// Paquetes importados
import java.util.*;

/**
 * Clase ComunidadDePropietarios
 * 
 * @author JuanSerberoCode
 */
public class ComunidadDePropietarios {

	// Atributos
	private String nombre;
	private double presupuesto;
	private LinkedList<Propietario> comunidad = new LinkedList<Propietario>();

	/**
	 * Constructor
	 * 
	 * @param name Nombre de la comunidad
	 * @param p    Resupuesto de la comunidad
	 */
	public ComunidadDePropietarios(String name, double p) {
		nombre = name;
		presupuesto = p;
	}

	/**
	 * Metodo que anade un propietario a la comunidad
	 * 
	 * @param propietario Propietario de la comunidad
	 */
	public void addPropietario(Propietario propietario) {
		boolean existe = false;
		int pos = -1;
		for (int i = 0; i < comunidad.size(); i++) {
			if (propietario.equals(comunidad.get(i))) {
				existe = true;
				pos = i;
				break;
			}
		}
		if (existe) {
			comunidad.set(pos, propietario);
		} else {
			comunidad.add(propietario);
		}
	}

	/**
	 * Metodo que elimina un propietario de la comunidad
	 * 
	 * @param name Nombre del propietario a eliminar
	 */
	public void removePropietario(String name) {
		for (int i = 0; i < comunidad.size(); i++) {
			if (name == comunidad.get(i).getNombre()) {
				comunidad.remove(i);
				break;
			}
		}
	}

	/**
	 * Metodo que devuleve la cuota mesual de un propietario
	 * 
	 * @param propietario Propietario de la comunidad
	 * @return Cuota mesual del propietario
	 */
	public double cuotaMensual(Propietario propietario) {
		return ((propietario.cuotaTotal() * presupuesto / 100) / 12);
	}

	/**
	 * Metodo que devuelve los datos de un propietario si existe en la comunidad
	 * 
	 * @param name Nombre del propietario
	 * @return Datos del propietario
	 */
	public Propietario getPropietario(String name) {
		for (int i = 0; i < comunidad.size(); i++) {
			if (name == comunidad.get(i).getNombre()) {
				return comunidad.get(i);
			}
		}
		return null;
	}

	/**
	 * Metodo que devuelve una lista con el numero de fincas de cada tipo
	 * 
	 * @return Lista con el numero de fincas de cada tipo
	 */
	public List<String> getFincasDistintas() {
		LinkedList<String> fincasTodas = new LinkedList<String>(); // lista con todas las fincas
		LinkedList<String> fincasTipo = new LinkedList<String>(); // lista con los tipos de finca
		List<String> result = new LinkedList<String>(); // lista de ristras

		for (int i = 0; i < comunidad.size(); i++) { // mete todas las fincas en un linkedlist y mete los tipos en otra
														// linkedlist
			for (int j = 0; j < comunidad.get(i).getFincas().size(); j++) {
				fincasTodas.add(comunidad.get(i).getFincas().get(j).getTipo());
				if (!fincasTipo.contains(comunidad.get(i).getFincas().get(j).getTipo())) {
					fincasTipo.add(comunidad.get(i).getFincas().get(j).getTipo());
				}
			}
		}
		Collections.sort(fincasTipo, new Comparator<String>() { // ordena lista con los tipos de finca
			public int compare(String tipo1, String tipo2) {
				return tipo1.compareTo(tipo2);
			}
		});

		for (int i = 0; i < fincasTipo.size(); i++) { // recorre lista con los tipos de finca
			int contador = 0;
			String ristra = fincasTipo.get(i) + ": ";
			for (int j = 0; j < fincasTodas.size(); j++) { // recorre lista con todas las fincas
				if (fincasTipo.get(i) == fincasTodas.get(j)) {
					contador++;
				}
			}
			ristra += contador;
			result.add(ristra);
		}

		return result;
	}

	/**
	 * Metodo que devuelve todos los datos de la comunidad en un string
	 * 
	 * @return String con los datos de la comunidad
	 */
	public String toString() {
		List<Propietario> comunidadCopia = new LinkedList<>(comunidad);
		Collections.copy(comunidadCopia, comunidad);
		Collections.sort(comunidadCopia, new Comparator<Propietario>() {
			public int compare(Propietario p1, Propietario p2) {
				return p1.getNombre().compareTo(p2.getNombre());
			}
		});
		String result = "";
		for (int i = 0; i < comunidadCopia.size(); i++) {
			if (i == comunidadCopia.size() - 1) {
				result += comunidadCopia.get(i).getNombre();
				break;
			}
			result += comunidadCopia.get(i).getNombre() + " - ";
		}
		return result;
	}

}
