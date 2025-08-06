package de.devnico.dbms.controller;

import de.devnico.dbms.model.CreateDatabaseRequest;
import de.devnico.dbms.model.DbSession;
import de.devnico.dbms.service.DbSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/databases")
public class DatabaseController {

    private final DbSessionService sessionService;

    public DatabaseController(DbSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<?> getDatabases(@RequestHeader("X-Session-ID") String sessionId) {
        DbSession session = sessionService.getSession(sessionId);
        if (session == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Ungültige Session"));
        }

        try (var stmt = session.getConnection().createStatement()) {
            var rs = stmt.executeQuery("SHOW DATABASES");
            List<String> databases = new ArrayList<>();
            while (rs.next()) {
                databases.add(rs.getString(1));
            }
            return ResponseEntity.ok(databases);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createDatabase(
            @RequestHeader("X-Session-ID") String sessionId,
            @RequestBody CreateDatabaseRequest request
    ) {
        DbSession session = sessionService.getSession(sessionId);
        if (session == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Ungültige Session"));
        }

        String dbName = request.getName();
        if (dbName == null || dbName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name der Datenbank fehlt"));
        }

        try (var stmt = session.getConnection().createStatement()) {
            stmt.executeUpdate("CREATE DATABASE " + dbName);
            return ResponseEntity.ok(Map.of("message", "Datenbank '" + dbName + "' erstellt"));
        } catch (SQLException e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteDatabase(
            @RequestHeader("X-Session-ID") String sessionId,
            @PathVariable String name
    ) {
        DbSession session = sessionService.getSession(sessionId);
        if (session == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Ungültige Session"));
        }

        try (var stmt = session.getConnection().createStatement()) {
            stmt.executeUpdate("DROP DATABASE " + name);
            return ResponseEntity.ok(Map.of("message", "Datenbank '" + name + "' gelöscht"));
        } catch (SQLException e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    //Table Endpoints

    @GetMapping("/tables")
    public ResponseEntity<?> listTables(
            @RequestHeader("X-Session-ID") String sessionId,
            @RequestParam("db") String dbName
    ) {
        DbSession session = sessionService.getSession(sessionId);
        if (session == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Ungültige Session"));
        }

        try (Connection conn = session.getConnection();
             Statement stmt = conn.createStatement()) {

                stmt.execute("USE " + dbName);

                // Tabellen abrufen
                ResultSet rs = conn.getMetaData().getTables(dbName, null, "%", new String[]{"TABLE"});

                List<String> tables = new ArrayList<>();
                while (rs.next()) {
                    tables.add(rs.getString("TABLE_NAME"));
                }

                return ResponseEntity.ok(Map.of("tables", tables));

        } catch (SQLException e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

}
