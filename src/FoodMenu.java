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
}