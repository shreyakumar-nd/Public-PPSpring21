// Better example using inheritance
import java.util.*;

public class Person{
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



    public static void main(String[] args){
        Person p = new Person();

        System.out.println("Salary: " + p.calculateSalary());
        System.out.println("Fin Aid: " + p.calculateFinancialAid());
        System.out.println("Food: " + p.findFood());
    
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
