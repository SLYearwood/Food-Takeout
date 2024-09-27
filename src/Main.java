import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Enter your customer's name: ");
            String customerName = input.next();
            System.out.print("Enter " + customerName + "'s starting money: ");
            int money = input.nextInt();
            Customer customer = new Customer(customerName, money);
            FoodMenu menu = new FoodMenu();
            TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, menu,input);
            takeOutSimulator.startTakeOutSimulator();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number input");
        }

    }
}