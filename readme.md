# Game2Gather

## Entwicklung

### Entwicklungsumgebung

Für die Entwicklung von Game2Gather müssen die folgenden Tools installiert werden:

- **Java**: Hauptprogrammiersprache
- **Spring Boot**: Framework zur Erstellung von Microservices
- **Spring Data JPA**: Datenzugriffsschicht
- **Liquibase**: Datenbank-Migrations-Tool
- **MySQL**: Datenbank
- **Maven**: Build-Automatisierung
- **Docker**: Containerisierung


### Datenbank
uf die Datenbank kann mit den folgenden Verbindungsdaten zugegriffen werden:

| Parameter | Wert          |
|-----------|---------------|
| Host      | localhost     |
| Port      | 1433:1433     |
| Database  | game2gather   |
| Username  | game2gather   |
| Password  | Game_2_Gather |

### App
Die Anwendung ist über http://localhost:4200 erreichbar.

### Testdata Profil
Um die Anwendung mit vorgefertigten Testdaten zu starten, 
müssen in den Run/Debug Configurations von IntelliJ unter dem Reiter 
"Spring Boot" und da unter dem Punkt "Active Profiles" testdata 
eingetragen werden und die Anwendung mit diesem hinterlegten Kondigurationen 
gestartet werden.

## Projektaufbau

### Design concept
Enthält ein Datenbankmodell, eine DatenbankExcel und das Entitity Relationship Modell

### Game2Gather-Frontend
Enthält das Frontend von Game2Gather

### Gamge2Gather_Backend 
Enthält das Backend von Game2Gather