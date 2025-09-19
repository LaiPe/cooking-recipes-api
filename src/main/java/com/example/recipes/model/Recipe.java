package com.example.recipes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * Classe représentant une recette de cuisine
 */
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 200, message = "Le titre ne peut pas dépasser 200 caractères")
    private String title;

    @Size(max = 1000, message = "La description ne peut pas dépasser 1000 caractères")
    private String description;

    @NotEmpty(message = "La liste des ingrédients ne peut pas être vide")
    private List<String> ingredients;

    private List<String> tags;

    // Constructeur par défaut
    public Recipe() {}

    // Constructeur avec paramètres
    public Recipe(String title, String description, List<String> ingredients, List<String> tags) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.tags = tags;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // Méthodes equals, hashCode et toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && 
               Objects.equals(title, recipe.title) && 
               Objects.equals(description, recipe.description) && 
               Objects.equals(ingredients, recipe.ingredients) && 
               Objects.equals(tags, recipe.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, ingredients, tags);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                ", tags=" + tags +
                '}';
    }
}