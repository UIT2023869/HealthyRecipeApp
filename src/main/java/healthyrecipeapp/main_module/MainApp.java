package healthyrecipeapp.main_module;

import healthyrecipeapp.recipe_module.*;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RecipeRepository repo = new RecipeRepository();

        while (true) {
            try {
                System.out.println("\n=== Healthy Recipe System ===");
                System.out.println("1. Add Recipe");
                System.out.println("2. View All Recipes");
                System.out.println("3. Search by Ingredient");
                System.out.println("4. View Vegetarian Recipes");
                System.out.println("5. View Sorted by Calories");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Ingredients (comma separated): ");
                        List<String> ing = Arrays.asList(sc.nextLine().split(","));
                        System.out.print("Calories: ");
                        int cal = Integer.parseInt(sc.nextLine());
                        System.out.print("Protein: ");
                        int protein = Integer.parseInt(sc.nextLine());
                        repo.addRecipe(new Recipe(name, ing, cal, protein));
                        System.out.println("Recipe added successfully!");
                        break;

                    case 2:
                        List<Recipe> all = repo.getAllRecipes();
                        if (all.isEmpty()) {
                            System.out.println("No recipes found!");
                        } else {
                            all.forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.print("Enter ingredient: ");
                        String keyword = sc.nextLine();
                        List<Recipe> found = repo.searchByIngredient(keyword);
                        if (found.isEmpty()) {
                            System.out.println("No recipes found!");
                        } else {
                            found.forEach(System.out::println);
                        }
                        break;

                    case 4:
                        repo.getVegetarianRecipes().forEach(System.out::println);
                        break;

                    case 5:
                        repo.getSortedByCalories().forEach(System.out::println);
                        break;

                    case 6:
                        System.out.println("Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }
}