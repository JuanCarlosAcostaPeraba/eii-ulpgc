package OOP;

// paquetes importados
import java.util.*;

public class Arrendatario {

	// Atributos
	private String nombre;
	private List<Vehiculo> vehiculos;

	/**
	 * Constructor
	 * @param name
	 */
	public Arrendatario(String name) {
		nombre = name;
		vehiculos = new LinkedList<Vehiculo>();
	}

	/**
	 * Metodo que devuelve el nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que anade nuevos vehiculos
	 * @param vehicle
	 */
	public void addVehiculo(Vehiculo vehicle) {
		boolean exist = false;
		for (Vehiculo x : vehiculos) {
			if (x.equals(vehicle)) {
				exist = true;
				break;
			}
		}
		if (!exist) {
			vehiculos.add(vehicle);
		}
	}

	/**
	 * Metodo que borra un vehiculo
	 * @param licence
	 */
	public void removeVehiculo(String licence) {
		for (Vehiculo x : vehiculos) {
			if (x.getMatricula().equals(licence)) {
				vehiculos.remove(x);
				break;
			}
		}
	}

	/**
	 * Metodo que devuelve una lista con todos los vehiculos
	 * @return
	 */
	public List<Vehiculo> getVehiculos() {
		List<Vehiculo> temp = new LinkedList<>(vehiculos);
		Collections.copy(temp, vehiculos);
		return temp;
	}

	/**
	 * Metodo que devuelve la suma de todas las cuotas
	 * @return
	 */
	public double cuotaTotal() {
		double temp = 0.;
		for (Vehiculo x : vehiculos) {
			temp += x.getCuota();
		}
		return temp;
	}

	/**
	 * Metodo que devuelve un string con todos los datos del arrendatario 
	 * @return
	 */
	@Override
	public String toString() {
		String temp = getNombre() + ":" + cuotaTotal() + "\n";
		for (int i = 0; i < getVehiculos().size(); i++) {
			temp += (i + 1) + ") [Matricula: " + getVehiculos().get(i).getMatricula() 
					+ " tipo: " + getVehiculos().get(i).getTipo() 
					+ " cuota: " + getVehiculos().get(i).getCuota() + "]\n";
		}
		return temp;
	}

}
