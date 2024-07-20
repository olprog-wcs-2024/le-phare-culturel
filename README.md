Merci pour la précision. Voici un README adapté pour votre projet "Le Phare Culturel" qui ne contient que le backend :

---

# Le Phare Culturel - Backend

Bienvenue dans le projet Le Phare Culturel. Ce projet est une application web pour la gestion d'événements culturels, développée avec Spring Boot pour le backend.

## Table des matières

- [Technologies](#technologies)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration](#configuration)
- [Démarrage](#démarrage)
- [Structure du projet](#structure-du-projet)
- [Fonctionnalités](#fonctionnalités)
- [Contribuer](#contribuer)
- [Licence](#licence)

## Technologies

### Backend

- Spring Boot 3.3.1
- Spring Security
- JWT pour l'authentification
- JPA/Hibernate pour la gestion de la base de données
- MySQL (ou tout autre SGBD compatible)

## Prérequis

- Java 17 ou supérieur
- Maven
- PostgreSQL (ou un autre SGBD compatible)

## Installation

### Cloner le dépôt

```bash
git clone https://github.com/olprog-wcs-2024/le-phare-culturel.git
cd le-phare-culturel
```

### Installation des dépendances

```bash
mvn clean install
```

## Configuration

1. **Base de données**: Configurez les paramètres de connexion à votre base de données dans `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:5432/lephareculturel
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

2. **JWT**: Configurez le secret et les temps d'expiration pour les tokens JWT dans `application.properties`.

```properties
jwt.secret=your_secret_key
jwt.accessTokenExpirationMinutes=1
jwt.refreshTokenExpirationMinutes=1440
```

## Démarrage

### Démarrer le backend

```bash
mvn spring-boot:run
```

Le serveur sera accessible à l'adresse [http://localhost:8080](http://localhost:8080).

## Structure du projet

```plaintext
le-phare-culturel/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── fr/olprog_c/le_phare_culturel/
│   │   │       ├── configuration/    # Configurations de sécurité et autres
│   │   │       ├── controller/       # Contrôleurs REST
│   │   │       ├── model/            # Entités JPA
│   │   │       ├── repository/       # Référentiels JPA
│   │   │       └── service/          # Services métier
│   │   └── resources/
│   │       ├── application.properties # Configuration de l'application
│   │       └── ...
│   └── test/
│       └── java/                     # Tests unitaires et d'intégration
│
└── pom.xml                           # Fichier de configuration Maven
```

## Fonctionnalités

- **Gestion des utilisateurs**: Inscription, connexion, déconnexion.
- **Gestion des événements**: Création, modification, suppression et recherche d'événements.
- **Notifications**: Système de notifications pour les événements.

## Contribuer

Les contributions sont les bienvenues ! Veuillez suivre les étapes ci-dessous pour contribuer :

1. Forkez le dépôt.
2. Créez votre branche feature (`git checkout -b feature/your-feature`).
3. Commitez vos modifications (`git commit -am 'Add some feature'`).
4. Poussez vers la branche (`git push origin feature/your-feature`).
5. Créez une nouvelle Pull Request.

## Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus d'informations.

---

Ce README fournit un guide clair et concis pour installer, configurer et démarrer le backend du projet, ainsi que des informations sur la structure du projet et les fonctionnalités. N'hésitez pas à ajuster les informations en fonction des spécificités de votre projet.
