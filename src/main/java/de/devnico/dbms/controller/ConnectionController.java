package de.devnico.dbms.controller;

import de.devnico.dbms.model.ConnectionRequest;
import de.devnico.dbms.service.DbSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

@RestController
@RequestMapping("/connect")
public class ConnectionController {

    private final DbSessionService sessionService;

    public ConnectionController(DbSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<?> connect(@RequestBody ConnectionRequest request) {
        String url = buildJdbcUrl(request);

        try {
            Connection conn = DriverManager.getConnection(url, request.getUsername(), request.getPassword());
            String sessionId = sessionService.createSession(conn);
            return ResponseEntity.ok(Map.of(
                    "message", "Verbindung erfolgreich",
                    "sessionId", sessionId
            ));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of(
                    "error", "Verbindung fehlgeschlagen: " + e.getMessage()
            ));
        }
    }

    private String buildJdbcUrl(ConnectionRequest req) {
        return switch (req.getDbType().toLowerCase()) {
            case
                    "mysql" -> "jdbc:mysql://" + req.getHost() + ":" + req.getPort() + "/";
            case
                    "postgresql" -> "jdbc:postgresql://" + req.getHost() + ":" + req.getPort() + "/";

            default -> throw new IllegalArgumentException("Unsupported DB type: " + req.getDbType());
        };
    }
}
