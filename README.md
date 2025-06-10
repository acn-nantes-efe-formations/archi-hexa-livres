# 📚 Application de Gestion de Livres - Architecture Hexagonale

## 📋 Description

Cette application permet de gérer une collection de livres (CRUD) en suivant les principes de l’**architecture hexagonale** (Ports & Adapters). L'objectif est d'assurer une **séparation claire entre la logique métier et les technologies/frameworks utilisés**.

---

## 🏗️ Architecture

### Structure du projet

```
src/main/java/com/accenture/livres/
├── application/                 # Couche Application
│   ├── annotation/             # Annotations personnalisées
│   ├── domain/                # Modèles du domaine
│   ├── exception/             # Exceptions métier
│   ├── port/                  # Ports (interfaces)
│   │   ├── input/             # Ports d'entrée (Use Cases)
│   │   └── output/            # Ports de sortie (Repositories)
│   └── service/               # Implémentation des use cases
├── infrastructure/             # Infrastructure
│   ├── clientside/           # Côté client
│   │   ├── controller/        # Contrôleurs REST
│   │   ├── dto/               # Objets de transfert de données
│   │   └── mapper/            # Mappeurs DTO <-> Domaine
│   ├── configuration/           # Configuration Spring
│   └── serverside/           # Côté serveur
│       ├── adapter/           # Adapteurs de sortie
│       ├── entity/            # Entités JPA
│       └── repository/        # Repositories JPA
└── LivresApplication.java      # Point d’entrée de l’application
```

### Principes d'architecture

- **Domaine (Core)** : logique métier pure, sans dépendance.
- **Ports d’entrée** : interfaces représentant les cas d’usage.
- **Ports de sortie** : interfaces pour interagir avec les systèmes externes.
- **Adaptateurs** :
  - *Primaires* : contrôleurs REST, CLI, etc.
  - *Secondaires* : accès aux données, services externes.

---

## 🛠 Technologies utilisées

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database (en mémoire)
- Lombok
- MapStruct
- SpringDoc OpenAPI

---

## ✅ Fonctionnalités

- Création d’un livre
- Consultation de la liste des livres
- Consultation d’un livre par ID
- Mise à jour partielle
- Suppression d’un livre
- Validation des données
- Gestion des erreurs
- Transactions

---

## 🚀 Démarrage rapide

### ✅ Prérequis

- Java 21+
- Maven 3.6+

### ⚙️ Installation & Exécution

```bash
git clone [URL_DU_DEPOT]
cd archi-hexa-livres
./mvnw clean install
./mvnw spring-boot:run
```

L’application sera disponible à l’adresse : [http://localhost:8080](http://localhost:8080)

---

## 📖 Documentation de l’API

- Swagger UI : [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- OpenAPI JSON : [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🧪 Exécuter les tests

```bash
./mvnw test
```

---

## 🧩 Points d'extension possibles

Grâce à l’architecture hexagonale :

- Ajouter une nouvelle API → implémenter un **adaptateur primaire**
- Ajouter une nouvelle base ou un service externe → implémenter un **adaptateur secondaire**
- Ajouter une fonctionnalité métier → implémenter un **nouveau port d’entrée**

---

## 🛡 Bonnes pratiques adoptées

- Séparation des responsabilités
- Injection de dépendances
- Gestion centralisée des exceptions
- Validation des entrées
- Documentation des API
- Logging cohérent
- Tests unitaires et d'intégration

---

## 🔄 Gestion Transactionnelle

### Approche par AOP (Programmation Orientée Aspect)

L'application utilise une approche basée sur les aspects pour gérer les transactions de manière déclarative, offrant ainsi :

- **Séparation des préoccupations** : La logique transactionnelle est séparée de la logique métier
- **Réduction de la duplication** : Pas besoin d'annotations `@Transactional` sur chaque méthode
- **Maintenabilité** : Configuration transactionnelle centralisée

### Implémentation

#### 1. Annotation Personnalisée

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TransactionMethod {
}
```

#### 2. Aspect Transactionnel

```java
@Aspect
@Component
public class TransactionAspect {
    @Around("@annotation(com.accenture.livres.application.annotation.TransactionMethod)")
    @Transactional
    public Object transactionalMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
```

#### 3. Utilisation dans le code

```java
@TransactionMethod
public Livre ajouterLivre(Livre livre) {
    // Logique métier avec gestion transactionnelle automatique
}
```

### Comportement par Défaut

- **Propagation** : `REQUIRED` (rejoint une transaction existante ou en crée une nouvelle)
- **Rollback** : Automatique pour les exceptions non vérifiées (RuntimeException)
- **Isolation** : Niveau par défaut de la base de données
- **Read-only** : `false` par défaut
- **Timeout** : Aucun par défaut

### Bonnes Pratiques Implémentées

1. **Niveau Service** : Les transactions sont gérées au niveau des services métier
2. **Visibilité** : Seules les méthodes publiques sont éligibles à l'AOP
3. **Auto-invocation** : Les appels internes ne déclenchent pas l'aspect

### Configuration Recommandée

Dans `application.properties` :

```properties
# Niveau d'isolation (2 = READ_COMMITTED)
spring.jpa.properties.hibernate.connection.isolation=2

# Afficher les requêtes SQL (dév uniquement)
spring.jpa.show-sql=true

# Formatage des requêtes SQL
spring.jpa.properties.hibernate.format_sql=true
```

### Dépannage

1. **Assurez-vous** que les méthodes transactionnelles sont `public`
2. **Consultez les logs** pour le suivi des transactions

## 📝 Licence

À définir selon vos besoins (MIT, Apache 2.0, etc.)
