package com.example.recipes.service;

import com.example.recipes.model.Recipe;
import com.example.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service contenant la logique métier pour les recettes
 */
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * Récupère toutes les recettes
     * @return Liste de toutes les recettes
     */
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    /**
     * Récupère une recette par son ID
     * @param id ID de la recette
     * @return Recette correspondante si elle existe
     */
    public Optional<Recipe> getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    /**
     * Crée une nouvelle recette
     * @param recipe Recette à créer
     * @return Recette créée avec son ID généré
     */
    public Recipe createRecipe(Recipe recipe) {
        // Vérification si une recette avec le même titre existe déjà
        Optional<Recipe> existingRecipe = recipeRepository.findByTitleIgnoreCase(recipe.getTitle());
        if (existingRecipe.isPresent()) {
            throw new IllegalArgumentException("Une recette avec ce titre existe déjà");
        }
        return recipeRepository.save(recipe);
    }

    /**
     * Met à jour une recette existante
     * @param id ID de la recette à mettre à jour
     * @param recipe Nouvelles données de la recette
     * @return Recette mise à jour
     */
    public Optional<Recipe> updateRecipe(String id, Recipe recipe) {
        Optional<Recipe> existingRecipe = recipeRepository.findById(id);
        if (existingRecipe.isPresent()) {
            Recipe recipeToUpdate = existingRecipe.get();
            recipeToUpdate.setTitle(recipe.getTitle());
            recipeToUpdate.setDescription(recipe.getDescription());
            recipeToUpdate.setIngredients(recipe.getIngredients());
            recipeToUpdate.setTags(recipe.getTags());
            return Optional.of(recipeRepository.save(recipeToUpdate));
        }
        return Optional.empty();
    }

    /**
     * Supprime une recette par son ID
     * @param id ID de la recette à supprimer
     * @return true si la recette a été supprimée, false sinon
     */
    public boolean deleteRecipe(String id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Recherche des recettes contenant au moins un des ingrédients spécifiés
     * @param ingredients Liste des ingrédients disponibles
     * @return Liste des recettes correspondantes
     */
    public List<Recipe> searchRecipesByIngredients(List<String> ingredients) {
        return recipeRepository.findByIngredientsIn(ingredients);
    }

    /**
     * Recherche des recettes contenant tous les ingrédients spécifiés
     * @param ingredients Liste des ingrédients requis
     * @return Liste des recettes correspondantes
     */
    public List<Recipe> searchRecipesByAllIngredients(List<String> ingredients) {
        return recipeRepository.findByIngredientsContainingAll(ingredients);
    }

    /**
     * Recherche des recettes par tags
     * @param tags Liste des tags
     * @return Liste des recettes correspondantes
     */
    public List<Recipe> searchRecipesByTags(List<String> tags) {
        return recipeRepository.findByTagsIn(tags);
    }

    /**
     * Recherche des recettes par titre
     * @param title Titre ou partie du titre
     * @return Liste des recettes correspondantes
     */
    public List<Recipe> searchRecipesByTitle(String title) {
        return recipeRepository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Recherche avancée dans le titre et la description
     * @param keyword Mot-clé à rechercher
     * @return Liste des recettes correspondantes
     */
    public List<Recipe> searchRecipesByKeyword(String keyword) {
        return recipeRepository.findByTitleOrDescriptionContaining(keyword);
    }

    /**
     * Recherche des recettes contenant un ingrédient spécifique
     * @param ingredient Ingrédient à rechercher
     * @return Liste des recettes correspondantes
     */
    public List<Recipe> searchRecipesByIngredient(String ingredient) {
        return recipeRepository.findByIngredientsContaining(ingredient);
    }

    /**
     * Recherche des recettes par tag spécifique
     * @param tag Tag à rechercher
     * @return Liste des recettes correspondantes
     */
    public List<Recipe> searchRecipesByTag(String tag) {
        return recipeRepository.findByTagsContaining(tag);
    }
}