# 📋 Cahier des Charges - API de Recettes de Cuisine

## 🎯 Objectif du Projet

Développer une API REST complète pour la gestion et la recherche de recettes de cuisine, utilisant une architecture moderne basée sur Spring Boot et une base de données NoSQL MongoDB.

## 📌 Fonctionnalités Requises

### 1. Gestion des Recettes

L'API doit permettre de stocker et gérer des recettes avec les informations suivantes :
- **Titre** : nom de la recette
- **Description** : explication détaillée de la recette
- **Liste d'ingrédients** : ensemble des composants nécessaires
- **Liste de tags** : catégories et caractéristiques de la recette

### 2. Système de Tags

Implémentation d'un système de catégorisation flexible permettant d'associer des tags aux recettes pour faciliter la recherche et l'organisation. Exemples de tags : "végétarien", "sans gluten", "rapide", "italien", "healthy".

### 3. Recherche Avancée

L'API doit offrir des capacités de recherche sophistiquées :
- **Recherche par ingrédients disponibles** : retourner toutes les recettes réalisables avec les ingrédients fournis
- **Recherche par tags** : filtrer les recettes selon leurs caractéristiques
- **Recherche par titre** : localiser une recette spécifique

## 🏗️ Architecture Technique

### Technologies Imposées

- **Langage** : Java (version 17 minimum)
- **Framework** : Spring Boot 3.x
- **Gestionnaire de dépendances** : Maven
- **Base de données** : MongoDB (NoSQL)
- **Format d'échange** : JSON via API REST

### Structure Organisationnelle

Le projet doit respecter une architecture en couches avec la séparation suivante :

```
src/main/java/com/example/recipes/
├── model/          → Entités et modèles de données
├── repository/     → Interfaces d'accès aux données
├── service/        → Logique métier et traitements
└── controller/     → Endpoints REST et gestion HTTP
```

## 🗂️ Modèle de Données

### Structure Document MongoDB

Collection `recipes` avec le schéma suivant :

```json
{
  "_id": "ObjectId",
  "title": "String (obligatoire, unique)",
  "description": "String (obligatoire)",
  "ingredients": ["Array of Strings (non vide)"],
  "tags": ["Array of Strings (optionnel)"]
}
```

### Contraintes de Données

- Le titre doit être unique dans la collection
- La liste d'ingrédients ne peut pas être vide
- La description est obligatoire
- Les tags sont optionnels mais recommandés

## 🔌 Spécifications API REST

### Endpoints Requis

| Méthode | Endpoint | Description | Paramètres |
|---------|----------|-------------|------------|
| `GET` | `/api/recipes` | Récupérer toutes les recettes | - |
| `POST` | `/api/recipes` | Créer une nouvelle recette | Body JSON |
| `GET` | `/api/recipes/{id}` | Récupérer une recette par ID | Path param |
| `PUT` | `/api/recipes/{id}` | Modifier une recette existante | Path param + Body |
| `DELETE` | `/api/recipes/{id}` | Supprimer une recette | Path param |
| `GET` | `/api/recipes/search` | Rechercher par ingrédients | Query param |
| `GET` | `/api/recipes/tags` | Rechercher par tags | Query param |

### Format des Réponses

Toutes les réponses doivent être au format JSON avec les codes de statut HTTP appropriés :
- `200 OK` : Opération réussie
- `201 Created` : Ressource créée
- `400 Bad Request` : Données invalides
- `404 Not Found` : Ressource inexistante
- `409 Conflict` : Conflit (titre déjà existant)

## 🛠️ Exigences Techniques

### Validation des Données

- Validation des champs obligatoires
- Vérification de l'unicité des titres
- Contrôle de la cohérence des données d'entrée

### Gestion des Erreurs

- Messages d'erreur explicites
- Codes de statut HTTP appropriés
- Logging des erreurs pour le débogage

### Performance et Scalabilité

- Indexation MongoDB sur les champs de recherche
- Requêtes optimisées pour les recherches fréquentes
- Architecture permettant la montée en charge

## 📦 Livrables Attendus

### Code Source

- Application Spring Boot complète et fonctionnelle
- Tests unitaires et d'intégration
- Configuration Docker pour le déploiement
- Documentation du code (Javadoc)

### Documentation

- README avec instructions d'installation et d'utilisation
- Exemples d'utilisation de l'API
- Documentation des endpoints (format .http)
- Schémas d'architecture

### Configuration

- Fichiers de configuration Maven (pom.xml)
- Configuration Spring Boot (application.properties)
- Scripts de déploiement Docker
- Fichier .gitignore approprié

## 🧪 Exemples d'Utilisation

### Création d'une Recette

```bash
POST /api/recipes
Content-Type: application/json

{
  "title": "Salade de quinoa",
  "description": "Une salade fraîche et healthy",
  "ingredients": ["quinoa", "tomates", "avocat", "citron"],
  "tags": ["végétarien", "sans gluten", "healthy"]
}
```

### Recherche par Ingrédients

```bash
GET /api/recipes/search?ingredients=tomates&ingredients=pâtes
```

### Recherche par Tags

```bash
GET /api/recipes/tags?tags=végétarien&tags=rapide
```

## 🎯 Critères de Validation

Le projet sera considéré comme réussi si :

- Toutes les fonctionnalités spécifiées sont implémentées et fonctionnelles
- L'architecture respecte les bonnes pratiques Spring Boot
- La base de données MongoDB est correctement intégrée
- Les tests couvrent les cas d'usage principaux
- La documentation est complète et claire
- Le déploiement est automatisé via Docker

## 📅 Contraintes

- Respect des standards REST
- Code lisible et maintenable
- Gestion appropriée des exceptions
- Sécurisation des données (validation, sanitisation)
- Performance acceptable pour un usage normal

---

*Ce cahier des charges définit les exigences pour le développement d'une API de recettes moderne, évolutive et conforme aux bonnes pratiques du développement Java/Spring Boot.*