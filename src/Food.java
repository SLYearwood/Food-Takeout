public class Food implements PricedItem<Integer> {

    private final String name;
    private final String description;
    private final int price;

    public Food (String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Enjoy " + this.name + ": " + this.description + "   Cost: $" + this.price;
    }

    public String getName() {
        return this.name;
    }
}