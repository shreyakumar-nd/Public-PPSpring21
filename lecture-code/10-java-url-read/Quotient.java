import java.util.Scanner;

// this program demonstrates how to manually throw an exception and how to catch and process exceptions.

public class Quotient{
    public static int calculateQuotient(int num1, int num2){
        if (num2 == 0)
            throw new ArithmeticException("new arithmetic exception occured");
        return num1/num2;
    } // end of method

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 2 integers for division: ");
        int number1 = input.nextInt();
        int number2 = input.nextInt();

        try {
            int result = calculateQuotient(number1, number2);
            System.out.println("Result = " + result);
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Exception occured because you did something bad arithmetically.");
        }
        catch(Exception e){
            System.out.println("Exception that occured was not an Arithmetic exception.");
            e.printStackTrace();
        }
        finally{ // if try is started, this block will be executed. like close the file handle.
            System.out.println("Your input was received and processed.");
        }

        System.out.println("Execution continues after try block ... ");

    }   // end of main
}

