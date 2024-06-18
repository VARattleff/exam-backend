# ExamBackend Application

Applikationnen er udviklet af Viktor Rattleff som en del af Programmering 2 24 timers eksamen.

<hr/>

## Indholdsfortegnelse
- [Introduktion](#introduktion)
- [Installation](#installation)

## Introduktion

Dette projekt er en backend applikation designet til at demonstrere de færdigheder og teknikker, der er lært i Programmering 2 kurset.

## Installation

Følg disse trin for at installere og køre applikationen lokalt:

1. Klon dette repository:
   ```bash
   git clone https://github.com/VARattleff/exam-backend

 2. Naviger til projektmappen:
    ```bash
    cd ExamBackend
    ```
    
## Konfiguration 

For at konfigurere applikationen skal du oprette en application.properties fil i src/main/resources mappen med følgende indhold:

```properties
spring.jpa.hibernate.ddl-auto=create-drop

spring.datasource.url=${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_USERNAME}
spring.datasource.password=${JDBC_PASSWORD}

spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

 ## Kørsel

For at starte applikationen, følg disse trin:

1. Sørg for at have Java og Maven installeret på din maskine.
2. Start projecet xD