package tp.practicas;

// Paquetes importados
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager.*;

/**
 * Clase AddStudentDialog
 * 
 * @author JuanSerberoCode
 */
class AddStudentDialog extends JDialog {

    // Atributos
    private JTextField id;
    private JTextField name;

    private JButton accept;
    private JButton cancel;

    /**
     * Constructor
     * 
     * @param ventana desde la que se ejecuta el evento
     */
    public AddStudentDialog(JFrame ventana) {
        // Inputs de texto
        id = new JTextField(6);
        name = new JTextField(10);

        // Evento para que el id sea caracter num
        id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' // key de numeros
                        || e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) { // key borrar
                    id.setEditable(true);
                } else {
                    id.setEditable(false);
                }
            }
        });

        // Labels
        JLabel idLabel = new JLabel("Student ID: ");
        JLabel nameLabel = new JLabel("Student name: ");

        // Title
        setTitle("Add new student");

        // Window
        Container window = getContentPane();
        window.setLayout(new BorderLayout());

        // Student id div
        JPanel student_id = new JPanel();
        student_id.setLayout(new FlowLayout(FlowLayout.LEFT));
        student_id.add(idLabel);
        student_id.add(id);

        // Student name div
        JPanel student_name = new JPanel();
        student_name.setLayout(new FlowLayout(FlowLayout.LEFT));
        student_name.add(nameLabel);
        student_name.add(name);

        // Buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        accept = new JButton("Accept");
        cancel = new JButton("Cancel");
        buttons.add(accept);
        buttons.add(cancel);

        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Layouts on window
        window.add(student_id, BorderLayout.NORTH);
        window.add(student_name, BorderLayout.CENTER);
        window.add(buttons, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Metodo para el evento cuando se acepta el envío de datos
     * 
     * @param acceptAction evento
     */
    public void isAccepted(ActionListener acceptAction) {
        accept.addActionListener(acceptAction);
    }

    /**
     * Metodo para conseguir los datos introducidos en el input text del id
     * 
     * @return datos introducidos en el input text del id
     */
    public String getId() {
        return id.getText();
    }

    /**
     * Metodo para conseguir los datos introducidos en el input text del nombre
     * 
     * @return datos introducidos en el input text del nombre
     */
    @Override
    public String getName() {
        return name.getText();
    }
}