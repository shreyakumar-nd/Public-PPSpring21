class Dog{
    String name;
    Dog(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }
    void setName(String name){
        this.name = name;
    }
} // end of class Dog

public class DogDemo{

    public static void main(String[] args) {
        Dog aDog = new Dog("Bagheera");
        Dog oldDog = aDog;

        // we pass the object to
        fnOverwrite(aDog);
        // aDog variable is still pointing to the "Bagheera" dog when fn(...) returns
        System.out.println("\nAfter overwriting function:");
        System.out.println("Is name Bagheera? " + aDog.getName().equals("Bagheera")); // true
        System.out.println("Is name Fido? " + aDog.getName().equals("Fido")); // false
        System.out.println("Is aDog == oldDog? " + (aDog == oldDog)); // true

        fnModify(aDog);
        // aDog variable is still pointing to the "Bagheera" dog when fn(...) returns
        System.out.println("\nAfter modifying function:");
        System.out.println("Is name Bagheera? " + aDog.getName().equals("Bagheera")); // true
        System.out.println("Is name Fido? " + aDog.getName().equals("Fido")); // false
        System.out.println("Is aDog == oldDog? " + (aDog == oldDog)); // true

        Dog[] dList = new Dog[3];
        dList[0] = new Dog("Rover");
        //printNames(dList);
        modifyArray(dList);
        System.out.println("In main after modifyArray");
        printNames(dList);
    } // end of main

    public static void fnOverwrite(Dog d) {
        System.out.println("\nInside fn overwrite?");
        System.out.println("Is name Bagheera? " + d.getName().equals("Bagheera")); // true
        // change d inside of fn() to point to a new Dog instance "Fido"
        d = new Dog("Fido");
        System.out.println("Overwrote variable for dog with new object");
        System.out.println("Is name Fido? " + d.getName().equals("Fido")); // true
    }

    public static void fnModify(Dog d) {
        System.out.println("\nInside modifying function:");

        System.out.println("Is name Bagheera? " + d.getName().equals("Bagheera")); // true
        // this changes the name of d to be "Fido"
        d.setName("Fido");
        System.out.println("Is name Fido? " + d.getName().equals("Fido")); // true
    }

    public static void printNames(Dog[] dlist){
        for(Dog d : dlist)
            System.out.println(d.getName());
    }

    public static void modifyArray(Dog[] dlist){
        dlist[1] = new Dog("Buddy");
        dlist[2] = new Dog("Chloe");
    }
}

