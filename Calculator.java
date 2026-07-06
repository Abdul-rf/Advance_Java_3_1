import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Calculator obj = new Calculator();
        obj.sender();
    }
}

class Calculator {

    int state = 0;
    Scanner sc = new Scanner(System.in);
    
    void sender() {
        System.out.println("Sender : Hi");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        receiver();
    }

    void receiver() {

        char op;
        int a, b;


        if (state == 0) {

            System.out.println("Receiver : Hello");
            System.out.println("Which operation would you like to perform? (+, -, *, /)");

            op = sc.next().charAt(0);

            System.out.print("Enter two numbers: ");
            a = sc.nextInt();
            b = sc.nextInt();

            operation(op, a, b);

        } else {
            System.out.println("Receiver : Bye");
        }
    }

    void operation(char op, int a, int b) {
        char ch;

        switch (op) {

            case '+':
                System.out.println("Result = " + (a + b));
                break;

            case '-':
                System.out.println("Result = " + (a - b));
                break;

            case '*':
                System.out.println("Result = " + (a * b));
                break;

            case '/':
                if (b != 0)
                    System.out.println("Result = " + (a / b));
                else
                    System.out.println("Division by zero is not allowed.");
                break;

            default:
                System.out.println("Invalid choice");
        }

         System.out.println("Would you like to continue(y/n):");
         ch = sc.next().charAt(0);
         
         if(ch == 'y'){
             receiver();
         }
         else{
             state = 1;
             receiver();
         }
    }
}