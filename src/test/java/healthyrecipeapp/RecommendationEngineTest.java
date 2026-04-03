package healthyrecipeapp;

import healthyrecipeapp.recipe_module.Recipe;
import healthyrecipeapp.recommendation_module.RecommendationEngine;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecommendationEngineTest {

    RecommendationEngine engine = new RecommendationEngine();
    List<Recipe> recipes;

    @BeforeEach
    void setUp() {
        recipes = List.of(
            new Recipe("Salad",   List.of("lettuce"), 150, 5),
            new Recipe("Oats",    List.of("oats"),    250, 8),
            new Recipe("Chicken", List.of("chicken"), 500, 40)
        );
    }

    @Test
    void testLowCal() {
        List<Recipe> result = engine.recommendLowCal(recipes, 300);
        assertEquals(2, result.size());
    }

    @Test
    void testInvalidMaxCaloriesThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            engine.recommendLowCal(recipes, 0));
    }

    @Test
    void testHighProtein() {
        List<Recipe> result = engine.recommendHighProtein(recipes, 10);
        assertEquals(1, result.size());
    }

    @Test
    void testBestBalanced() {
        Recipe best = engine.getBestBalancedRecipe(recipes);
        assertEquals("Chicken", best.getName());
    }
}