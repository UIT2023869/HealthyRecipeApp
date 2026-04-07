package diet_module;
import recipe_module.Recipe;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DietPlannerTest {

    DietPlanner planner;
    List<Recipe> recipes;

    @BeforeEach
    void setUp() {
        planner = new DietPlanner();
        recipes = new ArrayList<>();
        recipes.add(new Recipe("Salad", Arrays.asList("lettuce", "tomato"), 150, 5));
        recipes.add(new Recipe("Oats", Arrays.asList("oats", "milk"), 250, 10));
        recipes.add(new Recipe("Pasta", Arrays.asList("pasta", "cheese"), 400, 15));
    }

    @Test
    void testPlanWithinLimit() {
        List<Recipe> plan = planner.generatePlan(recipes, 400);
        assertTrue(planner.getTotalCalories(plan) <= 400);
    }

    @Test
    void testTotalCalories() {
        List<Recipe> plan = Arrays.asList(
            new Recipe("Salad", Arrays.asList("lettuce"), 150, 5),
            new Recipe("Oats", Arrays.asList("oats"), 250, 10)
        );
        assertEquals(400, planner.getTotalCalories(plan));
    }

    @Test
    void testPlanIsValid() {
        List<Recipe> plan = planner.generatePlan(recipes, 600);
        assertTrue(planner.isPlanValid(plan, 600));
    }

    @Test
    void testInvalidCaloriesThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            planner.generatePlan(recipes, -100));
    }

    @Test
    void testEmptyListGivesEmptyPlan() {
        List<Recipe> plan = planner.generatePlan(new ArrayList<>(), 500);
        assertEquals(0, plan.size());
    }
}