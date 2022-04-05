package recipesearch;

import javafx.scene.control.ToggleGroup;
import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;
import se.chalmers.ait.dat215.lab2.SearchFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RecipeRetriever {
    private String cuisine;
    private String mainIngredient;
    private String difficulty;
    private Integer maxPrice;
    private Integer maxTime;

    /*public List<Recipe> query(RecipeDatabase db, int percentMatch){
        List<Recipe> allRecipes = query(db);
        return allRecipes.stream().takeWhile(x -> x.getMatch() >= percentMatch).toList();
    }*/
    public List<Recipe> query(RecipeDatabase db){
        return db.search(new SearchFilter(
                difficulty,
                Optional.ofNullable(maxTime).orElse(0),
                cuisine,
                Optional.ofNullable(maxPrice).orElse(0),
                mainIngredient)
        );
    }

    public RecipeRetriever() {
        cuisine = null;
        mainIngredient = null;
        difficulty = null;
        maxPrice = null;
        maxTime = null;
    }

    public RecipeRetriever setCuisine(String cuisine) {this.cuisine = cuisine; return this;}
    public RecipeRetriever setMaxTime(Integer maxTime) {this.maxTime = maxTime; return this;}
    public RecipeRetriever setMaxPrice(Integer maxPrice) {this.maxPrice = maxPrice; return this;}
    public RecipeRetriever setDifficulty(String difficulty) {this.difficulty = difficulty; return this;}
    public RecipeRetriever setMainIngredient(String mainIngredient) {this.mainIngredient = mainIngredient; return this;}
    /*public String getCuisine() {return this.cuisine;}
    public Integer getMaxTime() {return this.maxTime;}
    public Integer getMaxPrice() {return this.maxPrice;}
    public String getDifficulty() {return this.difficulty;}
    public String getMainIngredient() {return this.mainIngredient ;}
    */

    enum Cuisine {
        Sweden("Sverige"),
        Greece("Grekland"),
        India("Indien"),
        Asia("Asien"),
        Africa("Afrika"),
        France("Frankrike");

        private final String dbKey;
        private Cuisine(String key){
            this.dbKey = key;
        }
        public String key() {
            return this.dbKey;
        }
        public static List<String> getAllKeys(){
            return (Arrays.stream(Cuisine.class.getEnumConstants()).map(Cuisine::key)).toList();
        }
    }
    enum MainIngredient {
        Meat("Kött"),
        Fish("Fisk"),
        Chicken("Kyckling"),
        Vegetarian("Vegetarian");

        private final String dbKey;
        private MainIngredient(String key){
            this.dbKey = key;
        }
        public String key() {
            return this.dbKey;
        }
        public static List<String> getAllKeys(){
            return (Arrays.stream(MainIngredient.class.getEnumConstants()).map(MainIngredient::key)).toList();
        }
    }
    enum Difficulty {
        East("Lätt"),
        Medium("Mellan"),
        Hard("Svår");

        private final String dbKey;
        private Difficulty(String key){
            this.dbKey = key;
        }
        public String key() {
            return this.dbKey;
        }
        public static List<String>  getAllKeys(){
            return (Arrays.stream(Difficulty.class.getEnumConstants()).map(Difficulty::key)).toList();
        }
    }
}
