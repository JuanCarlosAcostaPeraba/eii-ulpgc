package OOP;

/**
 * Clase Vehículo que representa los vehículos del concesionario
 *
 * @author TP
 * @version 21/01/2022
 *
 */
public class Vehiculo {
	private String matricula; // matrícula del vehículo
	private String tipo; // tipo de la vehículo: "Turismo", "Motocicleta", "Camión" o "Autobús"
	private double cuota; // cuota de alquiler

	/**
	 * Constructor al que se le pasa el matricula, el tipo de vehículo y la cuota
	 * de alquiler
	 *
	 * @param matricula, matrícula del vehículo
	 * @param tipo,      tipo de vehículo
	 * @param cuota,     cuota de alquiler
	 */
	public Vehiculo(String matricula, String tipo, double cuota) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.cuota = cuota;
	}

	/**
	 * Devuelve el matrícula del vehículo
	 *
	 * @return matrícula del vehículo
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Devuelve el nombre el tipo de vehículo
	 *
	 * @return tipo de vehículo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Devuelve la cuota de alquiler
	 *
	 * @return cuota de alquile
	 */
	public double getCuota() {
		return cuota;
	}

	/**
	 * @return String con la concatenación entre corchetes de la matrícula, el
	 *         tipo y la cuota de participación.
	 */
	public String toString() {
		return "[Matricula: " + matricula + " tipo: " + tipo + " cuota: " + cuota + "]";
	}
}