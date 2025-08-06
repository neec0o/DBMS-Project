package de.devnico.dbms.controller;

import de.devnico.dbms.model.DbSession;
import de.devnico.dbms.service.DbSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
