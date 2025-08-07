


- [x] **Login-Formular**
    - Formular-Felder: Host, Port, Benutzername, Passwort, DB-Typ (MySQL, Postgres…)
- [x] **Backend**
    - Verbindungsversuch zu DB über JDBC
    - Bei Erfolg: Session mit JDBC-Connection speichern (oder Connection Pool)
    - Nach lokalen Datenbanken suchen?
- [x] **JDBC-Verbindung managen**
  ```java
  Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost:3306/", 
      "user", 
      "password"
  );
- [x] Datenbanken erstellen 
- [x] Tabellen erstellen 
- [] Key zuweisungen 
- [x] Suchen in der Datenbank 
- [] Datenbank- und Tabellenanzeigen - Tabellenansicht im Frontend 
- [] Freie SQL-Konsole - User kann SQL eintippen → absenden → Ergebnis wird in Tabellenform zurückgegeben
