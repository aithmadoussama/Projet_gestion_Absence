# Gestion des Absences d'une entreprise – Application Java Swing
---

## Objectif du projet

Ce projet a pour objectif de développer une application de gestion des absences des employés, utilisant **Java (Swing)** pour l’interface graphique, **MySQL** pour la base de données, et une architecture organisée en couches (UI, DAO, Services).

L'application permet de gérer :
- Les employés  
- Les types d’absence  
- Les absences avec dates et justificatifs  
- Les statistiques via des graphes (JFreeChart)

---

## Fonctionnalités du projet

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
- Graphique (barres) du nombre d’absences par mois (via **JFreeChart**)
- Graphique (disque) du nombre d'employes par département (via **JFreeChart**)
  
### ✔ Base de données MySQL  
```
CREATE TABLE employe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    departement VARCHAR(100),
    poste VARCHAR(100)
);

CREATE TABLE typeabsence (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    justification TEXT
);

CREATE TABLE absence (
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    id_employe INT NOT NULL,
    id_typeabsence INT NOT NULL,
    PRIMARY KEY (id_employe, id_typeabsence, date_debut),
    FOREIGN KEY (id_employe) REFERENCES employe(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_typeabsence) REFERENCES typeabsence(id) ON DELETE CASCADE ON UPDATE CASCADE
);
```
---

## Modèle Conceptuel de Données (MCD)
<img width="619" height="282" alt="Capture d’écran du 2025-12-05 23-10-22" src="https://github.com/user-attachments/assets/8d32a7b1-9838-4e1b-8459-2317fb32b6ab" />


---

## Architecture du projet
<img width="851" height="261" alt="Diagramme sans nom drawio" src="https://github.com/user-attachments/assets/51e57bcf-17de-4468-ae04-c4d465058759" />

---

## Vidéo de démonstration
https://github.com/user-attachments/assets/b8077805-d9e4-4f28-b434-082049eb929d

--- 
*Rédigé par Ait Hmad Oussama*
