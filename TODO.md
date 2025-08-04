dbms-springboot/
├── src/
│   ├── main/
│   │   ├── java/com/yourapp/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── model/
│   │   │   └── DbmsApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── templates/ (wenn Thymeleaf)


[] Login-Formular
    - Formular: Host, Port, Benutzername, Passwort, DB-Typ (MySQL, Postgres…)
    - Backend: Verbindungsversuch zu DB über JDBC
    - Bei Erfolg: Session mit JDBC-Connection speichern (oder Pool)
    - Nach lokalen DB suchen?
[] JDBC-Verbindung managen
        Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/",
        "user", "password");

[] Datenbanken erstellen
[] Tabellen erstellen
[] Key zuweisungen
[] Suchen in der Datenbank
[] Datenbank- und Tabellenanzeigen
    - Tabellenansicht im Frontend
[] Freie SQL-Konsole
    - User kann SQL eintippen → absenden → Ergebnis wird in Tabellenform zurückgegeben