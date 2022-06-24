package tp.practicas;

// Paquetes importados
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;

/**
 * Clase CollegeGUI
 * 
 * @author JuanSerberoCode
 */
public class CollegeGUI extends JFrame {

    // Atributos
    static private OfferedCourses offeredCourses = new OfferedCourses();
    static private EnrolledStudents enrolledStudents = new EnrolledStudents();;

    private JTextArea data;
    private JRadioButton orderName;
    private JRadioButton orderId;
    private JButton addStudent;
    private JButton enrollStudent;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem itemAddStudent, itemEnrollStudent, itemExit;

    /**
     * Constructor del menu del UI
     */
    private void constructMenu() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Menu");
        itemAddStudent = new JMenuItem("Add new student");
        itemEnrollStudent = new JMenuItem("Enroll student in course");
        itemExit = new JMenuItem("Exit");
        menu.add(itemAddStudent);
        menu.add(itemEnrollStudent);
        menu.add(itemExit);
        menuBar.add(menu);

        // Evento para el boton 'add student' del menu
        itemAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                AddStudentDialog window = new AddStudentDialog(CollegeGUI.this);
                window.isAccepted(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if ((window.getId() != null) && (!window.getName().equals(""))) {
                                enrolledStudents
                                        .addStudent(new Student(Integer.parseInt(window.getId()), window.getName()));
                            }
                        } catch (NumberFormatException nfe) {
                            // No es un numero
                        }
                        data();
                    }
                });
            }
        });

        // Evento para el boton 'enrolled student' del menu
        itemEnrollStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EnrollStudentDialog window = new EnrollStudentDialog(CollegeGUI.this, offeredCourses, enrolledStudents);
                window.isAccepted(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.getStudent(enrolledStudents).enrollCourse(window.getCourse(offeredCourses));
                        data();
                    }
                });
            }
        });

        // Evento para el boton 'exit' del menu
        itemExit.addActionListener((e) -> {
            System.exit(NORMAL);
        });
    }

    /**
     * Constructor de los botones de control del UI
     * 
     * @return
     */
    private Component constructControls() {
        orderName = new JRadioButton("Order by student's name", true);
        orderId = new JRadioButton("Order by student's id", false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(orderName);
        bg.add(orderId);
        addStudent = new JButton("Add new student");
        enrollStudent = new JButton("Enroll student in course");
        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
        JPanel order = new JPanel();
        order.setLayout(new BoxLayout(order, BoxLayout.PAGE_AXIS));
        order.add(orderName);
        order.add(orderId);
        order.setBorder(BorderFactory.createTitledBorder("List order"));
        east.add(order);
        east.add(addStudent);
        east.add(enrollStudent);

        // Evento para el boton 'add student' del menu
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                AddStudentDialog window = new AddStudentDialog(CollegeGUI.this);
                window.isAccepted(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if ((window.getId() != null) && (!window.getName().equals(""))) {
                                enrolledStudents
                                        .addStudent(new Student(Integer.parseInt(window.getId()), window.getName()));
                            }
                        } catch (NumberFormatException nfe) {
                            // No es un numero
                        }
                        data();
                    }
                });
            }
        });

        // Evento para el boton 'enrolled student' del menu
        enrollStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EnrollStudentDialog window = new EnrollStudentDialog(CollegeGUI.this, offeredCourses, enrolledStudents);
                window.isAccepted(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.getStudent(enrolledStudents).enrollCourse(window.getCourse(offeredCourses));
                        data();
                    }
                });
            }
        });

        // Ordenar por nombre con radioButton 'orderName'
        orderName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data();
            }
        });

        // Ordenar por id con radioButton 'orderId'
        orderId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data();
            }
        });

        return east;
    }

    /**
     * Constructor de la informacion del UI
     * 
     * @return
     */
    private Component constructInfo() {
        data = new JTextArea();
        data.setEditable(false);
        return new JScrollPane(data);
    }

    /**
     * Constructor
     */
    public CollegeGUI() {
        super("College Management");
        InitialData.init(offeredCourses, enrolledStudents);
        constructMenu();
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add(constructInfo(), BorderLayout.CENTER);
        pane.add(constructControls(), BorderLayout.EAST);
        setContentPane(pane);
        setLocation(50, 50);
        setSize(700, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Carga datos iniciales
        data();
    }

    /**
     * Metodo para cargar los datos
     */
    private void data() {
        String result = "";
        if (orderName.getSelectedObjects() != null) {
            for (Student s : enrolledStudents.getStudentsOrderByName()) {
                result += s + "\n";
            }
        } else {
            for (Student s : enrolledStudents.getStudentsOrderById()) {
                result += s + "\n";
            }
        }

        data.setText(result);
    }

    /**
     * Public main
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }
        new CollegeGUI();
    }
}