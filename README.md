# Projet de mise en relation — Spring Boot (Web + JPA)

Application backend de mise en relation entre **porteurs/porteuses de projet** et **développeurs**.  
Les porteurs publient des projets, les devs candidatent, et les porteurs acceptent/refusent les candidatures.

## Stack
- Java 17 (ou 21)
- Spring Boot (Web, Data JPA, Validation)
- Base de données : **H2 en mémoire** (par défaut)
- Build : Maven
- **Sans Lombok** (POJOs classiques)

## Lancer l’application
```bash
# depuis la racine du projet
./mvnw spring-boot:run
# ou
mvn spring-boot:run
