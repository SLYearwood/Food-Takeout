import java.util.Scanner;

public class TakeOutSimulator {

    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator (Customer customer, FoodMenu menu, Scanner input) {
        this.customer = customer;
        this.menu = menu;
        this.input = input;
    }

    private <T> T getOutputOnIntInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever) {

        while (true) {
            System.out.println(userInputPrompt);
            try {
                if (input.hasNextInt()) {
                    return intUserInputRetriever.produceOutputOnIntUserInput(input.nextInt());
                } else {
                    System.out.println("Input needs to be an 'int' type.");
                    input.next();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(input + " is not a valid input. Try Again!");
            }
        }
    }
}

