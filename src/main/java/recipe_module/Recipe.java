package recipe_module;
import java.util.*;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private int calories;
    private int protein;

    public Recipe(String name, List<String> ingredients, int calories, int protein) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Recipe name cannot be empty");
        if (ingredients == null || ingredients.isEmpty())
            throw new IllegalArgumentException("Ingredients cannot be empty");
        if (calories <= 0)
            throw new IllegalArgumentException("Calories must be greater than 0");
        if (protein < 0)
            throw new IllegalArgumentException("Protein cannot be negative");
        this.name = name.trim();
        this.calories = calories;
        this.protein = protein;
        this.ingredients = new ArrayList<>();
        for (String ing : ingredients) {
            if (!ing.trim().isEmpty())
                this.ingredients.add(ing.trim().toLowerCase());
        }
    }

    public String getName() { return name; }
    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public List<String> getIngredients() { return new ArrayList<>(ingredients); }
}