import java.util.ArrayList;
import java.util.List;

public class FoodMenu {

    private List<Food> menu;

    public FoodMenu() {
        menu = new ArrayList<>();
        menu.add(new Food("Tacos", "Yummy steak tacos", 15));
        menu.add(new Food("Dumplings", "Delicious steamed dumplings", 10));
        menu.add(new Food("Ramen", "Hot pork ramen", 12));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < menu.size(); i++) {
            Food food = menu.get(i);
            sb.append((i + 1) + ". " + food.toString()).append("\n");
        }
        return sb.toString();
    }
    public Food getFood(int index) {
        try {
            return menu.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Food getLowestCostFood() {
        if (menu.isEmpty()) { return null; }

        Food lowest = menu.getFirst();
        for (Food food: menu) {
            if (food.getPrice() < lowest.getPrice())  { lowest = food; }
        }
        return lowest;
    }

    public boolean isEmpty() {
        return menu.isEmpty();
    }
}