package ComunidadDePropietarios;

// Paquetes importados
import java.awt.*;
import javax.swing.*;

/**
 * Clase ComunidadDePropietariosMain
 * 
 * @author JuanSerberoCode
 */
public class ComunidadDePropietariosMain extends JFrame {

	// Atributos
	private JPanel window = new JPanel();
	private JTextArea comunidades = new JTextArea();
	private JRadioButton addPropietario = new JRadioButton("Nuevo propietario", true);
	private JRadioButton removePropietario = new JRadioButton("Eliminar propietario", false);

	public static void main(String[] args) {
		new ComunidadDePropietariosMain();
	}

	/**
	 * Constructor de la UI
	 */
	public ComunidadDePropietariosMain() {
		// crear el window
		window.setLayout(new BorderLayout());

		// establece el titulo del window
		setTitle("Interfaz");

		// Jpanels
		window.add(area(), BorderLayout.CENTER);
		window.add(botones(), BorderLayout.EAST);
		setContentPane(window);

		// visible y tamaño
		setSize(400, 200);
		setVisible(true);

		// cerrar el programa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Metodo que crea el area de texto
	 * 
	 * @return JTextArea
	 */
	private Component area() {
		JPanel area = new JPanel();
		area.setLayout(new BorderLayout());
		JLabel tituloArea = new JLabel("Propietarios");
		comunidades.setEditable(true); // text area con las comunidades
		JScrollPane c = new JScrollPane(comunidades);
		area.add(tituloArea, BorderLayout.NORTH);
		area.add(c);
		return area;
	}

	/**
	 * Metodo que devuelve un JPanel con los botones
	 * 
	 * @return JPanel con los botones
	 */
	private Component botones() {
		JPanel botones = new JPanel();
		JLabel tituloBotones = new JLabel("Escoge:");
		botones.add(tituloBotones);
		botones.add(addPropietario);
		botones.add(removePropietario);
		return botones;
	}
}