package healthyrecipeapp.recommendation_module;

import healthyrecipeapp.recipe_module.Recipe;
import java.util.*;

public class RecommendationEngine {

    public List<Recipe> recommendLowCal(List<Recipe> recipes, int maxCalories) {
        if (maxCalories <= 0) {
            throw new IllegalArgumentException("Max calories must be greater than 0");
        }
        List<Recipe> result = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.getCalories() <= maxCalories) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Recipe> recommendHighProtein(List<Recipe> recipes, int minProtein) {
        if (minProtein <= 0) {
            throw new IllegalArgumentException("Min protein must be greater than 0");
        }
        List<Recipe> result = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.getProtein() >= minProtein) {
                result.add(r);
            }
        }
        return result;
    }

    public Recipe getBestBalancedRecipe(List<Recipe> recipes) {
        if (recipes == null || recipes.isEmpty()) {
            throw new IllegalArgumentException("Recipe list cannot be empty");
        }
        Recipe best = null;
        double bestRatio = -1;
        for (Recipe r : recipes) {
            double ratio = r.getProteinToCalorieRatio();
            if (ratio > bestRatio) {
                bestRatio = ratio;
                best = r;
            }
        }
        return best;
    }
}