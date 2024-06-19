const fs = require('fs');

// Création ou sélection de la base de données
db = db.getSiblingDB('osca_db');

// Création du user
db.createUser({
    user: 'userOSCA',
    pwd: 'userOSCA',
    roles: [{ role: 'readWrite', db: 'osca_db' }],
});

// Création des collections
db.createCollection('projects');
db.createCollection('refRGAA');

//insère le json du RGAA à l'intérieur de la base
const jsonDataPath = '/data/refRGAA.json';
print('Lecture du fichier JSON depuis le chemin : ' + jsonDataPath);

const jsonData = JSON.parse(fs.readFileSync(jsonDataPath, 'utf-8'));
print('Données JSON lues avec succès');

print('Insertion des données dans la collection refRGAA');
db.refRGAA.insertMany(jsonData);
print('Script exécuté avec succès');
