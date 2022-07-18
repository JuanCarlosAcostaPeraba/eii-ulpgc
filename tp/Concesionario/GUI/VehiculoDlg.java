package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase VehiculoDlg que representa el diálogo para añadir vehículos
 */
public class VehiculoDlg extends JDialog {
	// Panel principal del diálogo
	private Container contentPane;
	// Componentes visuales del diálogo
	private final JTextField matriculaTxf;
	private final JComboBox<String> tipoVehiculoCbx;
	private final JTextField cuotaVehiculoTxf;
	private final JButton okBtn;
	private final JButton cancelBtn;
	private boolean wasOk; // wasOk = true : los datos introducidos son válidos
							// wasOk = false : los datos introducidos no se tienen en cuenta
	private String tipoVehiculo; // almacena el tipo de vehículo introducido
	// Listener que valida los datos de la nuevo vehículo
	private ActionListener okHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			wasOk = true;
			setVisible(false);
		}
	};
	// Listener que descarta los datos del nuevo vehículo
	private ActionListener cancelHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
		}
	};

	/**
	 * Añade un nuevo componente al panel principal precedido con una etiqueta y
	 * con la alineación indicada
	 */
	private void addEntryLine(String text, JComponent comp, int aling) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(aling));
		panel.add(new JLabel(text + ":"));
		panel.add(comp);
		contentPane.add(panel);
	}

	/**
	 * Añade los botones de la aplicación al panel principal
	 */
	private void addButtons() {
		JPanel buttons = new JPanel();
		buttons.add(okBtn);
		buttons.add(cancelBtn);
		contentPane.add(buttons);
	}

	/**
	 * Constructor del diálogo al que se le pasa una referencia a la ventana
	 * principal de la que depende
	 *
	 * @param f referencia al frame de la ventana principal
	 */
	public VehiculoDlg(JDialog f) {
		// Se inicializan las opciones del diálogo
		super(f, "Nuevo vehículo");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(f);
		wasOk = false;
		tipoVehiculo = "";
		// Se crea el componente para introducir la matrícula del vehículo
		matriculaTxf = new JTextField(10);
		matriculaTxf.addActionListener(okHandler);
		// Se crea el componente para introducir la cuota del vehículo
		cuotaVehiculoTxf = new JTextField(6);
		cuotaVehiculoTxf.addActionListener(okHandler);
		// Se crea el componente para introducir el tipo del vehículo
		tipoVehiculoCbx = new JComboBox<>(new String[] { "Turismo", "Motocicleta", "Camión", "Autobús" });
		tipoVehiculoCbx.setSelectedItem("Turismo");
		tipoVehiculo = (String) tipoVehiculoCbx.getSelectedItem();
		tipoVehiculoCbx.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					tipoVehiculo = (String) tipoVehiculoCbx.getSelectedItem();
				}
			}
		});
		// Se crean los botones de interacción con el diálogo
		okBtn = new JButton("Aceptar");
		okBtn.addActionListener(okHandler);
		cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener(cancelHandler);
		// Interfaz del panel principal al que se le añaden los componentes visuales
		contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		addEntryLine("Matricula ", matriculaTxf, FlowLayout.RIGHT);
		addEntryLine("Cuota ", cuotaVehiculoTxf, FlowLayout.LEFT);
		addEntryLine("Tipo ", tipoVehiculoCbx, FlowLayout.LEFT);
		addButtons();
		pack();
	}

	/**
	 * ", cuotaVehiculoTxf,FlowLayout.LEFT); ", tipoVehiculoCbx,FlowLayout.LEFT);
	 * Devuelve el matricula del vehiculo
	 *
	 * @return matrícula del vehículo introducido
	 */
	public String getMatricula() {
		return matriculaTxf.getText();
	}

	/**
	 * Devuelve cuota vehículo
	 *
	 * @return cuota vehículo introducido
	 */
	public String getCuotaVehiculo() {
		return cuotaVehiculoTxf.getText();
	}

	/**
	 * Devuelve el tipo de vehículo
	 *
	 * @return tipo del vehículo introducido
	 */
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	/**
	 * Devuelve si los datos introducidos se aceptan o no
	 *
	 * @return true los datos del vehículo introducido son válidos o
	 * @return false y los datos del vehículo introducido no se tienen en cuenta
	 */
	public boolean isOk() {
		return wasOk;
	}
}