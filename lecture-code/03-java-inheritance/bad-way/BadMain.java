// example of bad java code, which we will fix
import java.util.*;

class Student{
    // properties
    String netid;
    String firstName;
    String lastName;
    Date joiningDate;
    double gpa;
    String classification; // freshman, grad

    // behavior
    double calculateFinancialAid(){
        return 500000;
    }

    String findFood(){
        return "go to Duncan Center";
    }

}
//----------------------
class Faculty{
    // properties
    String netid;
    String firstName;
    String lastName;
    Date joiningDate;
    double gpa;
    String classification; //visiting, permanent

    // behavior
    double calculateSalary(){
        return 1000000;
    }

    String findFood(){
        return "go to Duncan Center";
    }
}

//-------------------------
public class BadMain{

    public static void main(String[] args){
        Student s = new Student();
        Faculty f = new Faculty();

        System.out.println("Salary: " + f.calculateSalary());
        System.out.println("Fin Aid: " + s.calculateFinancialAid());
        System.out.println("Food: " + s.findFood());
    
    }
}
