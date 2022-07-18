package GUI;

import OOP.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase ConcesionarioMain que representa la ventana principal del concesionario
 */
public class ConcesionarioMain extends JFrame {

	private JButton button = new JButton("Oeste");

	/**
	 * Constructor de la ventana que representa la aplicación gráfica
	 *
	 * @param t título de la ventana principal de la aplicación gráfica
	 */
	public ConcesionarioMain(String t) {
		super(t);
		// Container pane = getContentPane();
		setLocation(5, 50);
		setSize(550, 315);
		setMinimumSize(new Dimension(500, 600));
		setLayout(new BorderLayout());
		// add(new JButton("Norte"), BorderLayout.NORTH);
		// add(new JButton("Sur"), BorderLayout.SOUTH);
		// add(new JButton("Este"), BorderLayout.EAST);
		add(button, BorderLayout.WEST);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VehiculoDlg(new JDialog());
			}
		});

		// pane.add(new JButton("Centro"), BorderLayout.CENTER);
		// pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Método main: ejecuta la aplicación usando tanto la interfaz de consola como
	 * la interfaz gráfica.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		new ConcesionarioMain("Mi Concesionario");
		// new ConcesionarioMainOk("Mi Concesionario (SOLUCIÓN)");
	}

	/**
	 * Inicializa el concesionario con varios arrendatario con sus correspondientes
	 * vehiculos
	 */
	private static void initConcesionario(Concesionario concesionarioPP) {
		Arrendatario p1 = new Arrendatario("Pepe");
		Arrendatario p2 = new Arrendatario("Luis");
		Arrendatario p3 = new Arrendatario("Ana");
		Arrendatario p4 = new Arrendatario("David");
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