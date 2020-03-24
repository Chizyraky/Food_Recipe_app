package chizy.foodrecipeapp.view;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeViewModel {

    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String image;

}
