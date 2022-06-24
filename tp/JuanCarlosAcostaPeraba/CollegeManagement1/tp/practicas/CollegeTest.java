package tp.practicas;

//Paquetes importados
import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase CollegeTest
 * 
 * @author JuanSerberoCode
 */
public class CollegeTest {

	// --------------------------------------------------
	// CLASE COURSE
	// --------------------------------------------------
	@Test
	public void getCodeCourseClass() {
		Course course = new Course(0, "courseName");
		assertEquals("El metodo getCode esta mal",
				0, course.getCode());
	}

	@Test
	public void getNameCourseClass() {
		Course course = new Course(0, "courseName");
		assertEquals("El metodo getName esta mal",
				"courseName", course.getName());
	}

	@Test
	public void toStringCourseClass() {
		Course course = new Course(0, "courseName");
		assertEquals("El metodo toString esta mal",
				"(0)courseName", course.toString());
	}

	// --------------------------------------------------
	// CLASE STUDENT
	// --------------------------------------------------
	@Test
	public void getIdStudentClass() {
		Student student = new Student(0, "studentName");
		assertEquals("El metodo getId esta mal",
				0, student.getId());
	}

	@Test
	public void getNameStudentClass() {
		Student student = new Student(0, "studentName");
		assertEquals("El metodo getName esta mal",
				"studentName", student.getName());
	}

	@Test
	public void enrollCourseTrueStudentClass() {
		Student student = new Student(0, "studentName");
		Course course = new Course(0, "courseName");
		assertEquals("El metodo enrollCourse esta mal",
				true, student.enrollCourse(course));
	}

	@Test
	public void enrollCourseFalseStudentClass() {
		Student student = new Student(0, "studentName");
		Course course = new Course(0, "courseName");
		student.enrollCourse(course);
		assertEquals("El metodo enrollCourse esta mal",
				false, student.enrollCourse(course));
	}

	@Test
	public void unenrollCourseFalseStudentClass() {
		Student student = new Student(0, "studentName");
		assertEquals("El metodo unenrollCourse esta mal",
				false, student.unenrollCourse(0));
	}

	@Test
	public void unenrollCourseTrueStudentClass() {
		Student student = new Student(0, "studentName");
		Course course = new Course(0, "courseName");
		student.enrollCourse(course);
		assertEquals("El metodo unenrollCourse esta mal",
				true, student.unenrollCourse(0));
	}

	@Test
	public void getEnrolledCoursesStudentClass() {
		Student student = new Student(0, "studentName");
		Course course0 = new Course(0, "courseName0");
		Course course1 = new Course(1, "courseName1");
		student.enrollCourse(course1);
		student.enrollCourse(course0);
		List<Course> tempCoursesList = new LinkedList<Course>();
		tempCoursesList.add(course0);
		tempCoursesList.add(course1);
		assertEquals("El metodo getEnrolledCourses esta mal",
				tempCoursesList, student.getEnrolledCourses());
	}

	@Test
	public void toStringVacioStudentClass() {
		Student student = new Student(0, "studentName");
		assertEquals("El metodo toString esta mal, debe estar vacio",
				"0-studentName[]", student.toString());
	}

	@Test
	public void toStringLlenoStudentClass() {
		Student student = new Student(0, "studentName");
		Course course0 = new Course(0, "courseName0");
		Course course1 = new Course(1, "courseName1");
		student.enrollCourse(course1);
		student.enrollCourse(course0);
		assertEquals("El metodo toString esta mal, debe estar lleno",
				"0-studentName[(0)courseName0, (1)courseName1]", student.toString());
	}

