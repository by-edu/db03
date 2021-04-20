/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Enrollment
 * Student(s) Name(s): Brandon Young, Stuart Griffin, Timothy Trusov
 */
import javax.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    private String code;

    @Id
    private int id;

    public String getCode(){return code;}

    public void setCode(String code){this.code = code;}

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

   @Override
    public String toString() {return code + "-" + id;}

    // @OneToMany for the primary key thing??




}
