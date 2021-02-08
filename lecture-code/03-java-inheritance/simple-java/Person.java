// showing simple class Person which also contains main

public class Person{
    int num = 50;

    int favNumber(){
        return num;
    }

    public static void main(String[] args){
        Person p1 = new Person();
        System.out.println(p1.favNumber());

    } // end of main method
} // end of class


