/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredientEntity;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CesarF
 */
public class RecipeDetailDTO extends RecipeDTO {
    
    private List<IngredientDTO> ingredients;
    
    public RecipeDetailDTO(){
    
    }
    public RecipeDetailDTO(RecipeEntity recipeEntity) {
        super(recipeEntity);
        if (recipeEntity != null) {
            if (recipeEntity.getIngredients() != null) {
                ingredients = new ArrayList<>();
                for (IngredientEntity entityIngredient : recipeEntity.getIngredients()) {
                    ingredients.add(new IngredientDTO(entityIngredient));
                }
            }
        }
    }
    
}
