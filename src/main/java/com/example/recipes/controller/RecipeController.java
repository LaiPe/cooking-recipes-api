package com.example.recipes.controller;

import com.example.recipes.model.Recipe;
import com.example.recipes.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour les opérations sur les recettes
 */
@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "*") // Permet les requêtes cross-origin pour le développement
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Récupère toutes les recettes
     * @return Liste de toutes les recettes
     */
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    /**
     * Récupère une recette par son ID
     * @param id ID de la recette
     * @return Recette correspondante
     */
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable String id) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        return recipe.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée une nouvelle recette
     * @param recipe Recette à créer
     * @return Recette créée avec son ID généré
     */
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
        try {
            Recipe createdRecipe = recipeService.createRecipe(recipe);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Met à jour une recette existante
     * @param id ID de la recette à mettre à jour
     * @param recipe Nouvelles données de la recette
     * @return Recette mise à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String id, @Valid @RequestBody Recipe recipe) {
        Optional<Recipe> updatedRecipe = recipeService.updateRecipe(id, recipe);
        return updatedRecipe.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime une recette par son ID
     * @param id ID de la recette à supprimer
     * @return Statut de la suppression
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String id) {
        boolean deleted = recipeService.deleteRecipe(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Recherche des recettes contenant au moins un des ingrédients spécifiés
     * @param ingredients Liste des ingrédients disponibles (séparés par des virgules)
     * @return Liste des recettes correspondantes
     */
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipesByIngredients(@RequestParam List<String> ingredients) {
        List<Recipe> recipes = recipeService.searchRecipesByIngredients(ingredients);
        return ResponseEntity.ok(recipes);
    }

    /**
     * Recherche des recettes contenant tous les ingrédients spécifiés
     * @param ingredients Liste des ingrédients requis
     * @return Liste des recettes correspondantes
     */
    @GetMapping("/search/all")
    public ResponseEntity<List<Recipe>> searchRecipesByAllIngredients(@RequestParam List<String> ingredients) {
        List<Recipe> recipes = recipeService.searchRecipesByAllIngredients(ingredients);
        return ResponseEntity.ok(recipes);
    }

    /**
     * Recherche des recettes par tags
     * @param tags Liste des tags
     * @return Liste des recettes correspondantes
     */
    @GetMapping("/tags")
    public ResponseEntity<List<Recipe>> searchRecipesByTags(@RequestParam List<String> tags) {
        List<Recipe> recipes = recipeService.searchRecipesByTags(tags);
        return ResponseEntity.ok(recipes);
    }

    /**
     * Recherche des recettes par titre
     * @param title Titre ou partie du titre
     * @return Liste des recettes correspondantes
     */
    @GetMapping("/search/title")
    public ResponseEntity<List<Recipe>> searchRecipesByTitle(@RequestParam String title) {
        List<Recipe> recipes = recipeService.searchRecipesByTitle(title);
        return ResponseEntity.ok(recipes);
    }

    /**
     * Recherche avancée dans le titre et la description
     * @param keyword Mot-clé à rechercher
     * @return Liste des recettes correspondantes
     */
    @GetMapping("/search/keyword")
    public ResponseEntity<List<Recipe>> searchRecipesByKeyword(@RequestParam String keyword) {
        List<Recipe> recipes = recipeService.searchRecipesByKeyword(keyword);
        return ResponseEntity.ok(recipes);
    }

    /**
     * Recherche des recettes contenant un ingrédient spécifique
     * @param ingredient Ingrédient à rechercher
     * @return Liste des recettes correspondantes
     */
    @GetMapping("/search/ingredient")
    public ResponseEntity<List<Recipe>> searchRecipesByIngredient(@RequestParam String ingredient) {
        List<Recipe> recipes = recipeService.searchRecipesByIngredient(ingredient);
        return ResponseEntity.ok(recipes);
    }

    /**
     * Recherche des recettes par tag spécifique
     * @param tag Tag à rechercher
     * @return Liste des recettes correspondantes
     */
    @GetMapping("/search/tag")
    public ResponseEntity<List<Recipe>> searchRecipesByTag(@RequestParam String tag) {
        List<Recipe> recipes = recipeService.searchRecipesByTag(tag);
        return ResponseEntity.ok(recipes);
    }
}