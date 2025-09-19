# 🍳 API de Recettes de Cuisine

Une API REST développée avec **Spring Boot 3** et **MongoDB** pour gérer des recettes de cuisine avec des fonctionnalités de recherche avancées.

## 📋 Fonctionnalités

- ✅ **CRUD complet** des recettes
- 🔍 **Recherche par ingrédients** disponibles
- 🏷️ **Recherche par tags** (catégories alimentaires)
- 📝 **Recherche par titre** et mots-clés
- ✅ **Validation** des données d'entrée
- 🌐 **API REST** complète avec endpoints documentés

## 🚀 Démarrage rapide

### Prérequis

- **Java 17+**
- **Maven 3.6+**
- **MongoDB 4.4+** (installé et en cours d'exécution)

### Installation

1. **Clonez le projet**
```bash
git clone <votre-repo>
cd cooking-recipes-api
```

2. **Installez les dépendances**
```bash
mvn clean install
```

3. **Démarrez MongoDB**
```bash
# Sur Windows
net start MongoDB

# Sur macOS/Linux
sudo systemctl start mongod
```

4. **Lancez l'application**
```bash
mvn spring-boot:run
```

L'API sera accessible sur `http://localhost:8080`

## 📚 Endpoints de l'API

### 📖 Recettes - CRUD

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/api/recipes` | Récupère toutes les recettes |
| `GET` | `/api/recipes/{id}` | Récupère une recette par ID |
| `POST` | `/api/recipes` | Crée une nouvelle recette |
| `PUT` | `/api/recipes/{id}` | Met à jour une recette |
| `DELETE` | `/api/recipes/{id}` | Supprime une recette |

### 🔍 Recherche

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/api/recipes/search?ingredients=tomate,pâtes` | Recherche par ingrédients (au moins un) |
| `GET` | `/api/recipes/search/all?ingredients=tomate,pâtes` | Recherche par ingrédients (tous requis) |
| `GET` | `/api/recipes/tags?tags=végétarien,rapide` | Recherche par tags |
| `GET` | `/api/recipes/search/title?title=pâtes` | Recherche par titre |
| `GET` | `/api/recipes/search/keyword?keyword=rapide` | Recherche par mot-clé |
| `GET` | `/api/recipes/search/ingredient?ingredient=tomate` | Recherche par un ingrédient |
| `GET` | `/api/recipes/search/tag?tag=végétarien` | Recherche par un tag |

## 🧪 Exemples d'utilisation

### 1. Créer une recette

```bash
curl -X POST http://localhost:8080/api/recipes \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Pâtes à la tomate",
    "description": "Un plat simple et rapide pour tous les jours",
    "ingredients": ["pâtes", "tomates", "huile d'\''olive", "basilic", "parmesan"],
    "tags": ["végétarien", "rapide", "italien"]
  }'
```

### 2. Créer une autre recette

```bash
curl -X POST http://localhost:8080/api/recipes \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Salade de quinoa",
    "description": "Une salade fraîche et healthy",
    "ingredients": ["quinoa", "tomates", "avocat", "citron", "huile d'\''olive"],
    "tags": ["végétarien", "sans gluten", "healthy"]
  }'
```

### 3. Rechercher par ingrédients disponibles

```bash
# Si vous avez des tomates et de l'huile d'olive
curl "http://localhost:8080/api/recipes/search?ingredients=tomates&ingredients=huile%20d'olive"
```

### 4. Rechercher par tags

```bash
# Recettes végétariennes
curl "http://localhost:8080/api/recipes/tags?tags=végétarien"

# Recettes rapides ET végétariennes
curl "http://localhost:8080/api/recipes/tags?tags=végétarien&tags=rapide"
```

### 5. Rechercher par titre

```bash
curl "http://localhost:8080/api/recipes/search/title?title=pâtes"
```

### 6. Récupérer toutes les recettes

```bash
curl http://localhost:8080/api/recipes
```

## 📦 Structure du projet

```
src/
├── main/
│   ├── java/com/example/recipes/
│   │   ├── CookingRecipesApiApplication.java    # Classe principale
│   │   ├── controller/
│   │   │   └── RecipeController.java            # Contrôleur REST
│   │   ├── model/
│   │   │   └── Recipe.java                      # Modèle de données
│   │   ├── repository/
│   │   │   └── RecipeRepository.java            # Interface MongoDB
│   │   └── service/
│   │       └── RecipeService.java               # Logique métier
│   └── resources/
│       └── application.properties               # Configuration
├── test/                                        # Tests (à implémenter)
└── pom.xml                                      # Configuration Maven
```

## 🗄️ Modèle de données MongoDB

### Collection `recipes`

```json
{
  "_id": "64fbc1234abcde56789",
  "title": "Pâtes à la tomate",
  "description": "Un plat simple et rapide",
  "ingredients": ["pâtes", "tomates", "huile d'olive", "basilic"],
  "tags": ["végétarien", "rapide"]
}
```

### Validation

- `title` : **obligatoire**, max 200 caractères
- `description` : optionnelle, max 1000 caractères  
- `ingredients` : **obligatoire**, liste non vide
- `tags` : optionnelle, liste de chaînes

## ⚙️ Configuration

### MongoDB

Par défaut, l'application se connecte à :
- **Host** : `localhost`
- **Port** : `27017`
- **Base de données** : `cooking_recipes`

Pour modifier la configuration, éditez `src/main/resources/application.properties` :

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=cooking_recipes
```

### Port de l'application

Par défaut, l'API écoute sur le port `8080`. Pour le changer :

```properties
server.port=9090
```

## 🧑‍💻 Développement

### Compilation et tests

```bash
# Compilation
mvn compile

# Tests
mvn test

# Package
mvn package

# Lancement en mode développement
mvn spring-boot:run
```

### Ajout de fonctionnalités

Le projet est structuré pour faciliter l'ajout de nouvelles fonctionnalités :

1. **Nouvelles entités** → `model/`
2. **Nouveaux repositories** → `repository/`
3. **Nouvelle logique métier** → `service/`
4. **Nouveaux endpoints** → `controller/`

## 🚀 Améliorations futures

- [ ] Tests unitaires et d'intégration
- [ ] Documentation Swagger/OpenAPI
- [ ] Authentification JWT
- [ ] Pagination des résultats
- [ ] Cache Redis
- [ ] Déploiement Docker
- [ ] Upload d'images pour les recettes
- [ ] Notation et commentaires
- [ ] Favoris utilisateur

## 🤝 Contribution

1. Fork le projet
2. Créez votre branche feature (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📝 Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.