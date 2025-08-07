package de.devnico.dbms.service;

import de.devnico.dbms.model.DbSession;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.sql.Connection;

@Service
public class DbSessionService {
    private final Map<String, DbSession> sessions = new ConcurrentHashMap<>();

    public String createSession(Connection connection) {
        String id = UUID.randomUUID().toString();
        sessions.put(id, new DbSession(id, connection));
        return id;
    }

    public DbSession getSession(String id) {
        return sessions.get(id);
    }

    public String connect(String host, String port, String username, String password) throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + port + "?useSSL=false&allowPublicKeyRetrieval=true";
        Connection connection = DriverManager.getConnection(url, username, password);
        return createSession(connection);
    }

}
