package chizy.foodrecipeapp.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientViewModel {

    private int id;

    @NotNull
    @Min(3)
    private String name;

    @NotNull
    private Integer amount;

}
