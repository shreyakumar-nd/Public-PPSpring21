// example of bad Java code which we will fix in the next lecture
import java.util.*;

public class Person{
    // properties
    boolean isStudent = true;
    String netid;
    String firstName;
    String lastName;
    Date joiningDate;
    double gpa;
    String classification; // freshman, grad

    // behavior
    double calculateFinancialAid(){
        if(isStudent){
            return 500000;
        }
        else return 0;
    }

    double calculateSalary(){
        if(!isStudent){
            return 100000;
        }
        else return 0;
    }


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