	// --------------------------------------------------
	// CLASE ENROLLEDSTUDENTS
	// --------------------------------------------------
	@Test
	public void addStudentTrueEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		Student student = new Student(0, "studentName");
		assertEquals("El metodo addStudent esta mal",
				true, enrolledStudents.addStudent(student));
	}

	@Test
	public void addStudentFalseEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		Student student = new Student(0, "studentName");
		enrolledStudents.addStudent(student);
		assertEquals("El metodo addStudent esta mal",
				false, enrolledStudents.addStudent(student));
	}

	@Test
	public void removeStudentTrueEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		Student student = new Student(0, "studentName");
		enrolledStudents.addStudent(student);
		assertEquals("El metodo removeStudent esta mal",
				true, enrolledStudents.removeStudent(0));
	}

	@Test
	public void removeStudentFalseEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		assertEquals("El metodo removeStudent esta mal",
				false, enrolledStudents.removeStudent(0));
	}

	@Test
	public void getStudentNullEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		assertEquals("El metodo getStudent esta mal",
				null, enrolledStudents.getStudent(0));
	}

	@Test
	public void getStudentStudentEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		Student student = new Student(0, "studentName");
		enrolledStudents.addStudent(student);
		assertEquals("El metodo getStudent esta mal",
				student, enrolledStudents.getStudent(0));
	}

	@Test
	public void getStudentByCourseEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		Student student0 = new Student(0, "studentName0");
		Student student1 = new Student(1, "studentName1");
		Course course0 = new Course(0, "courseName0");
		Course course1 = new Course(1, "courseName1");
		student1.enrollCourse(course1);
		student0.enrollCourse(course0);
		enrolledStudents.addStudent(student1);
		enrolledStudents.addStudent(student0);
		List<Student> tempStudentList = new LinkedList<Student>();
		tempStudentList.add(student0);
		assertEquals("El metodo getStudentByCourse esta mal",
				tempStudentList, enrolledStudents.getStudentsByCourse(0));
	}

	@Test
	public void getStudentsOrderByNameEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		Student student0 = new Student(0, "aStudentName0");
		Student student1 = new Student(1, "studentName1");
		enrolledStudents.addStudent(student1);
		enrolledStudents.addStudent(student0);
		List<Student> tempStudentsList = new LinkedList<Student>();
		tempStudentsList.add(student0);
		tempStudentsList.add(student1);
		assertEquals("El metodo getStudentsOrderByName esta mal",
				tempStudentsList, enrolledStudents.getStudentsOrderByName());
	}

	@Test
	public void getStudentsOrderByIdEnrolledStudentsClass() {
		EnrolledStudents enrolledStudents = new EnrolledStudents();
		Student student0 = new Student(0, "studentName0");
		Student student1 = new Student(1, "studentName1");
		enrolledStudents.addStudent(student1);
		enrolledStudents.addStudent(student0);
		List<Student> tempStudentsList = new LinkedList<Student>();
		tempStudentsList.add(student0);
		tempStudentsList.add(student1);
		assertEquals("El metodo getStudentsOrderById esta mal",
				tempStudentsList, enrolledStudents.getStudentsOrderById());
	}

	// --------------------------------------------------
	// CLASE OFFEREDCOURSES
	// --------------------------------------------------
	@Test
	public void addCourseTrueOfferedCoursesClass() {
		OfferedCourses offeredCourses = new OfferedCourses();
		Course course = new Course(0, "courseName");
		assertEquals("El metodo addCourse esta mal",
				true, offeredCourses.addCourse(course));
	}

	@Test
	public void addCourseFalseOfferedCoursesClass() {
		OfferedCourses offeredCourses = new OfferedCourses();
		Course course = new Course(0, "courseName");
		offeredCourses.addCourse(course);
		assertNotEquals("El metodo addCourse esta mal",
				true, offeredCourses.addCourse(course));
	}

	@Test
	public void removeCourseTrueOfferedCoursesClass() {
		OfferedCourses offeredCourses = new OfferedCourses();
		Course course = new Course(0, "courseName");
		offeredCourses.addCourse(course);
		assertEquals("El metodo removeCourse esta mal",
				true, offeredCourses.removeCourse(0));
	}

	@Test
	public void removeCourseFalseOfferedCoursesClass() {
		OfferedCourses offeredCourses = new OfferedCourses();
		assertNotEquals("El metodo removeCourse esta mal",
				true, offeredCourses.removeCourse(0));
	}

	@Test
	public void getCourseNullOfferedCoursesClass() {
		OfferedCourses offeredCourses = new OfferedCourses();
		assertEquals("El metodo getCourse esta mal",
				null, offeredCourses.getCourse(0));
	}

	@Test
	public void getCourseCourseOfferedCoursesClass() {
		OfferedCourses offeredCourses = new OfferedCourses();
		Course course = new Course(0, "courseName");
		offeredCourses.addCourse(course);
		assertEquals("El metodo getCourse esta mal",
				course, offeredCourses.getCourse(0));
	}

	@Test
	public void getCoursesOfferedCoursesClass() {
		OfferedCourses offeredCourses = new OfferedCourses();
		Course course0 = new Course(0, "acourseName0");
		Course course1 = new Course(1, "courseName1");
		offeredCourses.addCourse(course1);
		offeredCourses.addCourse(course0);
		List<Course> tempCoursesList = new LinkedList<Course>();
		tempCoursesList.add(course0);
		tempCoursesList.add(course1);
		assertEquals("El metodo getCourses esta mal",
				tempCoursesList, offeredCourses.getCourses());
	}

}
