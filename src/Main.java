import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Customer customer = null;
        FoodMenu menu = new FoodMenu();


        System.out.print("Enter your customer's name: ");
        String customerName = input.next();

        while (customer == null) {
            try {
                System.out.print("Enter " + customerName + "'s starting money: ");
                int money = input.nextInt();

                if (money < 0) {
                    System.out.println("Starting money cannot be negative. Please enter a valid amount.");
                } else {
                    customer = new Customer(customerName, money);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid number input. Please enter a valid integer.");
                input.next(); // Clear the invalid input
            }
        }

        TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, menu, input);
        takeOutSimulator.startTakeOutSimulator();

        input.close();
    }
}
