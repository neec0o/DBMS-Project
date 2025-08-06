package de.devnico.dbms.model;

import java.sql.Connection;

public class DbSession {
    private String id;
    private Connection connection;

    public DbSession(String id, Connection connection) {
        this.id = id;
        this.connection = connection;
    }

    public String getId() {
        return id;
    }

    public Connection getConnection() {
        return connection;
    }
}
