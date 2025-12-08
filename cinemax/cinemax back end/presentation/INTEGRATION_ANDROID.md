# Guide d'Intégration Backend (Spring Boot) avec Frontend Android

Ce document explique comment connecter votre application Android (développée sous Android Studio) avec ce backend Spring Boot.

## 1. Pré-requis Réseau

### Adresse IP Spéciale pour l'Émulateur
Lorsque vous lancez votre backend en local, il est accessible via `http://localhost:8080`.
Cependant, l'émulateur Android considère `localhost` comme étant lui-même. Pour accéder à votre ordinateur (où tourne le backend) depuis l'émulateur, vous devez utiliser l'adresse IP spéciale :
**`http://10.0.2.2:8080`**

*Si vous testez sur un appareil physique, votre téléphone et votre PC doivent être sur le même réseau Wifi, et vous devez utiliser l'adresse IP locale de votre PC (ex: `192.168.1.x:8080`).*

### Configuration Android Manifest
Dans votre projet Android (`AndroidManifest.xml`), vous devez ajouter la permission d'accès à Internet :

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

De plus, comme vous êtes en développement local (HTTP et non HTTPS), vous devez autoriser le trafic en texte clair dans la balise `<application>` :

```xml
<application
    ...
    android:usesCleartextTraffic="true">
    ...
</application>
```

## 2. Communication avec l'API

Pour communiquer avec le backend, il est recommandé d'utiliser une librairie HTTP robuste comme **Retrofit**.

### Exemple d'Architecture Android

1.  **Définir les Modèles (Data Classes)**
    Créez des classes Kotlin/Java qui correspondent exactement à la structure JSON renvoyée par votre backend.
    *Exemple pour `Movie` :*
    ```kotlin
    data class Movie(
        val id: Long?,
        val title: String,
        val description: String,
        val duration: Int,
        val releaseDate: String, // ou Date
        val posterUrl: String?
    )
    ```

2.  **Interface API (Retrofit)**
    Définissez les endpoints disponibles dans une interface.
    ```kotlin
    interface ApiService {
        @GET("/api/movies")
        suspend fun getAllMovies(): List<Movie>

        @POST("/api/users/login")
        suspend fun login(@Body user: User): Response<User>
        
        // Ajoutez les autres méthodes ici (create, update, delete...)
    }
    ```

3.  **Instance Retrofit**
    Configurez Retrofit avec l'URL de base correcte.
    ```kotlin
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // Attention au slash final
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        
    val apiService = retrofit.create(ApiService::class.java)
    ```

## 3. Endpoints Disponibles

Voici les routes principales que votre application Android devra appeler (basées sur vos contrôleurs actuels) :

*   **Films (`/api/movies`)**
    *   `GET /api/movies` : Récupérer la liste des films.
    *   `GET /api/movies/{id}` : Détails d'un film.
    *   `POST /api/movies` : Ajouter un film (Admin).

*   **Utilisateurs (`/api/users`)**
    *   `POST /api/users/login` : Connexion utilisateur.
    *   `POST /api/users` : Inscription.
    *   `GET /api/users/{id}` : Profil utilisateur.

*   **Images (`/api/upload`)**
    *   `POST /api/upload` : Envoyer une image (affiche de film ou avatar).
    *   *Note : Pour afficher une image, utilisez l'URL retournée combinée à votre base URL (ex: `http://10.0.2.2:8080/uploads/nom_image.jpg`). Utilisez une librairie comme **Glide** ou **Picasso** pour l'affichage.*

## 4. Tester avant de Coder

Avant d'écrire le code Android, assurez-vous que votre backend fonctionne correctement en utilisant **Postman**.
1.  Lancez le backend (`mvn spring-boot:run` ou via votre IDE).
2.  Testez une route (ex: `GET http://localhost:8080/api/movies`).
3.  Si cela fonctionne dans Postman, vous pouvez passer à l'intégration Android.
