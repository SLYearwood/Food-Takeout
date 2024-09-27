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

    private boolean shouldSimulate() {
        String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program: ";

        IntUserInputRetriever<Boolean> retriever = new IntUserInputRetriever<Boolean>() {
            @Override
            public Boolean produceOutputOnIntUserInput(int selection) throws IllegalArgumentException {
                if (selection == 1) {
                    if(menu.getLowestCostFood() != null && customer.getMoney() > menu.getLowestCostFood().getPrice()){
                        return true;
                    } else {
                        System.out.println("You don't have enough money to continue shopping :( - ending simulation...");
                        return false;
                    }
                } else if (selection == 0) {
                    return false;
                } else {
                    throw new IllegalArgumentException("Selection must be 0 or 1");
                }
            }
        };
        return getOutputOnIntInput(userPrompt, retriever);
    }

    public boolean isStillOrderingFood() {
        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT: ";

        IntUserInputRetriever<Boolean> retriever = new IntUserInputRetriever<Boolean>() {
            @Override
            public Boolean produceOutputOnIntUserInput(int selection) throws IllegalArgumentException {
                if (selection == 1) {
                    return true;
                } else if (selection == 2) {
                    return false;
                } else {
                    throw new IllegalArgumentException("Selection must be 0 or 1");
                }
            }
        };
        return getOutputOnIntInput(userPrompt, retriever);
    }

    public void checkOutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("Processing payment...");
        int remainingMoney = customer.getMoney() - shoppingBag.getTotalPrice();
        customer.setMoney(remainingMoney);
        System.out.println("Your remaining money: $" + remainingMoney);
        System.out.println("Thank you and enjoy your food!");
        }
    }
}

