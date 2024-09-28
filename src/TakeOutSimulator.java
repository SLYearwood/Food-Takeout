import java.util.Scanner;

public class TakeOutSimulator {

    private final Customer customer;
    private final FoodMenu menu;
    private final Scanner input;

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

        IntUserInputRetriever<Boolean> retriever = selection -> {
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
        };
        return getOutputOnIntInput(userPrompt, retriever);
    }

    public Food getMenuSelection() {
        String userPrompt = "Choose a menu item!: ";

        System.out.println("Today's Menu Options!\n");
        System.out.println(menu.toString());


        IntUserInputRetriever<Food> retriever = (int selection) -> {
            Food selectedFood = menu.getFood(selection - 1);
            if (selectedFood == null) {
                throw new IllegalArgumentException("Invalid selection: no such item.");
            }
            return selectedFood;
        };


        return getOutputOnIntInput(userPrompt, retriever);
    }


    public boolean isStillOrderingFood() {
        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT: ";

        IntUserInputRetriever<Boolean> retriever = selection -> {
            if (selection == 1) {
                return true;
            } else if (selection == 0) {
                return false;
            } else {
                throw new IllegalArgumentException("Selection must be 0 or 1");
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

    public void takeOutPrompt() {
        int customerMoneyLeft = customer.getMoney();
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();

        do {
            System.out.println("You have $" + customerMoneyLeft + " left to spend\n");
            Food selection = getMenuSelection();
            if (selection.getPrice() <= customerMoneyLeft) {
                customerMoneyLeft -= selection.getPrice();
                shoppingBag.addItem(selection);
                System.out.println(selection.getName() + " added to your bag. Choose another item or checkout.");
            } else {
                System.out.println("Oops! Looks like you don't have enough for that. Choose another item or checkout.");
            }
        }   while (isStillOrderingFood());

        checkOutCustomer(shoppingBag);
    }

    public void startTakeOutSimulator() {
        System.out.println("Hello, welcome to my restaurant!\n");
        System.out.println("Welcome " + customer.getName() + "!");

        if (menu.isEmpty()) {
            System.out.println("Sorry, the menu is empty. Please come back later!");
            return;
        }
        do takeOutPrompt();
        while (shouldSimulate());
        System.out.println("Simulation ended. Thank you for visiting!");
    }
}


