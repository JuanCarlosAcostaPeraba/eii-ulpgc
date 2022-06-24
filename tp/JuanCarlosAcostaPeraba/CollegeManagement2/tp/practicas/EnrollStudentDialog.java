package tp.practicas;

//Paquetes importados
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager.*;

/**
 * Clase EnrollStudentDialog
 * 
 * @author JuanSerberoCode
 */
class EnrollStudentDialog extends JDialog {

    // Atributos
    private JComboBox<String> students;
    private JComboBox<String> courses;

    private JButton accept;
    private JButton cancel;

    /**
     * Constructor
     * 
     * @param ventana          desde la que se ejecuta el evento
     * @param offeredCourses   objeto de la clase OfferedCourses
     * @param enrolledStudents objeto de la clase EnrolledStudents
     */
    public EnrollStudentDialog(JFrame ventana, OfferedCourses offeredCourses, EnrolledStudents enrolledStudents) {
        // Combobox
        students = new JComboBox<String>();

        for (Student s : enrolledStudents.getStudentsOrderById()) {
            students.addItem(s.getId() + "-" + s.getName());
        }

        courses = new JComboBox<String>();

        for (Course c : offeredCourses.getCourses()) {
            courses.addItem(c.toString());
        }

        // Labels
        JLabel studentsLabel = new JLabel("Student: ");
        JLabel coursesLabel = new JLabel("Course: ");

        // Title
        setTitle("Enroll student in course");

        // Window
        Container window = getContentPane();
        window.setLayout(new BorderLayout());

        // Students div
        JPanel student = new JPanel();
        student.setLayout(new FlowLayout(FlowLayout.LEFT));
        student.add(studentsLabel);
        student.add(students);

        // Courses div
        JPanel course = new JPanel();
        course.setLayout(new FlowLayout(FlowLayout.LEFT));
        course.add(coursesLabel);
        course.add(courses);

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
        window.add(student, BorderLayout.WEST);
        window.add(course, BorderLayout.EAST);
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
     * Metodo para conseguir los datos introducidos en el combobox del estudiante
     * 
     * @return datos introducidos en el input combobox del estudiante
     */
    public Student getStudent(EnrolledStudents enrolledStudents) {
        return enrolledStudents.getStudentsOrderById().get(students.getSelectedIndex());
    }

    /**
     * Metodo para conseguir los datos introducidos en el combobox del curso
     * 
     * @return datos introducidos en el input combobox del curso
     */
    public Course getCourse(OfferedCourses offeredCourses) {
        return offeredCourses.getCourses().get(courses.getSelectedIndex());
    }
}
