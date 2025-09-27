package com.example.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Classe représentant une recette de cuisine
 * Utilise Lombok pour générer automatiquement :
 * - getters/setters (@Data)
 * - constructeurs (@NoArgsConstructor, @AllArgsConstructor)
 * - equals, hashCode, toString (@Data)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}