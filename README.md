# 📘 Gestion des Absences – Application Java Swing

## 🎯 Objectif du projet
Ce projet a pour objectif de développer une application de gestion des absences des employés, utilisant **Java (Swing)** pour l’interface graphique, **MySQL** pour la base de données, et une architecture organisée en couches (UI, DAO, Services).

L'application permet de gérer :
- Les employés  
- Les types d’absence  
- Les absences avec dates et justificatifs  
- Les statistiques via un graphe (JFreeChart)

---

## 🧩 Fonctionnalités du projet

### ✔ Gestion des employés  
- Ajouter un employé  
- Modifier un employé  
- Supprimer un employé  
- Afficher la liste des employés  

### ✔ Gestion des types d'absence  
- Ajouter un type  
- Modifier un type  
- Supprimer un type  
- Liste des types  

### ✔ Gestion des absences  
- Ajouter une absence  
- Modifier une absence  
- Supprimer une absence  
- Affichage détaillé des absences  
- Liaison automatique employé ↔ type d’absence  

### ✔ Statistiques  
- Graphique (barres) du nombre d’absences par département (via **JFreeChart**)  

### ✔ Base de données MySQL  
Structure :
- **employe(id,nom,departement,poste)**
- **typeabsence(id,libelle,justification)**
- **absence(id,date_debut,date_fin,id_typeabsence,id_employe)**

---

## 🗂️ Modèle Conceptuel de Données (MCD)
 <img width="570" height="356" alt="Capture d’écran du 2025-12-02 23-27-58" src="https://github.com/user-attachments/assets/d7f0a46f-ae7a-41e9-851d-9cae79473b38" />
---

## 🏛️ Architecture du projet
```
AbsenceEntreprise/
├── Source Packages/
│   ├── UI/           # Interfaces graphiques Swing (Main, Principal, fenêtres)
│   ├── connexion/    # Classe de connexion à la base de données
│   ├── dao/          # Accès aux données : EmployeDao, AbsenceDao, TypeAbsenceDao
│   ├── entities/     # Classes du modèle : Employe, Absence, TypeAbsence
│   ├── images/       # Ressources graphiques (icônes, logos…)
│   └── services/     # Logique métier : EmployeService, AbsenceService, TypeAbsenceService
└── Libraries/        # Bibliothèques externes : JFreeChart, MySQL Connector, etc.
```

---
## 🎥 Vidéo de démonstration
