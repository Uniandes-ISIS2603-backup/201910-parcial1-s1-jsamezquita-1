/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.ejb;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author CesarF
 */
@Stateless
public class RecipeLogic {
    @Inject
    private RecipePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public RecipeEntity getRecipe(Long id) {
        return persistence.find(id);
    }

    //TODO crear el método createRecipe
    public RecipeEntity createRecipe(RecipeEntity recipeEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de una receta");
        if (recipeEntity.getName().length()>30||recipeEntity.getName()==null||recipeEntity.getName().isEmpty()){
            throw new BusinessLogicException("El nombre de la receta inválido");
        }
        if (persistence.findByName(recipeEntity.getName()) != null) {
            throw new BusinessLogicException("El nombre ya existe");
        }
        if (recipeEntity.getDescription().length()>150||recipeEntity.getDescription()==null||recipeEntity.getDescription().isEmpty()) {
            throw new BusinessLogicException("La descripcion es inválida");
        }
        if(recipeEntity.getIngredients().isEmpty()){
            throw new BusinessLogicException("La receta debe tener al menos un ingrediente");
        }
        persistence.create(recipeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del libro");
        return recipeEntity;
    }
}
