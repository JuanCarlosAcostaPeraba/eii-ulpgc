package OOP;

public class ConcesionarioMain {

	public static void main(String[] args) {
		Concesionario miConcesionario = new Concesionario("PepitoRentaCar", 18000);
		initConcesionario(miConcesionario);
		System.out.println(miConcesionario);
	}

	/**
	 * Inicializa el concesionario con varios arrendatario con sus correspondientes
	 * vehiculos
	 */
	private static void initConcesionario(Concesionario concesionarioPP) {

		Arrendatario p1 = new Arrendatario("a1");
		Arrendatario p2 = new Arrendatario("a2");
		Arrendatario p3 = new Arrendatario("a3");
		Arrendatario p4 = new Arrendatario("a4");
		p1.addVehiculo(new Vehiculo("t1", "Turismo", 7));
		p2.addVehiculo(new Vehiculo("t2", "Turismo", 9));
		p3.addVehiculo(new Vehiculo("t3", "Turismo", 7));
		p2.addVehiculo(new Vehiculo("t4", "Turismo", 5));
		p4.addVehiculo(new Vehiculo("a1", "Autobús", 9));
		p4.addVehiculo(new Vehiculo("a2", "Autobús", 12));
		p1.addVehiculo(new Vehiculo("m1", "Motocicleta", 2));
		p2.addVehiculo(new Vehiculo("m2", "Motocicleta", 2));
		p3.addVehiculo(new Vehiculo("m3", "Motocicleta", 1.5));
		p3.addVehiculo(new Vehiculo("c1", "Camión", 1));
		p2.addVehiculo(new Vehiculo("c2", "Camión", 1));
		p1.addVehiculo(new Vehiculo("c3", "Camión", 0.5));
		concesionarioPP.addArrendatario(p4);
		concesionarioPP.addArrendatario(p3);
		concesionarioPP.addArrendatario(p2);
		concesionarioPP.addArrendatario(p1);

	}
}
