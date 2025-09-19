package com.example.recipes.repository;

import com.example.recipes.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface repository pour les opérations MongoDB sur les recettes
 */
@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    /**
     * Recherche des recettes qui contiennent au moins un des ingrédients spécifiés
     * @param ingredients Liste des ingrédients à rechercher
     * @return Liste des recettes correspondantes
     */
    List<Recipe> findByIngredientsIn(List<String> ingredients);

    /**
     * Recherche des recettes qui contiennent tous les ingrédients spécifiés
     * @param ingredients Liste des ingrédients requis
     * @return Liste des recettes correspondantes
     */
    @Query("{'ingredients': {'$all': ?0}}")
    List<Recipe> findByIngredientsContainingAll(List<String> ingredients);

    /**
     * Recherche des recettes par tags
     * @param tags Liste des tags à rechercher
     * @return Liste des recettes correspondantes
     */
    List<Recipe> findByTagsIn(List<String> tags);

    /**
     * Recherche des recettes par titre (insensible à la casse)
     * @param title Titre ou partie du titre à rechercher
     * @return Liste des recettes correspondantes
     */
    List<Recipe> findByTitleContainingIgnoreCase(String title);

    /**
     * Recherche des recettes par titre exact (insensible à la casse)
     * @param title Titre exact à rechercher
     * @return Recette correspondante si elle existe
     */
    Optional<Recipe> findByTitleIgnoreCase(String title);

    /**
     * Recherche des recettes contenant un ingrédient spécifique
     * @param ingredient Ingrédient à rechercher
     * @return Liste des recettes correspondantes
     */
    List<Recipe> findByIngredientsContaining(String ingredient);

    /**
     * Recherche des recettes par tag spécifique
     * @param tag Tag à rechercher
     * @return Liste des recettes correspondantes
     */
    List<Recipe> findByTagsContaining(String tag);

    /**
     * Recherche avancée : recettes contenant des mots-clés dans le titre ou la description
     * @param keyword Mot-clé à rechercher
     * @return Liste des recettes correspondantes
     */
    @Query("{'$or': [{'title': {'$regex': ?0, '$options': 'i'}}, {'description': {'$regex': ?0, '$options': 'i'}}]}")
    List<Recipe> findByTitleOrDescriptionContaining(String keyword);
}