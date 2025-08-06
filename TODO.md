com.example.dbms
├── controller
│   └── ConnectionController.java       ← POST /connect
│   └── DatabaseController.java         ← GET /databases
├── model
│   └── ConnectionRequest.java
│   └── DbSession.java                  ← Speichert Connection pro Session
├── service
│   └── DbSessionService.java           ← Sessionverwaltung (Map)
│   └── DatabaseService.java            ← Datenbankliste holen


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