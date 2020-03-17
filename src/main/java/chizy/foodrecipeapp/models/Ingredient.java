package chizy.foodrecipeapp.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private Integer amount;

    @ManyToMany(mappedBy="ingredients", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Recipe> recipes;

    protected Ingredient(){
    }

    public Ingredient(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public Ingredient(int id, String name, Integer amount) {
        this(name, amount);
        this.id = id;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

