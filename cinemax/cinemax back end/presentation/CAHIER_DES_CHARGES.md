# 📄 Cahier des Charges & Spécifications Techniques - Projet CinéMax

## 1. Présentation du Projet
**Nom du projet :** CinéMax
**Description :** CinéMax est une application web complète de gestion de cinéma permettant aux utilisateurs de consulter les films à l'affiche, de réserver des places et d'interagir avec la communauté. Elle dispose également d'une interface d'administration robuste pour la gestion du contenu et des utilisateurs.

---

## 2. Cahier des Charges Fonctionnel

### 2.1. Objectifs
- Offrir une interface moderne et fluide pour la réservation de billets de cinéma.
- Simplifier la gestion du catalogue de films et des réservations pour les administrateurs.
- Créer de l'engagement via un système de commentaires et de notes.

### 2.2. Acteurs du Système
1.  **Visiteur (Non connecté) :** Peut consulter la liste des films, voir les détails, rechercher un film.
2.  **Utilisateur (Client connecté) :** Peut réserver des places, payer (simulation), laisser des commentaires, gérer son profil, contacter le support.
3.  **Administrateur :** Possède tous les droits de gestion sur la plateforme.

### 2.3. Fonctionnalités Détaillées

#### A. Partie Publique (Front-Office)
*   **Catalogue de films :** Affichage des films avec affiches, prix, et places disponibles.
*   **Recherche et Filtres :** Recherche par titre et filtrage par genre.
*   **Détails du film :** Synopsis, bande-annonce (vidéo YouTube), durée, séance, avis des autres utilisateurs.
*   **Authentification :** Inscription (rôle 'user' par défaut) et Connexion sécurisée avec persistance de session (localStorage).

#### B. Espace Utilisateur
*   **Réservation :** Sélection du nombre de places, calcul automatique du prix total.
*   **Paiement :** Simulation de paiement par carte bancaire avec validation visuelle.
*   **Historique :** Consultation des réservations passées et possibilité d'annulation (si la séance n'est pas passée).
*   **Interaction :** Ajout de commentaires et de notes (étoiles) sur les films.
*   **Contact :** Formulaire d'envoi de messages au support.

#### C. Espace Administration (Back-Office)
*   **Tableau de bord (Dashboard) :** Statistiques en temps réel (nombre de films, utilisateurs, réservations, messages).
*   **Gestion des Films :**
    *   Ajouter un film (Titre, Genre, Prix, Date, Description).
    *   Gestion des médias : Upload d'image (affiche) ou URL, lien vidéo (trailer).
    *   Modifier ou Supprimer un film (mise à jour immédiate de la liste).
*   **Gestion des Utilisateurs :** Liste des inscrits, rôles, suppression de comptes.
*   **Gestion des Réservations :** Vue d'ensemble des billets vendus.
*   **Modération :** Suppression des commentaires inappropriés.
*   **Messagerie :** Lecture et suppression des messages de contact reçus.

---

## 3. Cahier des Charges Technique

### 3.1. Architecture
Le projet suit une architecture **MVC (Modèle-Vue-Contrôleur)** adaptée aux applications modernes :
*   **Backend :** API RESTful exposant des endpoints JSON.
*   **Frontend :** Single Page Application (SPA) simulée via JavaScript vanilla, communiquant avec le backend via `fetch`.

### 3.2. Stack Technologique

#### Backend (Serveur)
*   **Langage :** Java (JDK 17+)
*   **Framework :** Spring Boot 3.x
    *   *Spring Web :* Pour la gestion des contrôleurs REST.
    *   *Spring Data JPA :* Pour l'abstraction de la base de données.
*   **Gestion de projet :** Maven

#### Frontend (Interface)
*   **Structure :** HTML5 (Templates Freemarker `.ftlh` pour le rendu initial).
*   **Style :** CSS3 natif + Framework **Tailwind CSS** (via CDN) pour le design responsive et moderne (Dark mode).
*   **Logique :** JavaScript (ES6+) pur (Vanilla JS). Aucune dépendance lourde type React/Angular.

