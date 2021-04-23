/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Controller
 * Student(s) Name(s): Brandon Young, Stuart Griffin, Timothy Trusov
 */

 import java.sql.*;

 public class Controller {

     private EntityManager em;
     private Session session;
     private String connstr= "jdbc:mysql://localhost:3306/enrollments";
     private String connuser= "root";
     private String connpwd= ""; //use password

     public Controller() {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
         em = emf.createEntityManager();
         session = em.unwrap(Session.class);
     }

     // TODO: return a Student entity from the given id (or null if the entity does not exist)
     public Student getStudent(int id) {
         try{
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn= DriverManager.getConnection(connstr,connuser,connpwd);
             Statement stmt= conn.createStatement();
             ResultSet rs= stmt.executeQuery("Select id,name From students where id="+id);
             Student mystudent=new Student();
             while(rs.next()){
                mystudent.setID(rs.getInt(1));
                mystudent.setName(rs.getString(2));
             }
             return mystudent;
         }
         catch (Exception e){
             e.printStackTrace();
             return null;
         }
     }

     // TODO: add the given student entity, returning true/false depending whether the operation was successful or not
     public boolean addStudent(final Student student) {
         boolean retval = true;
         if (
                 student == null
         ) return false;
         try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn= DriverManager.getConnection(connstr,connuser,connpwd);
             Statement stmt= conn.createStatement();
             ResultSet rs= stmt.executeQuery("Select Max(id) From students");
             int currmaxid= 0;
             while(rs.next())
                 currmaxid= rs.getInt();
             PreparedStatement ps= conn.prepareStatement("Insert into students(id, name) values (?,?)");
             ps.setInt(1,currmaxid+1);
             ps.setString(2,student.getName());
             int numrows= ps.executeUpdate();
             if(numrows==0)
                 retval= false;
             conn.close();
             return retval;
         }
         catch(Exception e){
             e.printStackTrace();
             return false;
         }

         // this is used to check if student successfully was added

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
         return null;
     }
 }
