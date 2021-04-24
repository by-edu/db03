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
                 currmaxid= rs.getInt(currmaxid);
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

         return true;

     }

     // TODO: return a list of all Course entities
     public List<Course> getCourses() {
         List<Course> courses = session.createQuery("FROM Course").getResultList();
        return courses;
     }

     // TODO: enroll a student to a course based on the given parameters, returning true/false depending whether the operation was successful or not
     public boolean enrollStudent(String code, int id) {
         try{
           session.beginTransaction();
           Enrollment enroll = new Enrollment();
           
           enroll.setCode(code);
           enroll.setId(id);
           session.save(enroll);
           session.getTransaction().commit();
           session.evict(enroll);
           session.clear();
         } catch (Exception e) {
            
           e.printStackTrace();
           return false;
         }
        return true;
     }

     // TODO: drop a student from a course based on the given parameters, returning true/false depending whether the operation was successful or not
     public boolean dropStudent(String code, int id) {
         
       try {
         Transaction transaction = session.beginTransaction();
         String stmt = ("DELETE FROM Enrollment WHERE code= :code AND id= :id");
         Query query = session.createQuery(stmt);
         query.setParameter("code", code);
         query.setParameter("id", id);
         query.executeUpdate();
         transaction.commit();
       } catch (Exception e){
         e.printStackTrace();
         return false;
       }
      return true;
     }

     // TODO: return a list of all Student entities enrolled in the given course (hint: use the stored procedure 'list_students')
     public List<Student> getStudentsEnrolled(String course) {
         return null;
     }
 }
