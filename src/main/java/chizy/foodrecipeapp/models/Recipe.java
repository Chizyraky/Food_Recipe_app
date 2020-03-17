package chizy.foodrecipeapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="recipe_ingredients",
            joinColumns=@JoinColumn(name="recipe_id"),
            inverseJoinColumns=@JoinColumn(name="ingredient_id"))
    private List<Ingredient> ingredients;

    protected Recipe(){
    }
    public Recipe(String name, String description, String image){
        this();
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Recipe(int id, String name, String description, String image) {
        this(name, description, image);
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
