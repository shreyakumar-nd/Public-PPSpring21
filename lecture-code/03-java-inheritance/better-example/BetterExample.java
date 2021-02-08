// Better example using inheritance
import java.util.*;

class Person{
    // properties
    String netid;
    String firstName;
    String lastName;
    Date joiningDate;
    String classification;

    // behavior

    String findFood(){
        return "go to Duncan Center";
    }



} // end of person

class Student extends Person{ // Student is a child class of Person(parent)
    double gpa;    

    double calculateFinancialAid(){
        return 500000;
    }

} // end of Student class

class Faculty extends Person{

    double calculateSalary(){
       return 100000;
    }

} // end of Faculty class

public class BetterExample{


    public static void main(String[] args){
        Student s = new Student();
        Faculty f = new Faculty();

        Person ps = new Student(); // allowed
        
        Person[] p_arr = new Person[3];
        p_arr[0] = new Student();

        Faculty fp = new Faculty();
        p_arr[1] = fp;

        System.out.println("Salary: " + f.calculateSalary());
        System.out.println("Fin Aid: " + s.calculateFinancialAid());
        System.out.println("Food: " + s.findFood());
    
    }

}
