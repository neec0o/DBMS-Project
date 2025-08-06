

[x] Login-Formular
    x- Formular: Host, Port, Benutzername, Passwort, DB-Typ (MySQL, Postgres…)
    x- Backend: Verbindungsversuch zu DB über JDBC
    x- Bei Erfolg: Session mit JDBC-Connection speichern (oder Pool)
    - Nach lokalen DB suchen?
[x] JDBC-Verbindung managen
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