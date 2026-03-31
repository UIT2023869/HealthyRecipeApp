package healthyrecipeapp;

import healthyrecipeapp.recipe_module.*;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    @Test
    void testValidRecipe() {
        Recipe r = new Recipe("Salad", List.of("lettuce", "tomato"), 150, 5);
        assertEquals("Salad", r.getName());
        assertEquals(150, r.getCalories());
        assertEquals(5, r.getProtein());
    }

    @Test
    void testEmptyNameThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Recipe("", List.of("rice"), 200, 10));
    }

    @Test
    void testNullNameThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Recipe(null, List.of("rice"), 200, 10));
    }

    @Test
    void testNegativeCaloriesThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Recipe("Rice", List.of("rice"), -100, 5));
    }

    @Test
    void testZeroCaloriesThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Recipe("Rice", List.of("rice"), 0, 5));
    }

    @Test
    void testNegativeProteinThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Recipe("Rice", List.of("rice"), 200, -5));
    }

    @Test
    void testIsVegetarian() {
        Recipe veg = new Recipe("Salad", List.of("lettuce", "tomato"), 100, 3);
        assertTrue(veg.isVegetarian());
    }

    @Test
    void testIsNotVegetarian() {
        Recipe nonVeg = new Recipe("Chicken Bowl", List.of("chicken", "rice"), 300, 25);
        assertFalse(nonVeg.isVegetarian());
    }

    @Test
    void testContainsIngredient() {
        Recipe r = new Recipe("Salad", List.of("lettuce", "tomato"), 150, 5);
        assertTrue(r.containsIngredient("lettuce"));
        assertFalse(r.containsIngredient("chicken"));
    }

    @Test
    void testProteinToCalorieRatio() {
        Recipe r = new Recipe("Egg", List.of("egg"), 200, 20);
        assertEquals(0.1, r.getProteinToCalorieRatio(), 0.001);
    }

    @Test
    void testAddAndGetFromRepo() {
        RecipeRepository repo = new RecipeRepository();
        Recipe r = new Recipe("Salad", List.of("lettuce"), 150, 5);
        repo.addRecipe(r);
        assertNotNull(repo.getRecipeByName("Salad"));
    }

    @Test
    void testDuplicateRecipeThrows() {
        RecipeRepository repo = new RecipeRepository();
        repo.addRecipe(new Recipe("Salad", List.of("lettuce"), 150, 5));
        assertThrows(IllegalArgumentException.class, () ->
            repo.addRecipe(new Recipe("Salad", List.of("tomato"), 200, 8)));
    }

    @Test
    void testRemoveRecipe() {
        RecipeRepository repo = new RecipeRepository();
        repo.addRecipe(new Recipe("Salad", List.of("lettuce"), 150, 5));
        assertTrue(repo.removeRecipe("Salad"));
        assertNull(repo.getRecipeByName("Salad"));
    }

    @Test
    void testSearchByIngredient() {
        RecipeRepository repo = new RecipeRepository();
        repo.addRecipe(new Recipe("Salad", List.of("lettuce"), 150, 5));
        repo.addRecipe(new Recipe("Chicken Bowl", List.of("chicken", "rice"), 400, 30));
        List<Recipe> result = repo.searchByIngredient("chicken");
        assertEquals(1, result.size());
    }

    @Test
    void testGetVegetarianRecipes() {
        RecipeRepository repo = new RecipeRepository();
        repo.addRecipe(new Recipe("Salad", List.of("lettuce"), 150, 5));
        repo.addRecipe(new Recipe("Chicken Bowl", List.of("chicken", "rice"), 400, 30));
        List<Recipe> vegs = repo.getVegetarianRecipes();
        assertEquals(1, vegs.size());
    }
}