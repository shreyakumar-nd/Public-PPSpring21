// showing simple class Person which also contains main

class Person{
    int num = 50;
    String name;
    
    Person(String name){
        this.name = name;
    } //non default constructor

    int favNumber(){
        return num;
    }

} // end of class Person

public class PersonDemo{
    // this class PersonDemo can see and use class Person because it is in the same file

    public static void main(String[] args){
        Person p1 = new Person("Dwight");
        System.out.println(p1.name + "\'s favourite number is " + p1.favNumber());

    } // end of main method
} // end of class PersonDemo


