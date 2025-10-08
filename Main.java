import java.util.Scanner;

class Calculator {

    public void performAddition(int a,int b) {
        int result = a+ b;
        System.out.println("Addition: " + result);
    }

    public void performAddition(double a , double b) {
        double result = a + b;
        System.out.println("Addition: " + result);
    }

    public void performAddition(int a,int b, int c) {
        int result = a+b+c;
        System.out.println("Addition: " + result);
    }

    public void performSubtraction(int a , int b) {
        int result = a-b;
        System.out.println("Substraction: " + result);
    }

    public void performMultiply(double a , double b){
        double result = a*b;
        System.out.println("Multiplication : " + result);
    }

    public void performDivision(int a, int b){
        if(b!=0){
            int result = a/b;
            System.out.println("Division : " + result);
        }
        else{
            System.out.println("invalid input");
        }
    }
    public void mainMenu(){
        System.out.println("------Welcome to the Calculator Application------");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Exit");
    }
}

class Main { 
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        Calculator obj2 = new Calculator();
        obj2.mainMenu();
        System.out.println("Enter your choice: ");
        int choice = obj.nextInt();

        if(choice == 1){
            System.out.println("which Addition you want to add : ");
            System.out.println("1. Integer Addition");
            System.out.println("2. Double Addition");
            System.out.println("Enter your choice: ");
            int addchoice = obj.nextInt();
            // to perform integer addition.
            if(addchoice == 1){
                System.out.println("Enter your 1st number:  ");
                int a = obj.nextInt();
                System.out.println("Enter your 2nd NUmber: ");
                int b = obj.nextInt();
                System.out.println("Do you want to add 3rd Number? (yes/no)");
                String ans = obj.next();
                if(ans.equals("yes")){
                    System.out.println("Enter your 3rd Number:");
                    int c = obj.nextInt();
                    obj2.performAddition(a,b,c);
                }
                else{
                    obj2.performAddition(a,b);
                }
            }
            // to perform double addition.
            if(addchoice == 2){
                System.out.println("Enter your 1st number:  ");
                double a = obj.nextDouble();
                System.out.println("Enter your 2nd NUmber: ");
                double b = obj.nextDouble();
                obj2.performAddition(a,b);
            }
        }
        
        if(choice ==2 ){
            System.out.println("Enter your 1st number:  ");
            int a = obj.nextInt();
            System.out.println("Enter your 2nd NUmber: ");
            int b = obj.nextInt();
            obj2.performSubtraction(a,b);
        }

        if(choice == 3){
            System.out.println("Enter your 1st number:  ");
            double a = obj.nextDouble();
            System.out.println("Enter your 2nd NUmber: ");
            double b = obj.nextDouble();
            obj2.performMultiply(a,b);
        }

        if(choice == 4){
            System.out.println("Enter your 1st number:  ");
            int a = obj.nextInt();
            System.out.println("Enter your 2nd NUmber: ");
            int b = obj.nextInt();
            obj2.performDivision(a,b);
        }
        if(choice == 5){
            System.out.println("Exiting the application. Goodbye!");
            System.exit(0);
        } 
        else {
            System.out.println("Invalid input");
        }
    }
}
