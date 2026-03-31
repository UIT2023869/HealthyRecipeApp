package healthyrecipeapp.recipe_module;

import java.util.*;

public class RecipeRepository {
    private List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe cannot be null");
        }
        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(recipe.getName())) {
                throw new IllegalArgumentException("Duplicate recipe not allowed");
            }
        }
        recipes.add(recipe);
    }

    public boolean removeRecipe(String name) {
        return recipes.removeIf(r -> r.getName().equalsIgnoreCase(name));
    }

    public Recipe getRecipeByName(String name) {
        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }

    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }

    public List<Recipe> searchByIngredient(String ingredient) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.containsIngredient(ingredient)) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Recipe> getVegetarianRecipes() {
        List<Recipe> result = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.isVegetarian()) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Recipe> getSortedByCalories() {
        List<Recipe> sorted = new ArrayList<>(recipes);
        sorted.sort(Comparator.comparingInt(Recipe::getCalories));
        return sorted;
    }
}