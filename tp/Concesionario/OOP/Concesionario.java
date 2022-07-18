package OOP;

// paquetes importados
import java.util.*;

public class Concesionario {

	// Atributos
	private String nombre;
	private double costeAnual;
	private List<Arrendatario> arrendatarios;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param anualCost
	 */
	public Concesionario(String name, double anualCost) {
		nombre = name;
		costeAnual = anualCost;
		arrendatarios = new LinkedList<Arrendatario>();
	}

	/**
	 * Metodo para anadir arrendatario
	 * 
	 * @param arrendatario
	 */
	public void addArrendatario(Arrendatario arrendatario) {
		boolean exist = false;
		for (int i = 0; i < arrendatarios.size(); i++) {
			if (arrendatarios.get(i).getNombre().equals(arrendatario.getNombre())) {
				arrendatarios.set(i, arrendatario);
				exist = true;
				break;
			}
		}
		if (!exist) {
			arrendatarios.add(arrendatario);
		}
	}

	/**
	 * Metodo para eliminar arrendatario
	 * @param name
	 */
	public void removeArrendatario(String name) {
		for (int i = 0; i < arrendatarios.size(); i++) {
			if (arrendatarios.get(i).getNombre().equals(name)) {
				arrendatarios.remove(i);
				break;
			}
		}
	}

	/**
	 * Metodo para obtener la cuota mensual del arrendatario
	 * 
	 * @param arrendatario
	 * @return
	 */
	public double cuotaMensual(Arrendatario arrendatario) {
		return getArrendatario(arrendatario.getNombre()).cuotaTotal();
	}

	/**
	 * Metodo para encontrar un arrendatario
	 * 
	 * @param name
	 * @return
	 */
	public Arrendatario getArrendatario(String name) {
		for (int i = 0; i < arrendatarios.size(); i++) {
			if (arrendatarios.get(i).getNombre().equals(name)) {
				return arrendatarios.get(i);
			}
		}
		return null;
	}

	/**
	 * Metodo para obtener una lista con todos los tipos de vehiculos
	 * @return
	 */
	public List<String> getVehiculosDistintos() {
		List<String> vehiculosTodos = new LinkedList<String>();
		List<String> vehiculosTipo = new LinkedList<String>();
		List<String> result = new LinkedList<String>();
		for (Arrendatario a : arrendatarios) {
			for (Vehiculo v : a.getVehiculos()) {
				vehiculosTodos.add(v.getTipo());
				if (!vehiculosTipo.contains(v.getTipo())) {
					vehiculosTipo.add(v.getTipo());
				}
			}
		}
		Collections.sort(vehiculosTipo, new Comparator<String>() {
			public int compare(String tipo1, String tipo2) {
				return tipo1.compareTo(tipo2);
			}
		});
		for (int i = 0; i < vehiculosTipo.size(); i++) {
			int contador = 0;
			String ristra = vehiculosTipo.get(i) + ": ";
			for (int j = 0; j < vehiculosTodos.size(); j++) {
				if (vehiculosTipo.get(i) == vehiculosTodos.get(j)) {
					contador++;
				}
			}
			ristra += contador;
			result.add(ristra);
		}
		return result;
	}

	/**
	 * Metodo para obtener toda la informacion en un String
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		List<Arrendatario> copy = new LinkedList<>(arrendatarios);
		Collections.copy(copy, arrendatarios);
		Collections.sort(copy, new Comparator<Arrendatario>() {
			public int compare(Arrendatario a1, Arrendatario a2) {
				return a1.getNombre().compareTo(a2.getNombre());
			}
		});
		String temp = "";
		for (Arrendatario x : copy) {
			temp += x.toString() + "\n";
		}
		return temp;
	}

}
