package diet_module;
import recipe_module.Recipe;
import java.util.*;

public class DietPlanner {

    public List<Recipe> generatePlan(List<Recipe> recipes, int targetCalories) {
        if (targetCalories <= 0)
            throw new IllegalArgumentException("Target calories must be positive");

        List<Recipe> sorted = new ArrayList<>(recipes);
        sorted.sort(Comparator.comparingInt(Recipe::getCalories));

        List<Recipe> plan = new ArrayList<>();
        int total = 0;
        for (Recipe r : sorted) {
            if (total + r.getCalories() <= targetCalories) {
                plan.add(r);
                total += r.getCalories();
            }
        }
        return plan;
    }

    public int getTotalCalories(List<Recipe> plan) {
        int total = 0;
        for (Recipe r : plan)
            total += r.getCalories();
        return total;
    }

    public boolean isPlanValid(List<Recipe> plan, int targetCalories) {
        return getTotalCalories(plan) <= targetCalories;
    }
}