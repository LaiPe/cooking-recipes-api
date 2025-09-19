// Script d'initialisation MongoDB avec des données de test

db = db.getSiblingDB('cooking_recipes');

// Insertion de recettes de test
db.recipes.insertMany([
  {
    "title": "Pâtes à la tomate",
    "description": "Un plat simple et rapide pour tous les jours",
    "ingredients": ["pâtes", "tomates", "huile d'olive", "basilic", "parmesan"],
    "tags": ["végétarien", "rapide", "italien"]
  },
  {
    "title": "Salade de quinoa",
    "description": "Une salade fraîche et healthy",
    "ingredients": ["quinoa", "tomates", "avocat", "citron", "huile d'olive"],
    "tags": ["végétarien", "sans gluten", "healthy"]
  },
  {
    "title": "Risotto aux champignons",
    "description": "Un risotto crémeux aux champignons de saison",
    "ingredients": ["riz arborio", "champignons", "bouillon de légumes", "parmesan", "vin blanc"],
    "tags": ["végétarien", "italien", "champignons"]
  },
  {
    "title": "Soupe de lentilles",
    "description": "Une soupe nourrissante et réconfortante",
    "ingredients": ["lentilles", "carottes", "oignons", "céleri", "bouillon de légumes"],
    "tags": ["végétarien", "healthy", "hiver"]
  },
  {
    "title": "Curry de légumes",
    "description": "Un curry épicé avec des légumes de saison",
    "ingredients": ["courgettes", "aubergines", "tomates", "lait de coco", "épices curry"],
    "tags": ["végétarien", "épicé", "indien"]
  }
]);

print('Base de données cooking_recipes initialisée avec succès avec ' + db.recipes.countDocuments() + ' recettes.');

// Création d'index pour améliorer les performances de recherche
db.recipes.createIndex({ "ingredients": 1 });
db.recipes.createIndex({ "tags": 1 });
db.recipes.createIndex({ "title": "text", "description": "text" });

print('Index créés avec succès.');