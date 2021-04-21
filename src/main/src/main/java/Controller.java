/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Controller
 * Student(s) Name(s): Brandon Young, Stuart Griffin, Timothy Trusov
 */

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.List;

public class Controller {

    private EntityManager em;
    private Session session;

    public Controller() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        em = emf.createEntityManager();
        session = em.unwrap(Session.class);
    }

    // TODO: return a Student entity from the given id (or null if the entity does not exist)
    public Student getStudent(int id) {


        String stdntString;
        Student student = new Student();

        if(student.getId() != 0){

            stdntString = student.toString();

            System.out.println(stdntString + " This is the 'toString' method.");

            return student;

        }

        System.out.println("Student is empty.");
        return null;

    }

    // TODO: add the given student entity, returning true/false depending whether the operation was successful or not
    public boolean addStudent(final Student student) {

        boolean flag;
        int id = 0;
        String stdntString;
        student.setId(id);
        System.out.println(id);
            // Just to check
        stdntString = student.toString();


        

        id = student.getId();

        //student.add();


        // Below should be for when it's added or not
        if (getStudent().getId()){
            // this is used to check if student successfully was added
            flag = true;
            return flag;
        } else{
            flag = false;
        }

        return flag;
    }

    // TODO: return a list of all Course entities
    public List<Course> getCourses() {





        return null;
    }

    // TODO: enroll a student to a course based on the given parameters, returning true/false depending whether the operation was successful or not
    public boolean enrollStudent(String code, int id) {
        return false;
    }

    // TODO: drop a student from a course based on the given parameters, returning true/false depending whether the operation was successful or not
    public boolean dropStudent(String code, int id) {
        return false;
    }

    // TODO: return a list of all Student entities enrolled in the given course (hint: use the stored procedure 'list_students')
    public List<Student> getStudentsEnrolled(String course) {

        SessionImpl sessionImpl = (SessionImpl) session;
        Connection conn = sessionImpl.connection();


        return null;
    }
}