#### Base de Données & Stockage
*   **SGBD :** H2 Database (Base de données relationnelle en mémoire pour le développement) ou MySQL/PostgreSQL (configurable).
*   **Stockage Fichiers :** Stockage local sur le serveur (répertoire `user.home/cinemax-uploads`) pour les affiches de films uploadées.

### 3.3. Modèle de Données (Schéma simplifiée)

1.  **User**
    *   `id` (Long) : Identifiant unique de l'utilisateur.
    *   `username` (String) : Nom d'utilisateur pour l'affichage et la connexion.
    *   `password` (String) : Mot de passe pour la sécurité.
    *   `email` (String) : Adresse email unique pour les notifications.
    *   `first_name` (String) : Prénom de l'utilisateur.
    *   `last_name` (String) : Nom de l'utilisateur.
    *   `phone` (String) : Numéro de téléphone.
    *   `role` (String) : Rôle de l'utilisateur ('admin' ou 'user').
    *   `created_at` (DateTime) : Date de création du compte.

2.  **Movie**
    *   `id` (Long) : Identifiant unique du film.
    *   `title` (String) : Titre du film.
    *   `genre` (String) : Genre du film.
    *   `duration` (String) : Durée du film.
    *   `price` (Double) : Prix unitaire du billet.
    *   `showtime` (String) : Horaire de la séance.
    *   `availableSeats` (Int) : Nombre de places disponibles.
    *   `imageUrl` (String) : URL de l'affiche.
    *   `videoUrl` (String) : URL de la bande-annonce.
    *   `user_id` (Long) : Créateur du film (Admin).

3.  **Reservation**
    *   `id` (Long) : Identifiant de la réservation.
    *   `userId` (Long) : ID du client ayant réservé.
    *   `movieId` (Long) : ID du film réservé.
    *   `seatsBooked` (Int) : Nombre de places réservées.
    *   `price` (Double) : Prix total payé.
    *   `created_at` (DateTime) : Date de la réservation.

4.  **Comment**
    *   `id` (Long) : Identifiant du commentaire.
    *   `userId` (Long) : Auteur du commentaire.
    *   `movieId` (Long) : Film concerné.
    *   `comment` (String) : Contenu de l'avis.
    *   `rating` (Int) : Note (1-5).
    *   `created_at` (DateTime) : Date du commentaire.

5.  **ContactMessage**
    *   `id` (Long) : Identifiant du message.
    *   `user_id` (Long) : ID utilisateur (Optionnel, permet de lier le message à un compte existant).
    *   `name` (String) : Nom de l'expéditeur.
    *   `email` (String) : Email de contact.
    *   `subject` (String) : Objet du message.
    *   `message` (String) : Contenu du message.
    *   `created_at` (DateTime) : Date d'envoi.

### 3.4. API Endpoints (Exemples)

| Méthode | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/users/login` | Authentification utilisateur |
| `GET` | `/api/movies` | Récupérer tous les films |
| `POST` | `/api/movies` | Créer un film (Admin) |
| `DELETE` | `/api/movies/{id}` | Supprimer un film (Admin) |
| `POST` | `/api/reservations` | Créer une réservation |
| `POST` | `/api/upload` | Upload d'une image |

### 3.5. Sécurité
*   **Contrôle d'accès :** Vérification des rôles côté Frontend (masquage des menus) et côté Backend (logique métier).
*   **Persistance :** Utilisation du `localStorage` navigateur pour maintenir la session utilisateur active.
*   **Uploads :** Nettoyage des noms de fichiers et génération d'UUID pour éviter les conflits et failles de sécurité.

---

## 4. Évolutions Futures Possibles
*   Intégration d'une vraie passerelle de paiement (Stripe/PayPal).
*   Envoi d'emails de confirmation (JavaMailSender).
*   Système de placement numéroté dans la salle.
*   Application mobile (PWA).
