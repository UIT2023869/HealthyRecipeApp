package healthyrecipeapp.recipe_module;

import java.util.*;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private int calories;
    private int protein;

    public Recipe(String name, List<String> ingredients, int calories, int protein) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be empty");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Ingredients cannot be empty");
        }
        if (calories <= 0) {
            throw new IllegalArgumentException("Calories must be greater than 0");
        }
        if (protein < 0) {
            throw new IllegalArgumentException("Protein cannot be negative");
        }
        this.name = name.trim();
        this.ingredients = new ArrayList<>();
        for (String ing : ingredients) {
            if (!ing.trim().isEmpty()) {
                this.ingredients.add(ing.trim().toLowerCase());
            }
        }
        this.calories = calories;
        this.protein = protein;
    }

    public String getName() { return name; }
    public List<String> getIngredients() { return new ArrayList<>(ingredients); }
    public int getCalories() { return calories; }
    public int getProtein() { return protein; }

    public boolean isVegetarian() {
        for (String ing : ingredients) {
            if (ing.contains("chicken") || ing.contains("meat") || ing.contains("fish")) {
                return false;
            }
        }
        return true;
    }

    public boolean containsIngredient(String keyword) {
        for (String ing : ingredients) {
            if (ing.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public double getProteinToCalorieRatio() {
        return (double) protein / calories;
    }

    @Override
    public String toString() {
        return "\n--- Recipe ---" +
            "\nName: " + name +
            "\nIngredients: " + ingredients +
            "\nCalories: " + calories +
            "\nProtein: " + protein;
    }
}