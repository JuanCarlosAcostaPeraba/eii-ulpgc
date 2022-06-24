package ComunidadDePropietarios;

// Paquetes importados
import java.util.LinkedList;
import java.util.List;

/**
 * Clase ComunidadDePropietarios
 * 
 * @author JuanSerberoCode
 */
public class Propietario {

	// Atributos
	private String nombre;
	private LinkedList<Finca> fincas = new LinkedList<>();

	/**
	 * Constructor
	 * 
	 * @param name Nombre del propietario
	 */
	public Propietario(String name) {
		nombre = name;
	}

	/**
	 * Metodo que devulve el nombre del propietario
	 * 
	 * @return Nombre del propietario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que anade una finca a la lista de fincas del propietario
	 * 
	 * @param finca Finca a anadir
	 */
	public void addFinca(Finca finca) {
		if (!repetido(finca)) {
			fincas.add(finca);
		}
	}

	/**
	 * Metodo que elimina una finca de la lista de fincas del propietario
	 * 
	 * @param nombre Nombre de la finca a eliminar
	 */
	public void removeFinca(String nombre) {
		for (int i = 0; i < fincas.size(); i++) {
			if (nombre == fincas.get(i).getNombre()) {
				fincas.remove(i);
			}
		}
	}

	/**
	 * Metodo que devuelve la lista de fincas del propietario
	 * 
	 * @return Lista de fincas del propietario
	 */
	public List<Finca> getFincas() {
		return fincas;
	}

	/**
	 * Metodo que calcula la cuota del propietario
	 * 
	 * @return Cuota del propietario
	 */
	public double cuotaTotal() {
		double sumatorio = 0.0;
		for (int i = 0; i < fincas.size(); i++) {
			sumatorio += fincas.get(i).getCuota();
		}
		return sumatorio;
	}

	/**
	 * Metodo que devuelve los datos del propietario en formato String
	 * 
	 * @return Datos del propietario en formato String
	 */
	public String toString() {
		String result = getNombre() + ":" + cuotaTotal() + "\n";
		for (int i = 0; i < fincas.size(); i++) {
			if (i == fincas.size() - 1) { // ultima vuelta
				result += (i + 1) + ") " + fincas.get(i).toString();
				break;
			}
			result += (i + 1) + ") " + fincas.get(i).toString() + "\n";
		}
		return result;
	}

	/**
	 * Metodo que comprueba si una finca ya esta en la lista de fincas del
	 * propietario
	 * 
	 * @param finca Finca a comprobar
	 * @return True si la finca ya esta en la lista de fincas del propietario, false
	 *         en caso contrario
	 */
	private boolean repetido(Finca finca) {
		for (int i = 0; i < fincas.size(); i++) {
			if (finca.equals(fincas.get(i))) {
				return true;
			}
		}
		return false;
	}
}
